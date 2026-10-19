# Modul 07 – Buchverwaltung

## Lernziel

Eine Buch-Klasse kapseln und danach mehrere Bücher nach Autor filtern.

Start in der IDE: [docs/ide-maven.md](../../../docs/ide-maven.md). Geöffnet wird der Unterordner mit der `pom.xml`, nicht dieser Elternordner.

## Codebeispiel

`Buch` hält Titel, Autor, Jahr und Preis und kann einen Rabatt anwenden. `BuchApp` legt zwei Bücher an. Main-Klasse: `de.ibb.oop.BuchApp`.

```bash
mvn exec:java
```

## Stufe 1

Ordner `uebung-stufe-1`. Konstruktor und Getter sind vorgegeben. `rabatt` senkt den Preis um den Prozentwert. 10 bei einem Preis von 20 ergibt 18.

Abnahme: `rabatt(10)` auf 20 Euro ergibt 18. `rabatt(15)` auf 49.90 ergibt 42.415.

## Stufe 2

Ordner `uebung-stufe-2`. `Regal` speichert Bücher. `vonAutor` liefert alle Bücher dieses Autors, die Reihenfolge des Hinzufügens bleibt.

Abnahme: Zwei Bücher von Erika und eines von Max: die Suche nach Erika liefert zwei Titel, in der Reihenfolge des Hinzufügens.

## Lösung

`loesung/stufe-1` und `loesung/stufe-2` erst nach dem eigenen Versuch öffnen. Prüfung: `mvn test`.
