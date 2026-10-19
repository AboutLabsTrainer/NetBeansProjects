# Tag10 – Connection Pools & JDBC Performance (Mini‑Challenge)

**Namespace:** `de.ibb`  
**Projektname:** `Tag10`  
**Server:** Payara 6, HTTP-Port 8080 (Jakarta EE 10)  
**Packaging:** Maven WAR

## Ziel der Mini‑Challenge
Baue ein lauffähiges **Benchmark‑Projekt** und messe:
- `Statement` vs **`PreparedStatement`**
- **Single Inserts** vs **Batch Inserts**
- **Transaktion**: Commit & Rollback
- Wirkeinstellung des **Connection Pools** (JNDI `jdbc/jwebb_tag10_jdbc`)

Öffne in der App:  
- `/initdb` – erstellt das Schema *jwebb_products* und seedet 1000 Datensätze  
- `/benchmark` – UI zum Ausführen & Messen

## Start in NetBeans
1. Öffne das Maven‑Projekt **Tag10**.
2. Stelle sicher, dass Payara 6 als Server konfiguriert ist.
3. Deploye das Projekt (F6).  
4. Rufe `http://localhost:8080/Tag10/initdb` auf, dann `http://localhost:8080/Tag10/benchmark`.

**Hinweis:** Nach dem Build siehst du ggf. `Payara Server, deploy, null, false`. Das ist die Ausgabe des NetBeans-Payara-Plugins und kann bedeuten, dass kein detaillierter Deploy-Status zurückgegeben wurde. Prüfen: Payara läuft (Services → Server), im Browser `http://localhost:8080/Tag10/` öffnen. Wenn die App antwortet, war das Deploy erfolgreich.

### Wenn 404: App manuell deployen
Wenn `/Tag10/` oder `/Tag10/initdb` mit 404 antworten, hat Payara die App oft nicht registriert (z. B. weil das NetBeans-Deploy nicht durchgekommen ist). So deployest du die WAR von Hand:

1. **WAR bauen:** In NetBeans **Rechtsklick auf Projekt → Clean and Build** (oder `mvn clean package`).
2. **Payara Admin Console:** Im Browser `http://localhost:4848` öffnen.
3. **Applications → Deploy:** „Choose File“ → im Projektordner `target\Tag10.war` wählen → **OK**.
4. Nach dem Deploy erscheint **Tag10** in der Liste. Dann: `http://localhost:8080/Tag10/` und `http://localhost:8080/Tag10/initdb` testen.

**Alternative (Auto-Deploy):** Die Datei `target\Tag10.war` in das **autodeploy**-Verzeichnis deines Payara-Servers kopieren (z. B. `C:\payara7\glassfish\domains\domain1\autodeploy\`). Payara deployt die WAR automatisch.

## JNDI DataSource (Payara)
**Datenbank:** `jwebb_tag10_jdbc`, Nutzer und Passwort gleich dem Datenbanknamen. Tabellen mit Präfix `jwebb_` (z. B. `jwebb_products`).

Variante A – **glassfish-resources.xml** (automatisch beim Deploy):
- Datei: `src/main/webapp/WEB-INF/glassfish-resources.xml`
- Vorkonfiguriert: `URL=jdbc:mysql://localhost:3306/jwebb_tag10_jdbc`, `user=jwebb_tag10_jdbc`, `password=jwebb_tag10_jdbc`.

Variante B – **manuell im Admin Console** (http://localhost:4848):
- **JDBC Connection Pool** `jwebb_tag10_jdbc` anlegen (Classname `com.mysql.cj.jdbc.MysqlDataSource`).
- `URL=jdbc:mysql://localhost:3306/jwebb_tag10_jdbc`, `user=jwebb_tag10_jdbc`, `password=jwebb_tag10_jdbc`.
- Validation: `is-connection-validation-required=true`, `connection-validation-method=auto-commit`.
- Optional: `statement-cache-size=50`.
- **JDBC Resource**: `jdbc/jwebb_tag10_jdbc` → Pool `jwebb_tag10_jdbc`.

## Tuning‑Tipps
- Aktiviere Statement‑Cache im Pool (`statement-cache-size`) und Validation (z. B. `auto-commit`).  
- Miss **Wait‑Times** & **Used/Free Connections** und justiere `max-pool-size`.
- Nutze `PreparedStatement` für Sicherheit und Plan‑Reuse.  
- Für 100+ INSERTs: Batching in einer Transaktion (`setAutoCommit(false)` → `executeBatch()` → `commit()`).

## Quellen / weiterführend
- Tag 10 Kursseite: Connection Pools & JDBC Performance.  
- Payara Docs: JDBC & Connection Pools.  
- HikariCP – Hintergründe zu Pooling & Performance.

## Projektstruktur
- `BenchmarkServlet` – UI & Steuerlogik
- `SeedServlet` – Schema & Seed
- `ProductDAO` – JDBC‑Zugriffe (Prepared + Batch)
- `web.xml` – **wichtig** für NetBeans/Payara 6
- `glassfish-resources.xml` – (optional) Ressourcen‑Provisioning

Viel Erfolg beim Unterrichten! ✨
