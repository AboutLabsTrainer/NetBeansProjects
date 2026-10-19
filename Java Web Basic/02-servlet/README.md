# 02 Servlet

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

`RequestDispatcher` unterscheidet Forward und Include. Ein Zähler für parallele Requests erhöht sich nur an einer Stelle.

## Codebeispiel

- Context-Pfad: `/HelloPayara-Tag3`
- URL: http://localhost:8080/HelloPayara-Tag3/lifecycle , http://localhost:8080/HelloPayara-Tag3/config , http://localhost:8080/HelloPayara-Tag3/dispatch?mode=forward , http://localhost:8080/HelloPayara-Tag3/metrics , http://localhost:8080/HelloPayara-Tag3/cookie , http://localhost:8080/HelloPayara-Tag3/upload , http://localhost:8080/HelloPayara-Tag3/error-demo?code=404 , http://localhost:8080/HelloPayara-Tag3/counter und http://localhost:8080/HelloPayara-Tag3/counter-safe
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`DispatchMode.from` liefert `FORWARD` oder `INCLUDE`. Die Schreibweise darf abweichen. Jeder andere Text wirft `IllegalArgumentException`.

## Stufe 2

`VisitCounter.next` ist `synchronized`, startet bei 0 und liefert nach jedem Aufruf den neuen Stand.

## Abnahme

- Stufe 1: `forward`, `include` und ein ungültiger Wert.
- Stufe 2: zwei Aufrufe liefern 1 und 2.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
