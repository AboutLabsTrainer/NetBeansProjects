# Konfiguration

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

`@ConfigurationProperties` bindet `lab.*`. Ein Logger schreibt auf DEBUG.

## Codebeispiel

Main-Klasse: `com.example.helloworldapi.HelloWorldApiApplication`

`server.port`: **8095** (Profil `dev` und `prod`)

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`GET /api/config` liefert den Wert von `lab.title`, hier `Kurs`.

Port **8208** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8208/api/config`

## Stufe 2

Zusätzliche Property `lab.subtitle`. `GET /api/config/subtitle` liefert `Mehr`.

Port **8209** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8209/api/config/subtitle`

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
