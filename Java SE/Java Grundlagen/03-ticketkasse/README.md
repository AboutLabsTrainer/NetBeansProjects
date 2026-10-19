# Modul 03 – Ticketkasse

## Lernziel

Preise aus dem Alter ableiten und danach Rabattregeln anwenden, ohne die Eingabe mit der Rechnung zu vermischen.

Start in der IDE: [docs/ide-maven.md](../../../docs/ide-maven.md). Geöffnet wird der Unterordner mit der `pom.xml`, nicht dieser Elternordner.

## Codebeispiel

`TicketKasse` fragt per Menü Alter ab. Unter 14 kostet ein Ticket 5 Euro, ab 14 und unter 65 kostet es 10 Euro, ab 65 kostet es 7 Euro. Ein Rabatttag multipliziert mit 0,8. Main-Klasse: `de.ibb.controlflow.TicketKasse`.

```bash
mvn exec:java
```

## Stufe 1

Ordner `uebung-stufe-1`. `PreisRechner.preisFuerAlter` und `mitRabatt` enthalten nur die Rechnung. Die Grenzen und der Faktor 0,8 stehen als Kommentar über den Methoden.

Abnahme: Alter 13 ergibt 5, 14 und 64 ergeben 10, 65 ergibt 7. `mitRabatt(10, true)` ist 8, ohne Rabatttag bleibt 10.

## Stufe 2

Ordner `uebung-stufe-2`. `KassenSumme.gesamt` nimmt Anzahl, Einzelpreis und Rabatttag. Ab 5 Tickets gibt es nach dem Tagesrabatt noch 10 Prozent Gruppenrabatt.

Abnahme: 5 Tickets zu 10 Euro ohne Rabatttag kosten 45. Dieselben 5 Tickets am Rabatttag kosten 36.

## Lösung

`loesung/stufe-1` und `loesung/stufe-2` erst nach dem eigenen Versuch öffnen. Prüfung: `mvn test`.
