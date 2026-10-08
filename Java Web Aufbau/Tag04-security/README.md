# Tag4 – Container‑Managed Security (Jakarta EE)
Namespace: `de.ibb` · Projektname: `Tag4` · Server: Payara 7 (Jakarta EE 10)

**Mini‑Challenge umgesetzt:** JDBC‑Realm mit **3 Rollen** (`admin`, `manager`, `user`) und **3 Bereichen** (`/admin/*`, `/manager/*`, `/user/*`), **FORM‑Login** (`login.jsp` mit `j_security_check`) und Logout.

## Setup Schritt für Schritt

### 1. NetBeans/Maven
1. Projekt über `pom.xml` importieren, Server **Payara 7** wählen.
2. `mvn clean package` erzeugt `target/Tag4.war`.

### 2. Datenbank vorbereiten (MySQL oder MariaDB)
1. `WEB-INF/sql/security-schema.sql` ausführen, dadurch entsteht die DB `jwebak_tag04_security` mit Tabellen `jwebak04_users` und `jwebak04_user_roles`.
2. Es werden drei Accounts angelegt (`admin`, `manager`, `user`) – Passwörter liegen als SHA‑256 Hex vor, z. B. `user`/`user123`.
3. Falls neue User angelegt werden müssen, Passwort ebenfalls SHA‑256 + Hex kodieren.

### 3. Payara: JDBC Connection Pool
1. Admin Console (`http://localhost:4848`) → `Resources > JDBC > JDBC Connection Pools > New`.
2. Werte:
   - `Pool Name`: `jwebak_tag04_security`
   - `Resource Type`: `javax.sql.DataSource`
   - `Datasource Classname`: `org.mariadb.jdbc.MariaDbDataSource` *(oder `com.mysql.cj.jdbc.MysqlDataSource` bei MySQL-Treiber)*
3. Unter **Additional Properties** diese Keys pflegen:
   - `serverName=localhost`
   - `portNumber=3306`
   - `databaseName=jwebak_tag04_security`
   - `user=jwebak_tag04_security`
   - `password=jwebak_tag04_security`
   - `URL=jdbc:mysql://localhost:3306/jwebak_tag04_security`
     - Der verwendete Treiber muss zuvor in `C:\payara7\glassfish/domains/domain1/lib` abgelegt werden
       (z. B. `mariadb-java-client-3.x.x.jar` oder `mysql-connector-j-8.x.x.jar`).
4. Danach im Pool `Ping` ausführen → muss „Ping Succeeded“ melden.

### 4. Payara: JDBC Resource & Realm
1. `Resources > JDBC > JDBC Resources > New`:
   - `JNDI Name`: `jdbc/jwebak_tag04_security` (diesen Namen erwartet der Realm)
   - `Pool Name`: `jwebak_tag04_security`
   - optional `Description`: „Security Realm Datasource“
2. Nach dem Anlegen der Resource in der Liste auf `jdbc/jwebak_tag04_security` klicken und per `Ping` testen – so wird geprüft, ob die Resource korrekt auf den Pool zugreift.
3. `Configurations > server-config > Security > Realms > New`:
   - `Name`: `jwebak_tag04_securityRealm`
   - `Class Name`: `com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm`
   - Properties:
     - `datasource-jndi = jdbc/jwebak_tag04_security`
     - `user-table = jwebak04_users`
     - `user-name-column = username`
     - `password-column = password`
     - `group-table = jwebak04_user_roles`
     - `group-table-user-name-column = username`
     - `group-name-column = role_name`
     - `digest-algorithm = SHA-256`
     - `encoding = Hex`
4. Domain neu starten (`asadmin restart-domain domain1`), damit Realm und Resource aktiv sind.
5. Wichtig: Es darf **kein** Tomcat-`DataSourceRealm` über `META-INF/context.xml` deployt werden
   (z. B. `org.apache.catalina.realm.DataSourceRealm`). Payara verwendet den oben konfigurierten
   Server-Realm `jwebak_tag04_securityRealm`.

### 5. Deploy & Test
1. `Tag4.war` deployen (`Applications > Deploy`).
2. URLs testen:
   - `http://localhost:8080/Tag4/user/dashboard` → Login mit `user/user123`
   - `http://localhost:8080/Tag4/manager/dashboard` → `manager/manager123` (oder `admin`)
   - `http://localhost:8080/Tag4/admin/dashboard` → `admin/admin123`
   - Logout: `http://localhost:8080/Tag4/logout`

## Hinweise
- `web.xml` deklariert **Security Constraints** und **Roles**; Login‑Form ist `login.jsp` (Action `j_security_check`, Felder `j_username`/`j_password`).
- Optionales `glassfish-web.xml` mapped Rollen ↔ Gruppen (hier identisch benannt).
- Hashes sind **SHA‑256 Hex** – kompatibel mit dem JDBC Realm.

## Funktionsüberblick
- `index.jsp` verlinkt die drei geschützten Bereiche.
- `login.jsp` stellt das FORM-Login (`j_security_check`) bereit.
- Je Rolle existiert ein Servlet, das nach erfolgreicher Authentifizierung ein individuelles Dashboard rendert:
  - `UserDashboard` (`/user/dashboard`)
  - `ManagerDashboard` (`/manager/dashboard`)
  - `AdminDashboard` (`/admin/dashboard`)
- `LogoutServlet` invalidiert die Session und schickt den Nutzer zum Login zurück.
- Die Security-Constraints in `web.xml` sorgen dafür, dass nur berechtigte Rollen die jeweiligen URLs erreichen können; das Mapping Admin → Manager → User erlaubt Admins alle Bereiche.
