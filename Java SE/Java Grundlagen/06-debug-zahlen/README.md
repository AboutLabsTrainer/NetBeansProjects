# Modul 06 – Zahlen auswerten

## Lernziel

Schleifengrenzen und den Startwert eines Maximums korrigieren. Das Codebeispiel stürzt absichtlich über die Array-Grenze.

Start in der IDE: [docs/ide-maven.md](../../../docs/ide-maven.md). Geöffnet wird der Unterordner mit der `pom.xml`, nicht dieser Elternordner.

## Codebeispiel

`Zahlenauswerter` läuft mit `{5, 8, -3, 10, 42}` absichtlich falsch: Index ab 1, Maximum startet bei 0, Durchschnitt ist ganzzahlig. `ZahlenauswerterLoesung` ist die bereits korrigierte Fassung zum Vergleich nach dem Debuggen. Main-Klasse: `de.ibb.debugchallenge.Zahlenauswerter`.

```bash
mvn exec:java
```

## Stufe 1

Ordner `uebung-stufe-1`. `Auswerter` liefert Summe, Maximum, Durchschnitt und den Index einer gesuchten Zahl. Nicht gefundene Werte ergeben `-1`. Das Maximum darf nicht bei 0 starten.

Abnahme: Summe 62, Maximum 42, Durchschnitt 12.4, Index von 10 ist 3, Index von 99 ist -1. Für `{-8, -3, -1}` ist das Maximum -1.

## Stufe 2

Ordner `uebung-stufe-2`. `Spanne.differenz` ist Maximum minus Minimum. Weniger Vorgabe, nur die Methode.

Abnahme: `{5, 8, -3, 10, 42}` ergibt 45. `{-3, -1, -8}` ergibt 7.

## Lösung

`loesung/stufe-1` und `loesung/stufe-2` erst nach dem eigenen Versuch öffnen. Prüfung: `mvn test`.
