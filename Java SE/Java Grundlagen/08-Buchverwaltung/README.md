# Buchverwaltung

## Was dieses Beispiel übt

Eine erste eigene Klasse: private Felder, zwei Konstruktoren, Zugriff über Getter und Setter, eine fachliche Methode `rabatt`.

## In dieser Reihenfolge lesen

1. `Buch` – Zustand und Verhalten
2. `BuchApp` – drei Arten, ein Buch anzulegen

## Start in NetBeans

Ant-Projekt. Main-Klasse: `de.ibb.oop.BuchApp`. Mit F6 starten. Ausgabe nur auf der Konsole.

## Ausprobieren

1. Starte `BuchApp` und lies den Preis von „Java ist auch eine Insel“ vor und nach `rabatt(15)`.
2. Das dritte Buch entsteht mit dem Standardkonstruktor. Vor den Settern stehen die Default-Werte „Unbekannt“ und 0.
3. Setze einen Haltepunkt in `rabatt` und prüfe, warum `this.preis` und nicht eine lokale Kopie geändert wird.
