# 03 Lambda

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

Eine eigene funktionale Schnittstelle rechnet einen Preis um. `Predicate` filtert Artikel, ein `Supplier` zieht Bestellnummern. Die zweite Stufe hängt eine weitere Preisregel und einen Kategoriefilter daran, ohne die erste Pipeline zu kopieren.

## Codebeispiel

`LambdaApp` rechnet Mehrwertsteuer, Rabatt und Versand für das Java-Buch und filtert die Produktliste.

Main-Klasse: `de.ibb.lambda.LambdaApp`

Im Ordner `codebeispiel`: `mvn exec:java`

Artikel im Beispiel: Java-Buch 29.90 Buch, Monitor 27" 199.00 Elektronik, Kaffeemaschine 79.90 Haushalt, Maus 19.99 Elektronik, Roman 12.50 Buch.

## Stufe 1

`PreisRechner` hat genau eine Methode `anwenden(double)`. `PreisPipeline` nutzt sie.

- `rabattDannMwst`: zuerst 10 Prozent Rabatt (`* 0.90`), danach 19 Prozent MwSt. (`* 1.19`).
- `mitVersand`: dasselbe und dann `+ 4.95`.
- `filtern` behält die Artikel, für die das `Predicate` gilt, in der alten Reihenfolge.
- `bestellnummern(start, anzahl)` liefert `anzahl` Nummern ab `start`, erzeugt über einen `Supplier`.

Abnahme für 29.90: Rabatt dann MwSt. ist `29.90 * 0.90 * 1.19`, mit Versand zusätzlich `4.95`. Teurer als 50 Euro sind nur Monitor 27" und Kaffeemaschine. Teure Bücher gibt es in der Liste keine. `bestellnummern(1000, 5)` ist `1000, 1001, 1002, 1003, 1004`.

## Stufe 2

`ShopRegeln.elektronikNamen` liefert die Namen der Kategorie `Elektronik`, Groß- und Kleinschreibung egal. `skontoWennGross` gibt zuerst 10 Prozent Rabatt. Liegt das Ergebnis bei mindestens 50, kommen weitere 2 Prozent Skonto (`* 0.98`) dazu, sonst nicht.

Abnahme: Elektroniknamen sind `Monitor 27"` und `Maus`. `skontoWennGross(199)` ist `199 * 0.90 * 0.98`. `skontoWennGross(29.90)` ist `29.90 * 0.90`.

## Abnahme

Die Werte oben. Gerüste: `mvn -q -DskipTests compile`, ohne Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
