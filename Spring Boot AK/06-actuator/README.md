# Actuator

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein eigener `HealthIndicator` meldet UP. Actuator zeigt ihn unter `/actuator/health`.

## Codebeispiel

Main-Klasse: `com.example.ActuatorMonitoringApplication`

`server.port`: **8080**

Das Codebeispiel nutzt H2. Die Übung hat keine Datenbank und keinen Mailserver.

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

`LabHealthIndicator` ist UP und trägt das Detail `modul=actuator`.

Port **8312** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8312/actuator/health`

## Stufe 2

Zweiter Indikator `QueueHealthIndicator` mit Detail `queue=orders`.

Port **8313** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8313/actuator/health`

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
