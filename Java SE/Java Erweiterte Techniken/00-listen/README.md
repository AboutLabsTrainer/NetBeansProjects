# 00 Listen

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

Eine Aufgabe liegt entweder in der offenen oder in der erledigten Liste. In der zweiten Stufe kommt eine Sortierung nach Priorität dazu, und dieselbe Beschreibung darf offen nur einmal vorkommen.

## Codebeispiel

Das Beispiel ist die ToDo-Mini-Challenge mit `ArrayList`, Priorität und einer eigenen `SimpleArrayList`.

Main-Klasse: `de.ibb.collections.todo.ui.TodoAppConsole`

Im Ordner `codebeispiel`: `mvn exec:java`

## Stufe 1

Bauen Sie `de.ibb.listen.AufgabenListe` mit zwei Listen.

- `hinzufuegen` hängt den Text an die offenen Aufgaben.
- `erledigen(index)` verschiebt den Eintrag an diesem Index von offen nach erledigt und liefert `true`. Ein ungültiger Index liefert `false` und ändert nichts.
- `entfernen(index)` löscht nur aus der offenen Liste.
- `offene` und `erledigte` liefern Kopien, keine live-Sicht auf die internen Listen.

Abnahme an diesem Ablauf: `hinzufuegen("Java-Artikel lesen")`, `hinzufuegen("Mini-Challenge nachbauen")`, `erledigen(0)`. Offen ist danach genau `[Mini-Challenge nachbauen]`, erledigt genau `[Java-Artikel lesen]`. `erledigen(9)` bleibt `false`. `entfernen(0)` leert die offene Liste, `entfernen(0)` danach ist `false`. Erledigt bleibt `[Java-Artikel lesen]`.

## Stufe 2

`PrioritaetsListe` ist keine zweite Kopie der offenen/erledigten Liste. `hinzufuegen(text, prioritaet)` nimmt denselben Text kein zweites Mal an und liefert dann `false`. `sortiert` liefert die offenen Texte, `HIGH` vor `MEDIUM` vor `LOW`, bei gleicher Priorität in der Einfügereihenfolge. `anzahl` zählt nur die offenen Einträge einer Priorität.

Abnahme: `Bug/HIGH`, `Doku/LOW`, danach noch einmal `Bug/MEDIUM` wird abgelehnt. `sortiert` ist `[Bug, Doku]`. Nach `Test/MEDIUM` ist `sortiert` `[Bug, Test, Doku]`. `anzahl` ist für jede der drei Prioritäten `1`.

## Abnahme

Stufe 1 und Stufe 2 erfüllen die Werte oben. Die Gerüste kompilieren mit `mvn -q -DskipTests compile`. Es gibt dort keine Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
