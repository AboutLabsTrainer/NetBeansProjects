# Custom Starter

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Properties `greeting.*` und eine Auto-Configuration stellen einen `GreetingService` bereit, ohne ihn per Component-Scan zu finden.

## Codebeispiel

Main-Klasse der Demo: `de.ibb.demo.DemoApplication`

`server.port`: **8101**

Der Reactor bleibt in `codebeispiel`. Im Ordner `codebeispiel`:

```bash
mvn -pl greeting-spring-boot-starter-demo -am spring-boot:run
```

## Stufe 1

`GET /api/greet?name=Ada` liefert `Hallo Ada`. Die Auto-Configuration liegt im Paket `de.ibb.greeting` und ist über `AutoConfiguration.imports` registriert.

Port **8300** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8300/api/greet?name=Ada`

## Stufe 2

Zusätzliche Property `greeting.suffix`. Mit Suffix `!` lautet die Antwort `Hallo Ada!`.

Port **8301** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8301/api/greet?name=Ada`

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
