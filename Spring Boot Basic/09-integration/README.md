# Integration

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein REST-Endpunkt nutzt eine Service-Bean. So liegen Ansicht und Fachlogik auseinander.

## Codebeispiel

Main-Klasse: `com.example.personmanagement.PersonManagementApplication`

`server.port`: **8100**

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`GET /api/names` liefert eine JSON-Liste mit `Ada`.

Port **8218** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8218/api/names`

## Stufe 2

`GET /api/count` liefert die Anzahl der Namen, hier `1`.

Port **8219** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8219/api/count`

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
