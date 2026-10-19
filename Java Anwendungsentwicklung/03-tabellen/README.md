# 03 – Tabellen

JTable-Demos aus `Tag04-swing`. Eine eigene Nummer schließt die Lücke, die durch den fehlenden Tag 03 entstanden ist.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie lesen eine Buchzeile spaltenweise wie `BuchTableModel` und übernehmen einen Preis mit Komma oder Punkt.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.tag4.Tag4Launcher`.

```bash
mvn -q -DskipTests compile
```

Das alte Projekt hat mit dem JDK-Swing kompiliert, ohne zusätzliche JavaFX-Bibliothek. Release bleibt 21. `BuchTableModel` mappt Titel, Autor, Jahr, Preis und Gelesen.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `BuchZeile`.

`wert` liefert dieselbe Spalte wie `getValueAt`: 0 Titel, 1 Autor, 2 Jahr, 3 Preis, 4 Gelesen.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `PreisLeser`.

Wie `setValueAt` für den Preis: Komma wird zum Punkt. Ein unlesbarer Text lässt den bisherigen Preis stehen.

## Abnahme

- Spalte 0 ist der Titel, Spalte 4 der Wahrheitswert `gelesen`.
- `12,5` wird 12,5; `abc` lässt den alten Preis stehen.
- Codebeispiel kompiliert ohne Anzeige. Lösungen: `mvn -q test`.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
