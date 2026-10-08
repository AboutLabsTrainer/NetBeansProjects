# Tag 4 – Komplexe Swing-Komponenten

## Was dieses Beispiel übt

`JTable` in drei Stufen: eine einfache Tabelle, eine Tabelle mit Anlegen, Ändern und Löschen, und ein eigenes `TableModel` für typisierte Spalten inklusive Checkbox.

## Klassen

In dieser Reihenfolge lesen:

1. `Level1TableDemo` – Tabelle, Sortierung, Auswahl in der Konsole
2. `CrudTableDemo` – Zeilen anlegen, ändern, löschen
3. `Buch` – die Daten einer Zeile im eigenen Model
4. `BuchTableModel` – Spalten, Typen, welche Zellen editierbar sind
5. `CustomTableModelDemo` – die Tabelle mit dem eigenen Model
6. `Tag4Launcher` – öffnet die drei Demos über Buttons

## Start in NetBeans

F6 startet `de.ibb.tag4.Tag4Launcher` (`main`). Es öffnet sich ein kleines Fenster mit drei Buttons, kein Server. Jede Demo hat zusätzlich ein eigenes `main` (Rechtsklick auf die Klasse, Datei ausführen).

## Ausprobieren

1. Level 1 öffnen, eine Zeile anklicken und einen Spaltenkopf zum Sortieren benutzen. Die Auswahl erscheint in der Konsole.
2. Level 2: eine Zeile anlegen, die Auswahl ändern und wieder löschen.
3. Level 3: Preis und die Spalte „Gelesen“ direkt in der Tabelle ändern.
