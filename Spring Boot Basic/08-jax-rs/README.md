# JAX-RS

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Eine Jakarta-Resource mit `@Path` liefert die Notiz. Jersey registriert sie in einer `ResourceConfig`.

## Codebeispiel

Main-Klasse: `com.example.jaxrs.JaxRsApplication`

`server.port`: **9099**

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`GET /api/notes` liefert den Text `Erste Notiz`.

Port **8216** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8216/api/notes`

`spring.jersey.application-path` ist `/api`.

## Stufe 2

`POST /api/notes` mit dem Text `Zweite` hängt die Notiz an. Die Antwort ist `Erste Notiz,Zweite`.

Port **8217** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8217/api/notes`

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
