#!/usr/bin/env bash
# Linux/WSL-Variante von configure-ibb.ps1.
# Resets domain1 JDBC resources and installs the single pool jdbc/ibb plus realm ibb-realm.
# Domain, admin user and ports stay. Deployed applications are removed.
# Fragt vor dem Loeschen nach. --force ueberspringt die Rueckfrage.
#
#   PAYARA_HOME=/opt/payara6 docker/payara/configure-ibb.sh [--force]
set -uo pipefail

force=false
[ "${1:-}" = "--force" ] && force=true

payara_home="${PAYARA_HOME:-$HOME/payara6}"
asadmin="$payara_home/bin/asadmin"
if [ ! -x "$asadmin" ]; then
    echo "asadmin nicht gefunden: $asadmin. PAYARA_HOME auf den Payara-6-Ordner setzen." >&2
    exit 1
fi

# Der Pool nutzt com.mysql.cj.jdbc.MysqlDataSource. Ohne den Treiber im Server-Classpath scheitert erst ping-connection-pool am Ende.
driver=$(find "$payara_home/glassfish/lib" "$payara_home/glassfish/domains/domain1/lib" \
    -maxdepth 2 -name 'mysql-connector-j-*.jar' 2>/dev/null | head -n 1)
if [ -z "$driver" ]; then
    echo "MySQL Connector/J fehlt. mysql-connector-j-*.jar nach $payara_home/glassfish/lib kopieren oder 'asadmin add-library <jar>' ausfuehren." >&2
    exit 1
fi
echo "Treiber gefunden: $driver"

password_file=$(mktemp)
trap 'rm -f "$password_file"' EXIT
# domain1 hat ein leeres Admin-Passwort. NetBeans speichert deshalb keinen Benutzernamen.
echo "AS_ADMIN_PASSWORD=" > "$password_file"

as() {
    "$asadmin" --user admin --passwordfile "$password_file" "$@"
}

run() {
    if ! as "$@"; then
        echo "asadmin fehlgeschlagen: $*" >&2
        exit 1
    fi
}

lines() {
    local output
    if ! output=$(as "$@"); then
        echo "asadmin fehlgeschlagen: $*" >&2
        exit 1
    fi
    grep -vE '^(Command |Nothing to list|No applications are deployed|Picked up JAVA_TOOL_OPTIONS)|^$' <<< "$output" || true
}

echo "Pruefe domain1 ueber den Admin-Port."
if ! as version; then
    echo "Starte domain1."
    "$asadmin" start-domain domain1 || { echo "domain1 konnte nicht gestartet werden." >&2; exit 1; }
else
    echo "domain1 antwortet auf Port 4848."
fi

apps=$(lines list-applications | awk '{print $1}')
pools=$(lines list-jdbc-connection-pools | grep -v '^__' || true)
if ! $force; then
    echo
    echo "Dieses Skript entfernt auf domain1:"
    echo "  Anwendungen: $(if [ -n "$apps" ]; then paste -sd, - <<< "$apps"; else echo '(keine)'; fi)"
    echo "  JDBC-Pools:  $(if [ -n "$pools" ]; then paste -sd, - <<< "$pools"; else echo '(keine)'; fi)"
    echo "  Realms:      jwebak04Realm, ibb-realm"
    read -r -p "Fortfahren? (j/n) " answer
    if [[ ! "$answer" =~ ^[jJyY] ]]; then
        echo "Abgebrochen. Nichts geaendert."
        exit 1
    fi
fi

echo "Entferne deployte Anwendungen."
for app in $apps; do
    echo "undeploy $app"
    run undeploy "$app"
done

echo "Entferne alte Realms."
for realm in jwebak04Realm ibb-realm; do
    as delete-auth-realm "$realm"
done

echo "Entferne JDBC-Ressourcen und Pools."
for resource in $(lines list-jdbc-resources); do
    [[ "$resource" == jdbc/__* ]] && continue
    echo "delete-jdbc-resource $resource"
    run delete-jdbc-resource "$resource"
done
for pool in $pools; do
    echo "delete-jdbc-connection-pool $pool"
    run delete-jdbc-connection-pool "$pool"
done

pool_props="user=ibb-user:password=ibb-pw:DatabaseName=ibb-db:ServerName=localhost:portNumber=3306:useSSL=false:allowPublicKeyRetrieval=true:serverTimezone=UTC"
run create-jdbc-connection-pool \
    --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource \
    --restype javax.sql.DataSource \
    --property "$pool_props" \
    ibb-pool

run create-jdbc-resource --connectionpoolid ibb-pool jdbc/ibb

realm_props='jaas-context=jdbcRealm:datasource-jndi=jdbc/ibb:user-table=jwebak03_users:user-name-column=username:password-column=password:group-table=jwebak03_user_roles:group-name-column=role_name:digest-algorithm=SHA-256:encoding=Hex'
run create-auth-realm \
    --classname com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm \
    --property "$realm_props" \
    ibb-realm

echo "Starte domain1 neu, damit das Realm geladen wird."
run restart-domain domain1
run ping-connection-pool ibb-pool

echo "Pools:"
run list-jdbc-connection-pools
echo "Realms:"
run list-auth-realms
echo "Fertig: Pool ibb-pool, Ressource jdbc/ibb, Realm ibb-realm."
