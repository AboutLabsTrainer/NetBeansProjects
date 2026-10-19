# 05 Streams

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

`filter`, `map`, `max` und `count` liefern ein neues Ergebnis, die Quelle bleibt stehen. Die zweite Stufe gruppiert nach Abteilung und zieht zusätzlich die Namen der Erwachsenen heraus.

## Codebeispiel

`StreamChallenges` führt die neun Challenges aus. Challenge 1.1 erwartet für 1 bis 10 die Liste `[12, 14, 16, 18, 20]`.

Main-Klasse: `de.ibb.streams.StreamChallenges`

Im Ordner `codebeispiel`: `mvn exec:java`

## Stufe 1

`StreamAufgaben`

- `ueberFuenfVerdoppelt`: Zahlen strikt größer als 5, danach verdoppeln. `1` bis `10` ergibt `[12, 14, 16, 18, 20]`.
- `laengsterName`: `max` nach der Länge. Bei gleicher Länge bleibt der zuerst gesehene Name (`maxBy` behält bei Vergleich 0 den bisherigen). `Max, Anna, Tom, Alexandra, Tim, Christoph` ergibt `Alexandra`. Eine leere Liste ergibt ein leeres `Optional`.
- `beginneMitA`: zählt Namen, die mit dem Zeichen `A` beginnen. `Max, Anna, Tom, Alexandra, Tim, Alfred` ergibt `3`.

## Stufe 2

`PersonenStreams` arbeitet auf `Person(name, alter, abteilung)`.

- `durchschnittAlter` gruppiert nach Abteilung und bildet den Durchschnitt des Alters.
- `erwachseneNamen` behält Alter ab 18, entfernt doppelte Namen und sortiert aufsteigend.

Abnahme für Max/25/IT, Anna/30/HR, Tom/17/IT, Max/25/HR, Lisa/28/HR: IT ist `21.0`, HR ist `83.0 / 3.0`. Die Namen sind `[Anna, Lisa, Max]`.

## Abnahme

Die Werte oben. Gerüste: `mvn -q -DskipTests compile`, ohne Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
