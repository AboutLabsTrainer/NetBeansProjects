# Resets domain1 JDBC resources and installs the single pool jdbc/ibb plus realm ibb-realm.
# Domain, admin user and ports stay. Deployed applications are removed.
# Fragt vor dem Loeschen nach. -Force ueberspringt die Rueckfrage.
param([switch]$Force)

$ErrorActionPreference = "Continue"
$PSNativeCommandUseErrorActionPreference = $false
$asadmin = "C:\payara6\bin\asadmin.bat"
if (-not (Test-Path $asadmin)) {
    throw "asadmin nicht gefunden: $asadmin"
}

# Der Pool nutzt com.mysql.cj.jdbc.MysqlDataSource. Ohne den Treiber im Server-Classpath scheitert erst ping-connection-pool am Ende.
$driverDirs = @("C:\payara6\glassfish\lib", "C:\payara6\glassfish\domains\domain1\lib", "C:\payara6\glassfish\domains\domain1\lib\ext")
$driver = $driverDirs | Where-Object { Test-Path $_ } | ForEach-Object { Get-ChildItem -Path $_ -Filter "mysql-connector-j-*.jar" } | Select-Object -First 1
if (-not $driver) {
    throw "MySQL Connector/J fehlt. mysql-connector-j-*.jar nach C:\payara6\glassfish\lib kopieren oder 'asadmin add-library <jar>' ausfuehren."
}
Write-Host "Treiber gefunden: $($driver.FullName)"

$passwordFile = Join-Path $env:TEMP "payara-ibb-password.txt"
# domain1 auf diesem Rechner hat ein leeres Admin-Passwort. NetBeans speichert deshalb keinen Benutzernamen.
Set-Content -Path $passwordFile -Value "AS_ADMIN_PASSWORD=" -Encoding ascii

function Invoke-Asadmin {
    param([Parameter(ValueFromRemainingArguments = $true)][string[]]$AsadminArgs)
    & $asadmin --user admin --passwordfile $passwordFile @AsadminArgs
    if ($LASTEXITCODE -ne 0) {
        throw "asadmin fehlgeschlagen: $($AsadminArgs -join ' ')"
    }
}

function Get-AsadminLines {
    param([string[]]$AsadminArgs)
    $output = & $asadmin --user admin --passwordfile $passwordFile @AsadminArgs
    if ($LASTEXITCODE -ne 0) {
        throw "asadmin fehlgeschlagen: $($AsadminArgs -join ' ')"
    }
    return @($output | Where-Object {
        $_ -and
        ($_ -notmatch '^Command ') -and
        ($_ -notmatch '^Nothing to list') -and
        ($_ -notmatch '^No applications are deployed') -and
        ($_ -notmatch '^Picked up JAVA_TOOL_OPTIONS')
    })
}

Write-Host "Pruefe domain1 ueber den Admin-Port."
& $asadmin --user admin --passwordfile $passwordFile version | Out-Host
if ($LASTEXITCODE -ne 0) {
    Write-Host "Starte domain1."
    & $asadmin start-domain domain1 | Out-Host
    if ($LASTEXITCODE -ne 0) {
        throw "domain1 konnte nicht gestartet werden."
    }
} else {
    Write-Host "domain1 antwortet auf Port 4848."
}

$apps = @(Get-AsadminLines list-applications)
$pools = @(Get-AsadminLines list-jdbc-connection-pools | Where-Object { $_ -notlike "__*" })
if (-not $Force) {
    Write-Host ""
    Write-Host "Dieses Skript entfernt auf domain1:"
    Write-Host "  Anwendungen: $(if ($apps) { ($apps | ForEach-Object { ($_ -split '\s+')[0] }) -join ', ' } else { '(keine)' })"
    Write-Host "  JDBC-Pools:  $(if ($pools) { $pools -join ', ' } else { '(keine)' })"
    Write-Host "  Realms:      jwebak04Realm, ibb-realm"
    $answer = Read-Host "Fortfahren? (j/n)"
    if ($answer -notmatch '^[jJyY]') {
        Write-Host "Abgebrochen. Nichts geaendert."
        exit 1
    }
}

Write-Host "Entferne deployte Anwendungen."
foreach ($app in $apps) {
    $name = ($app -split '\s+')[0]
    if ($name) {
        Write-Host "undeploy $name"
        Invoke-Asadmin undeploy $name
    }
}

Write-Host "Entferne alte Realms."
foreach ($realm in @("jwebak04Realm", "ibb-realm")) {
    & $asadmin --user admin --passwordfile $passwordFile delete-auth-realm $realm | Out-Host
}

Write-Host "Entferne JDBC-Ressourcen und Pools."
foreach ($resource in (Get-AsadminLines list-jdbc-resources)) {
    if ($resource -like "jdbc/__*") { continue }
    Write-Host "delete-jdbc-resource $resource"
    Invoke-Asadmin delete-jdbc-resource $resource
}
foreach ($pool in (Get-AsadminLines list-jdbc-connection-pools)) {
    if ($pool -like "__*") { continue }
    Write-Host "delete-jdbc-connection-pool $pool"
    Invoke-Asadmin delete-jdbc-connection-pool $pool
}

$poolProps = "user=ibb-user:password=ibb-pw:DatabaseName=ibb-db:ServerName=localhost:portNumber=3306:useSSL=false:allowPublicKeyRetrieval=true:serverTimezone=UTC"
Invoke-Asadmin create-jdbc-connection-pool `
    --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource `
    --restype javax.sql.DataSource `
    --property $poolProps `
    ibb-pool

Invoke-Asadmin create-jdbc-resource --connectionpoolid ibb-pool jdbc/ibb

$realmProps = 'jaas-context=jdbcRealm:datasource-jndi=jdbc/ibb:user-table=jwebak03_users:user-name-column=username:password-column=password:group-table=jwebak03_user_roles:group-name-column=role_name:digest-algorithm=SHA-256:encoding=Hex'
Invoke-Asadmin create-auth-realm `
    --classname com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm `
    --property $realmProps `
    ibb-realm

Write-Host "Starte domain1 neu, damit das Realm geladen wird."
$listed = & $asadmin list-domains
if ($listed -match "domain1 running") {
    Invoke-Asadmin restart-domain domain1
} else {
    Write-Host "Keine pid-Datei. Beende den Prozess auf Port 4848 und starte domain1."
    netstat -ano | Select-String ":4848" | ForEach-Object {
        if ($_.Line -match "ABH|LISTEN" -and $_.Line -match "\s(\d+)\s*$") {
            Stop-Process -Id ([int]$Matches[1]) -Force -ErrorAction SilentlyContinue
        }
    }
    Start-Sleep -Seconds 3
    & $asadmin start-domain domain1 | Out-Host
    if ($LASTEXITCODE -ne 0) {
        throw "domain1 konnte nach dem Stopp nicht gestartet werden."
    }
}
Invoke-Asadmin ping-connection-pool ibb-pool

Write-Host "Pools:"
Invoke-Asadmin list-jdbc-connection-pools
Write-Host "Realms:"
Invoke-Asadmin list-auth-realms
Write-Host "Fertig: Pool ibb-pool, Ressource jdbc/ibb, Realm ibb-realm."
