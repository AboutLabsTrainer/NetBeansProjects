# Debugging-Challenge: Zahlenauswerter

## Was dieses Beispiel übt

Dieselbe Idee wie die Debug-Übung, an einem Array: falsche Schleifengrenzen, ein Maximum, das bei 0 startet, und eine ganzzahlige Division.

## In dieser Reihenfolge lesen

1. `Zahlenauswerter` – die fehlerhafte Vorlage
2. `ZahlenauswerterLoesung` – erst nach dem eigenen Debug-Lauf

## Start in NetBeans

Ant-Projekt. Zum Üben `de.ibb.debugchallenge.Zahlenauswerter` als Main Class. Die Lösung startet über `de.ibb.debugchallenge.ZahlenauswerterLoesung`.

## Ausprobieren

1. Starte die Vorlage im Debugger. Der erste Absturz sitzt in `values[i]`, weil die Schleife bei 1 beginnt und bis `length` läuft.
2. Werte im Kopf: Summe 62, Maximum 42, Durchschnitt etwa 12,4, Index von 10 ist 3.
3. Vergleiche danach jede korrigierte Zeile in der Lösung mit der Vorlage.
