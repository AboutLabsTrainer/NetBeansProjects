# HelloPayara – Tag 1 Übungen (Jakarta Servlet 6, Java 17)
Dieses Repository begleitet den ersten Schulungstag „Java EE/Jakarta EE Grundlagen“.  
Im Fokus stehen Application Server, Webcontainer, HTTP und erste Servlets auf Payara 7.

## Voraussetzungen
- JDK 17 (z. B. Temurin/OpenJDK)
- Apache Maven 3.9 oder neuer
- Payara Server 7 (HTTP-Port 8080) (Full oder Web Profile)

## Projektstruktur
- `pom.xml` – Maven-Projekt, Packaging `war`
- `src/main/java/com/javadeveloper/examples` – Beispiel-Servlets (`HelloServlet`, `GreetServlet`)
- `src/main/webapp` – Statische Inhalte und `WEB-INF`-Konfiguration
- `target/Tag1-1.0-SNAPSHOT.war` – Ausgabe nach `mvn package`

## Import & Deployment mit NetBeans
1. **Projekt öffnen:** `Datei → Öffnen` und Ordner `HelloPayara-Tag1-Exercises` (enthält `pom.xml`) wählen.
2. **Server einrichten:** Payara 7 in NetBeans registrieren (Services-Tab → Servers).
3. **Context-Root festlegen:** In den Projekteigenschaften unter *Run* `Context Path` auf `/HelloPayara` setzen.
4. **Deploy:** Rechtsklick auf das Projekt → `Run` oder `Deploy`. NetBeans baut automatisch und überträgt auf Payara.

## Deployment via Maven & asadmin (optional)
1. **Build:** `mvn clean package`
2. **Deploy:** `asadmin deploy --contextroot HelloPayara target/Tag1-1.0-SNAPSHOT.war`
   - Neu deployen: `asadmin redeploy --contextroot HelloPayara target/Tag1-1.0-SNAPSHOT.war`
   - Entfernen: `asadmin undeploy HelloPayara`

## Endpunkte (Context-Root: `/HelloPayara`)
- `GET http://localhost:8080/HelloPayara/hello` – Einfaches Hello-Servlet
- `GET http://localhost:8080/HelloPayara/greet?name=Anna` – Begrüßung mit optionalem Parameter `name`

## Nützliche Hinweise
- Payara Admin-Konsole: `http://localhost:4848`
- Servlet-Antworten werden als `text/plain` ausgeliefert – ideal für erste Tests mit Browser oder `curl`.
- NetBeans legt projektspezifische Einstellungen in `nb-configuration.xml` ab; diese kann ins Versionskontrollsystem aufgenommen werden.
