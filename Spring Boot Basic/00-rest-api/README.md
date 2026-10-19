# REST-API

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein `@RestController` beantwortet HTTP mit JSON. Die Daten liegen nur im Speicher.

## Codebeispiel

Main-Klasse: `com.example.restapi.RestApiApplication`

`server.port`: **8080**

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

Baue `GET /api/notes`. Die Antwort ist eine JSON-Liste mit einer Notiz, id `1`, Titel `Erste Notiz`.

Port **8200** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`GET http://localhost:8200/api/notes`

## Stufe 2

Übernimm die Liste und ergänze `POST /api/notes`. Der Body ist JSON mit `title`. Die neue id ist um eins höher als die bisherige Anzahl.

Port **8201**.

```bash
mvn spring-boot:run
```

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
