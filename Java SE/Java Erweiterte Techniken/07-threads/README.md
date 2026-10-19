# 07 Threads

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

Mehrere Aufgaben laufen parallel, das Ergebnis ist danach eine Zahl. Die zweite Stufe lässt zwei Threads gerade und ungerade Zahlen zählen und wartet mit einem `CountDownLatch`, bis beide fertig sind.

## Codebeispiel

`App` zeigt Annotationen, Zähl-Threads, einen Pool und Producer/Consumer. Die Pausen gehören zum Beispiel.

Main-Klasse: `de.ibb.tag08.App`

Im Ordner `codebeispiel`: `mvn exec:java`

## Stufe 1

`BereichsSumme.summe(von, bisExklusiv, teile)` verteilt den Bereich `[von, bisExklusiv)` auf `teile` Aufgaben. Jede Aufgabe ist ein `Callable` und liefert ihre Teilsumme. `invokeAll` sammelt die Teile. Die Summe der Teile ist das Ergebnis.

Abnahme: `summe(1, 101, 4)` ist `5050` (1 bis 100). `summe(1, 1, 2)` ist `0`.

## Stufe 2

`DoppelArbeit.geradePlusUngerade(n)` startet zwei Threads für die Zahlen `1` bis `n` einschließlich. Einer addiert die geraden, einer die ungeraden Werte. Ein `CountDownLatch` mit dem Zähler 2 gibt den Aufrufer frei, wenn beide `countDown` erreicht haben. Das Ergebnis ist die Summe beider Teile.

Abnahme: `geradePlusUngerade(10)` ist `55`. `geradePlusUngerade(1)` ist `1`.

## Abnahme

Die Summen oben, ohne `Thread.sleep`. Gerüste: `mvn -q -DskipTests compile`, ohne Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
