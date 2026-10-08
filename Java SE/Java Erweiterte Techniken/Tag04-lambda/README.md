# Shop-Lambda-Demo

## Was dieses Beispiel übt

Lambdas als kleine Verhaltensbausteine: eine eigene funktionale Schnittstelle für Preise und die Standardtypen `Consumer`, `Predicate`, `Function` und `Supplier`.

## Die wichtigen Klassen, in der Reihenfolge, in der man sie lesen sollte

1. `Product` – Name, Preis und Kategorie
2. `PriceOperation` – genau eine abstrakte Methode, deshalb als Lambda nutzbar
3. `LambdaApp` – Rabatt, Mehrwertsteuer, Filter, Umwandlung in Text und fortlaufende Bestellnummern

## Start in NetBeans

Konsolenprogramm, kein Payara. Main-Klasse: `de.ibb.lambda.LambdaApp`. Projekt öffnen und mit F6 starten.

## Zwei oder drei Schritte zum Ausprobieren

1. F6 drücken und am Java-Buch nachrechnen: Originalpreis, 19 % MwSt., 10 % Rabatt, danach Versand.
2. In der Produktliste einen weiteren Artikel anlegen und prüfen, wo er in der schönen Ausgabe, im Filter und in den Textzeilen auftaucht.
3. Die Grenze in `teuer` von 50 Euro auf einen anderen Wert setzen und die beiden Filterblöcke erneut lesen.
