# Debug-Übung

## Was dieses Beispiel übt

Mit dem Debugger einen falschen Rechenschritt finden. Die Fehler sind Absicht und sollen nicht vor dem Debuggen „repariert“ werden.

## Lesen

`DebugUebung`. Zuerst `main`, dann `square` und `sumUpTo`.

## Start in NetBeans

Ant-Projekt. Main-Klasse: `de.ibb.debug.DebugUebung`. Statt F6 einen Haltepunkt auf `return value + value` setzen und mit Debug starten (Strg+F5).

## Ausprobieren

1. Erwartet für den Startwert 5: Quadrat 25, Summe 15. Das Programm liefert andere Zahlen.
2. Schritte einzeln weiter und lies `value` und `sum` im Variablenfenster.
3. `square` addiert statt zu multiplizieren. `sumUpTo` bricht ab, bevor `i` den Wert `n` erreicht.
