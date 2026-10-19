# Tag 3 – Servlets meistern: Servlet-API (NetBeans/Payara)

**Ziel des Projekts:** Einen praktischen Überblick über den Servlet-Lebenszyklus, Konfigurationsmechanismen (`ServletConfig`/`ServletContext`), den `RequestDispatcher` (forward/include), Filter, Listener, Cookies, Datei-Uploads, Fehlerseiten sowie Thread-Safety zu gewinnen.

## Voraussetzungen
- Java 17 oder höher
- Maven 3.8+
- Payara 6 (oder kompatibler Jakarta EE 10 Application Server)
- NetBeans (optional, empfohlen für die Übungen)

## Projekt importieren (NetBeans)
1. `Datei → Öffnen` und den Ordner mit der `pom.xml` auswählen.
2. Nach dem Import Rechtsklick auf das Projekt → `Run` oder `Deploy` auf Payara 6 (Port 8080).
3. Standard-URL: `http://localhost:8080/HelloPayara-Tag3/`

## Build & Deployment (CLI)
```bash
mvn clean package
```

Die erzeugte WAR-Datei liegt anschließend unter `target/HelloPayara-Tag3-ServletAPI.war` und kann manuell auf Payara 6 (Port 8080) deployt werden.

## Endpunkte (Basis-URL + Pfad)
- `/lifecycle` – Init-/Destroy-Hooks, Thread-Infos
- `/config` – Init-Parameter vs. Context-Parameter
- `/dispatch?mode=forward|include` – `RequestDispatcher`-Varianten
- `/metrics` – Listener-basierter Request-Zähler
- `/cookie` – Lesen/Schreiben eines Cookies
- `/upload` – Datei-Upload via `@MultipartConfig`
- `/error-demo?code=404|500` – `sendError` + Fehlerseiten
- `/counter` vs. `/counter-safe` – Thread-Safety-Demonstration

## Weitere Hinweise
- `@WebFilter` (`LoggingFilter`) protokolliert jeden Request.
- `@WebListener` (`AppLifecycleListener`) beobachtet Lifecycle-, Session- und Request-Ereignisse.
- Datei-Uploads landen standardmäßig im temporären Ordner (`java.io.tmpdir`); bei Bedarf Verzeichnis anpassen (z. B. via `MultipartConfig`-Annotation).