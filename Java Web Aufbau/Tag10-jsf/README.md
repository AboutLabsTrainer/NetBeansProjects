# Tag10-JSF-PrimeFaces – JSF Application
Namespace: `de.ibb` · Projektname: `Tag10-JSF-PrimeFaces` · Server: **Payara 7** (Jakarta EE 10)

## Features
- **JSF** mit Standard-Komponenten (`h:dataTable`, `h:messages`, `h:inputText`, `h:commandButton`)
- **AJAX‑Suche** für Produkte
- **CRUD-Operationen** für Produkte

## Start
1. Projekt via `pom.xml` in NetBeans öffnen und **Payara 7** wählen.
2. Projekt bauen: `mvn clean package` (erstellt WAR-Datei mit MariaDB JDBC Driver).
3. MySQL-Benutzer erstellen (falls noch nicht vorhanden):
   ```sql
   CREATE USER IF NOT EXISTS 'jwebak_tag10_jsf'@'localhost' IDENTIFIED BY 'jwebak_tag10_jsf';
   GRANT ALL PRIVILEGES ON jwebak_tag10_jsf.* TO 'jwebak_tag10_jsf'@'localhost';
   FLUSH PRIVILEGES;
   ```
4. `sql/schema-mysql.sql` ausführen (DB `jwebak_tag10_jsf`).
5. **MariaDB JDBC Driver in Payara installieren** (siehe [MariaDB JDBC Driver](#mariadb-jdbc-driver) unten).
6. Datenquelle einrichten (siehe [Setup Datenquelle](#setup-datenquelle) unten).
7. Deploy & testen: `http://localhost:8080/Tag10JSFPrimeFaces/product-list.xhtml`

## Setup Datenquelle

Die MySQL-Datenquelle kann entweder über die **Payara Admin Console** (UI) oder automatisch beim Deployment eingerichtet werden.

### Option 1: Payara Admin Console (UI)

1. **Payara Server starten** und Admin Console öffnen:
   - URL: `http://localhost:4848`
   - Standard-Login: `admin` / `admin` (oder wie konfiguriert)

2. **JDBC Connection Pool erstellen**:
   - Navigation: **Resources** → **JDBC** → **JDBC Connection Pools** → **New**
   - **Pool Name**: `jwebak_tag10_jsf`
   - **Resource Type**: `javax.sql.DataSource`
   - **Database Driver Vendor**: `MySQL` (oder `Other`)
   - Klicke auf **Next**

3. **Pool-Eigenschaften konfigurieren**:
   - **Datasource Classname**: `org.mariadb.jdbc.MariaDbDataSource`
   - Klicke auf **Next**

4. **Zusätzliche Eigenschaften hinzufügen**:
   - Klicke auf **Add Property** und füge eine einzige Property hinzu:
     - `url` = `jdbc:mariadb://localhost:3306/jwebak_tag10_jsf?user=jwebak_tag10_jsf&password=jwebak_tag10_jsf&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC`
   - **Hinweis:** MariaDB verwendet eine URL statt einzelner Properties
   - Klicke auf **Finish**

5. **Pool-Einstellungen anpassen** (optional):
   - Öffne den erstellten Pool `jwebak_tag10_jsf`
   - Unter **Pool Settings**:
     - **Steady Pool Size**: `2`
     - **Max Pool Size**: `16`
     - **Pool Resize Quantity**: `2`
     - **Idle Timeout**: `300`
   - Klicke auf **Save**

6. **JDBC Resource erstellen**:
   - Navigation: **Resources** → **JDBC** → **JDBC Resources** → **New**
   - **JNDI Name**: `jdbc/jwebak_tag10_jsf`
   - **Pool Name**: `jwebak_tag10_jsf` (aus Dropdown wählen)
   - Klicke auf **OK**

7. **Verbindung testen**:
   - Gehe zurück zu **JDBC Connection Pools** → `jwebak_tag10_jsf`
   - Klicke auf **Ping** (sollte "Ping Succeeded" anzeigen)


### Automatische Einrichtung (bei Deployment)

Falls `glassfish-resources.xml` (oder `payara-resources.xml`) im `WEB-INF` Verzeichnis vorhanden ist, wird die Datenquelle beim Deployment automatisch erstellt. 

**Hinweis:** 
- Payara 7 verwendet `glassfish-resources.xml` für die automatische Ressourcen-Erstellung
- Die Datei muss vor dem Deployment vorhanden sein
- **Wichtig:** Falls die Ressourcen nach dem Deployment nicht sichtbar sind:
  1. Prüfe die Server-Logs auf Fehler
  2. Stelle sicher, dass `glassfish-resources.xml` im `WEB-INF` Verzeichnis vorhanden ist
  3. Erstelle die Ressourcen manuell über die Admin Console (Option 1)

### Datenquelle prüfen

**Via UI:**
- **Resources** → **JDBC** → **JDBC Resources** → `jdbc/jwebak_tag10_jsf` sollte sichtbar sein
- **Resources** → **JDBC** → **JDBC Connection Pools** → `jwebak_tag10_jsf` sollte sichtbar sein

**Via asadmin:**
```cmd
# Alle Connection Pools auflisten
.\asadmin list-jdbc-connection-pools

# Alle JDBC Resources auflisten
.\asadmin list-jdbc-resources

# Verbindung zum Pool testen
.\asadmin ping-connection-pool jwebak_tag10_jsf
```

**Erwartete Ausgabe:**
- `list-jdbc-connection-pools` sollte `jwebak_tag10_jsf` in der Liste enthalten sein
- `list-jdbc-resources` sollte `jdbc/jwebak_tag10_jsf` in der Liste enthalten sein
- `ping-connection-pool jwebak_tag10_jsf` sollte `Command ping-connection-pool executed successfully.` anzeigen

### Fehlerbehebung

- **"Ping failed"**: 
  - Prüfe MySQL-Server läuft und Datenbank `jwebak_tag10_jsf` existiert
  - Prüfe MySQL-Benutzer `jwebak_tag10_jsf` hat Zugriff auf `jwebak_tag10_jsf`
  
- **"ClassNotFoundException" oder "Class name is wrong or classpath is not set"**:
  - MariaDB JDBC Driver muss im Payara `lib` Verzeichnis vorhanden sein
  - **Payara muss nach dem Kopieren des Drivers neu gestartet werden:**
    ```cmd
    .\asadmin restart-domain domain1
    ```
  
- **"Access denied"**: 
  - MySQL Benutzername/Passwort prüfen
  - Prüfe ob Benutzer `jwebak_tag10_jsf` existiert und Berechtigungen hat
  
- **"Pool is not initialized"**: 
  - Payara neu starten: `.\asadmin restart-domain domain1`

## Hinweise

### Produktive Umgebungen
- `jakarta.persistence.schema-generation.database.action` in `persistence.xml` auf `none` setzen
- Passwörter nicht in `payara-resources.xml` hardcoden, sondern über Payara-Variablen verwenden
- Connection Pool-Größen an die erwartete Last anpassen

### MariaDB JDBC Driver

**Wichtig:** Der MariaDB JDBC Driver muss vor dem Erstellen der Datenquelle installiert werden!

1. **Projekt bauen** (falls noch nicht geschehen):
   ```cmd
   mvn clean package
   ```

2. **MariaDB JDBC Driver in Payara installieren:**
   ```cmd
   # Vom Projektverzeichnis aus:
   copy target\Tag10JSFPrimeFaces\WEB-INF\lib\mariadb-java-client-*.jar "C:\payara7\glassfish\lib\"
   
   # Oder mit absolutem Pfad:
   copy "C:\Users\Trainer\Documents\NetBeansProjects\Java Web Aufbau\Tag10-JSF-PrimeFaces\target\Tag10JSFPrimeFaces\WEB-INF\lib\mariadb-java-client-*.jar" "C:\payara7\glassfish\lib\"
   ```

3. **Payara neu starten** (wichtig, damit der Driver geladen wird):
   ```cmd
   cd C:\payara7\bin
   .\asadmin restart-domain domain1
   ```
   
   **Hinweis:** Falls eine andere Domain verwendet wird, ersetze `domain1` durch den entsprechenden Domain-Namen.

4. **Verifizieren:**
   ```cmd
   dir C:\payara7\glassfish\lib\mariadb-java-client*.jar
   ```
   Sollte `mariadb-java-client-3.4.1.jar` (oder aktuelle Version) anzeigen.

**Ohne Neustart funktioniert die Datenquelle nicht!** Payara lädt die JDBC Driver nur beim Start.

**Hinweis:** Der Pool `jwebak_tag10_jsf` wird automatisch beim Deployment der Anwendung aus `payara-resources.xml` erstellt. Alternativ kann der Pool über die Admin Console erstellt werden (siehe [Option 1](#option-1-payara-admin-console-ui)).
