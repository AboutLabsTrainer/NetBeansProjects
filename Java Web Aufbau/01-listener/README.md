# 01 Listener

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Listener zählen Requests und Sessions. Die Dauer landet in einem Korb, der höchste Session-Stand bleibt stehen.

## Codebeispiel

- Context-Pfad: `/Tag2`
- URL: http://localhost:8080/Tag2/ , http://localhost:8080/Tag2/admin/monitor , http://localhost:8080/Tag2/admin/api/metrics
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.
- `/admin/*` ist hier ohne Login erreichbar, damit das Lab ohne MySQL und Realm läuft. In einer echten Anwendung gehört eine `security-constraint` davor, wie in [03-security](../03-security/README.md).

## Stufe 1

`RequestBucket.of` liefert `FAST` unter 100 ms, `OK` unter 500 ms und sonst `SLOW`.

## Stufe 2

`SessionPeak.peak` behält den höheren Wert aus bisherigem Peak und aktuell aktiven Sessions.

## Abnahme

- Stufe 1: 99, 100, 499 und 500.
- Stufe 2: Peak 3 mit 5 aktiven wird 5, Peak 7 mit 4 aktiven bleibt 7.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
