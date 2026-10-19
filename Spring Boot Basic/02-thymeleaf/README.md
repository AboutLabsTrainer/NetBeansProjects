# Thymeleaf

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein Controller legt ein Model ab und liefert den View-Namen. Thymeleaf ersetzt die Platzhalter.

## Codebeispiel

Main-Klasse: `de.ibb.springboot.tag3.SpringBootBasicTag3Application`

`server.port`: **8081**

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

Die Startseite zeigt die Überschrift `Willkommen im Lab`.

Port **8204** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8204/`

## Stufe 2

Zusätzliche Seite `GET /info` mit dem Text `Zweite Seite`. Die Startseite bleibt.

Port **8205** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8205/info`

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
