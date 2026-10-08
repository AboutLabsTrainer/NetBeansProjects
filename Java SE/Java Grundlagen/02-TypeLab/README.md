# TypeLab: Werttypen und Referenztypen

## Was dieses Beispiel übt

Die acht primitiven Typen kopieren den Wert. Referenztypen kopieren die Adresse des Objekts. `String` ist ein Sonderfall, weil ein String nach dem Erzeugen nicht verändert wird.

## In dieser Reihenfolge lesen

1. `PrimitiveDemo` – die acht Typen, Wertkopie, `final`
2. `ReferenceDemo` – `String` und `java.awt.Point`

## Start in NetBeans

Ant-Projekt. Main-Klassen: `de.ibb.typelab.PrimitiveDemo` und `de.ibb.typelab.ReferenceDemo`. Jeweils unter Run als Main Class setzen und mit F6 starten.

## Ausprobieren

1. Starte `PrimitiveDemo` und lies die Zeilen vor und nach `cityPopulation = cityPopulation + 1_000`. Die Kopie bleibt stehen.
2. Starte `ReferenceDemo`. Nach `greeting = "..."` zeigt `alias` noch auf den alten Text.
3. Dieselbe Klasse: nach `p1.x = 99` ändert sich auch `p2`, weil beide auf dasselbe `Point`-Objekt zeigen.
