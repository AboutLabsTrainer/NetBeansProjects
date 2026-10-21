# Maven-Projekt in der IDE starten

Jedes Modul ist ein Elternordner (`00-thema`, `01-thema`, …). Der Elternordner ist kein Maven-Projekt. Geöffnet wird immer der Unterordner, in dem die `pom.xml` liegt:

| Ordner | Rolle |
|--------|--------|
| `codebeispiel` | Fertiges Beispiel zum Lesen und Starten |
| `uebung-stufe-1` | Geführte Aufgabe, Gerüst mit `TODO` |
| `uebung-stufe-2` | Transferaufgabe, weniger Gerüst |
| `loesung/stufe-1` und `loesung/stufe-2` | Lösungen, erst nach dem eigenen Versuch |

Die Aufgabe steht in der `README.md` des Elternordners. Diese Datei gilt für NetBeans, IntelliJ und VS Code.

## Voraussetzungen

- **JDK 21** (auf diesem Kursrechner `C:\Program Files\Java\jdk-21.0.11`). `JAVA_HOME` muss auf dieses JDK zeigen.
- **Maven 3.6+** (`mvn -version` zeigt Java 21).
- **Jakarta-Web** (`Java Web Basic`, `Java Web Aufbau`): Payara Community **6** unter `C:\payara6`. Die API kommt aus dem Server, Scope `provided`.
- **Spring Boot**: eingebetteter Tomcat. Vor dem nächsten Lab die vorherige Anwendung beenden, sonst bleibt der Port belegt.
- **MySQL**: Docker, nicht XAMPP. Im Ordner `docker/mysql` den Befehl `docker compose up -d` ausführen. Datenbank `ibb-db`, Benutzer `ibb-user`, Passwort `ibb-pw`. Adminer unter http://localhost:8088, Server `mysql`. Payara nutzt den Pool `jdbc/ibb` und das Realm `ibb-realm`. XAMPP-MySQL muss gestoppt sein, sonst ist Port 3306 belegt. Das Root-Passwort des Containers ist `ibb-root`.

## NetBeans

1. **Datei → Projekt öffnen**.
2. Den Unterordner wählen, der die `pom.xml` enthält, zum Beispiel `uebung-stufe-1`. Nicht den Elternordner `00-thema`.
3. Rechtsklick auf das Projekt → **Build**. Bei Konsolenprogrammen **Run** (Main-Klasse steht in der Modul-README). Bei Spring Boot die Klasse mit `main` starten. Bei Web **Deploy** auf Payara 6.

## IntelliJ IDEA

1. **File → Open** und denselben Unterordner mit der `pom.xml` wählen.
2. Projekt als Maven-Projekt importieren. SDK **21** einstellen (**File → Project Structure → Project SDK**).
3. Maven-Toolfenster: **compile**. Run-Konfiguration auf die Main-Klasse aus der Modul-README legen. Webprojekte auf den registrierten Payara 6 deployen.

## VS Code

1. Extension Pack for Java installieren.
2. **File → Open Folder** und den Unterordner mit der `pom.xml` öffnen.
3. Die Java-Runtime muss **JavaSE-21** sein. Danach den Hinweis **Import Maven project** bestätigen.
4. Start über **Run** an der `main`-Methode oder im Terminal mit den Befehlen unten.

## Befehle im Projektordner

```bash
mvn -q -DskipTests compile
mvn exec:java
mvn test
mvn spring-boot:run
mvn -q package
```

`mvn exec:java` gilt für Konsolenmodule, bei denen das POM die Main-Klasse setzt. `mvn test` prüft die Lösungen. `mvn spring-boot:run` startet Spring Boot. `mvn package` baut bei Webmodulen das WAR unter `target/`.

## Port belegt (Spring Boot)

Die Anwendung im Terminal mit Strg+C beenden und warten, bis der Prompt zurück ist. In NetBeans im Ausgabefenster **Stop** drücken. Wenn der Port danach noch belegt ist, nur den Prozess dieses Labs beenden. `8080` nicht pauschal freiräumen, der Message-Broker nutzt ihn ebenfalls.

```powershell
Get-NetTCPConnection -LocalPort 8080 -State Listen -ErrorAction SilentlyContinue |
  ForEach-Object { Stop-Process -Id $_.OwningProcess -Force }
```

Die Portnummer durch den `server.port` aus der Modul-README ersetzen.
