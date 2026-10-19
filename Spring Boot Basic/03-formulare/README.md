# Formulare

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein Formular wird auf ein Objekt gebunden. Bean Validation hält leere Pflichtfelder auf.

## Codebeispiel

Main-Klasse: `com.example.helloworldapi.HelloWorldApiApplication`

`server.port`: **8094**

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`POST /notes` mit leerem Titel bleibt auf `form`. Mit Titel `Lab` erscheint `saved`.

Port **8206** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8206/`

## Stufe 2

Zusätzliches Pflichtfeld `email` mit `@NotBlank` und `@Email`. Ohne gültige Adresse bleibt das Formular stehen.

Port **8207** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8207/`

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
