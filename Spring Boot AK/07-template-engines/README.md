# Template Engines

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Eine Server-Seite wird mit Thymeleaf gefüllt. Die anderen Engines bleiben im Codebeispiel.

## Codebeispiel

Jedes Demo bleibt ein eigener Ordner in `codebeispiel`. Nur eines gleichzeitig starten.

| Ordner | Main-Klasse | server.port |
| --- | --- | --- |
| thymeleaf-demo | `de.ibb.demo.ThymeleafDemoApplication` | **8080** |
| freemarker-demo | `de.ibb.demo.FreemarkerDemoApplication` | **8081** |
| jte-demo | `de.ibb.demo.JteDemoApplication` | **8082** |
| groovy-demo | `de.ibb.demo.GroovyDemoApplication` | **8083** |
| pebble-demo | `de.ibb.demo.PebbleDemoApplication` | **8084** |
| mustache-demo | `de.ibb.demo.MustacheDemoApplication` | **8085** |
| jsp-demo | `de.ibb.demo.JspDemoApplication` | **8086** |

Beispiel im Ordner `codebeispiel/thymeleaf-demo`:

```bash
mvn spring-boot:run
```

## Stufe 1

Die Startseite zeigt `Thymeleaf`.

Port **8314** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8314/`

## Stufe 2

`GET /about` zeigt `Eine Engine`.

Port **8315** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8315/about`

## Abnahme

Im Ordner `uebung-stufe-1` und `uebung-stufe-2`:

```bash
mvn -q -DskipTests compile
```

Im Ordner `loesung/stufe-1` und `loesung/stufe-2`:

```bash
mvn -q test
```

## Lösung erst nach eigenem Versuch

Die Lösungen liegen unter `loesung/stufe-1` und `loesung/stufe-2`. Erst öffnen, wenn die eigene Übung kompiliert und das erwartete Verhalten zeigt.
