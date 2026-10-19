# Security

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

HTTP Basic schützt `/api/me`. Ein öffentlicher Pfad bleibt offen. Die Rolle ADMIN schützt einen zweiten Pfad.

## Codebeispiel

Main-Klasse: `com.fleetdata.personmanagement.PersonManagementApplication`

`server.port`: **8080**

Das Codebeispiel spricht MariaDB an. Die Übung hält Benutzer nur im Speicher und braucht keine Datenbank.

Im Ordner `codebeispiel`:

```bash
mvn spring-boot:run
```

## Stufe 1

Benutzer `ada` / `secret`, Rolle `USER`. `GET /api/public` ist offen. `GET /api/me` ohne Login liefert 401, mit Basic-Auth den Namen `ada`.

Port **8306** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8306/api/me`

## Stufe 2

`GET /api/admin` nur für die Rolle `ADMIN` (Benutzer `admin` / `secret`). `AccessPolicy.canOpenAdmin` prüft die Rolle ohne Server.

Port **8307** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8307/api/admin`

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
