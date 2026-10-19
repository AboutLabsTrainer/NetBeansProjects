# JPA

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Eine Entity und ein Spring-Data-Repository speichern eine Notiz in H2. Hibernate kommt nur aus dem Boot-BOM.

## Codebeispiel

Main-Klasse: `com.example.demo.DemoApplication`

`server.port`: **8080**

Das Codebeispiel startet mit Profil `mysql`. Die Übung nutzt H2 und braucht kein MySQL.

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`POST /api/notes?title=Erste Notiz` speichert eine Zeile. `GET /api/notes` zeigt sie.

Port **8302** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8302/api/notes`

## Stufe 2

Abfragemethode `findByTitle`. `GET /api/notes/search?title=Erste Notiz` liefert genau diese Notiz.

Port **8303** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8303/api/notes/search?title=Erste%20Notiz`

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
