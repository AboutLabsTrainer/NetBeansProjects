# Übung: Fahrzeugtypen, Konstruktoren und Casts

## Was dieses Beispiel übt

Eine kleine Fuhrpark-Hierarchie (`Fahrzeug`, `Pkw`, `Lkw`) und was mit Typen passiert, wenn Objekte und primitive Werte die Form wechseln: Upcast in ein `Fahrzeug[]`, `instanceof`, Downcast, expliziter Cast von `int` nach `short`, Auto-Boxing und Auto-Unboxing.

## In dieser Reihenfolge lesen

1. `Fahrzeug` – gemeinsame Felder, Konstruktor, `druckeDaten()`
2. `Pkw` und `Lkw` – `super(...)` und überschriebene Ausgabe
3. `FahrzeugDemo` – die drei Stufen in `main`

## Start in NetBeans

Ant-Projekt. Main-Klasse: `de.ibb.uebung.fahrzeuge.FahrzeugDemo`. Mit F6 starten. Ausgabe nur auf der Konsole, kein Server.

## Ausprobieren

1. Starte `FahrzeugDemo` und vergleiche die Ausgabe von `druckeDaten()` für ein einzelnes Objekt mit dem Aufruf über das `Fahrzeug[]`.
2. Setze einen Haltepunkt in den `instanceof`-Zweig und prüfe, welcher Laufzeittyp in `f` steckt.
3. Schau dir die auskommentierte Zeile `Long l1 = 42` an und lies den Kommentar daneben: warum das Literal erst nach `long` gecastet werden muss.
