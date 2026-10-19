# 04 Funktionen

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

`Predicate`, `Function` und ihre Verkettung ersetzen verschachtelte Ifs. Die zweite Stufe rechnet mit zwei Eingaben (`BiFunction`) und einer Preisspanne.

## Codebeispiel

`MiniChallenge` zeigt leer/lang, `and`/`or` und `trim` vor `toUpperCase`.

Main-Klasse: `de.ibb.MiniChallenge`

Im Ordner `codebeispiel`: `mvn exec:java`

## Stufe 1

`TextRegeln` baut die Prüfungen aus den Standardtypen, nicht aus einer eigenen If-Kaskade.

- `leerOderLang`: leer **oder** länger als 10 Zeichen.
- `gueltig`: nicht leer **und** höchstens 10 Zeichen.
- `trimUndGross`: zuerst `trim`, dann Großbuchstaben.
- `laengeAlsText`: zuerst `trim`, dann die Länge, dann der Text `Länge: ` plus Zahl.

Abnahme: `""` ist leer oder lang und nicht gültig. `"Hallo"` ist weder leer noch lang, aber gültig. `"Hallo Welt, das ist ein langer Text"` ist leer oder lang und nicht gültig. `" hallo welt "` wird `"HALLO WELT"`. `"  hallo  "` wird `"Länge: 5"`.

## Stufe 2

`PreisFunktionen` kopiert die String-Pipeline nicht.

- `gesamt(preis, menge)` ist das Produkt, gebildet mit einer `BiFunction`.
- `istMittel` gilt von 20 bis 80 einschließlich.
- `betragText` hängt an das Produkt die Formatierung `%.2f EUR` mit `Locale.US`.

Abnahme: `gesamt(19.99, 3)` ist `19.99 * 3`. `betragText(19.99, 3)` ist `59.97 EUR`. Mittel sind 20, 50, 79.90 und 80. Nicht mittel sind 19.99, 80.01 und 199.

## Abnahme

Die Werte oben. Gerüste: `mvn -q -DskipTests compile`, ohne Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
