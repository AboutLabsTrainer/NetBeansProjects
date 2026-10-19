# 00 Hello

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein Servlet liest den Parameter `name` und antwortet mit einer Begrüßung als `text/plain`.

## Codebeispiel

- Context-Pfad: `/HelloPayara`
- URL: http://localhost:8080/HelloPayara/hello und http://localhost:8080/HelloPayara/greet?name=Anna
- Deploy: Ordner `codebeispiel` öffnen, Context-Pfad `/HelloPayara` setzen, auf Payara 6 deployen. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

In `uebung-stufe-1` `Greeting.text` fertigstellen. Fehlt der Name oder ist er leer, lautet der Text `Hallo Welt`, sonst `Hallo, Name!`. Randleerzeichen fallen weg. Das Servlet `/greet` gibt den Text aus.

## Stufe 2

In `uebung-stufe-2` `DayGreeting.text` schreiben. Stunde 5–11 ist Morgen, 12–17 Tag, 18–22 Abend, sonst Nacht. Ein leerer Name wird `Gast`. Stunde 9 und `Anna` ergeben `Guten Morgen, Anna!`.

## Abnahme

- Stufe 1: `null` und `Anna` erzeugen die beiden Sätze. Im Übungsordner `mvn -q -DskipTests compile`.
- Stufe 2: die vier Tageszeiten und der Gast-Fall.
- Die Lösung prüft dieselben Fälle mit `mvn -q test`, ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert und die Fälle stimmen.
