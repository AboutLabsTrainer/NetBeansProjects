# Modul 01 – Typen

## Lernziel

Primitive Werte kopieren und einen veränderlichen Kontostand über eine Referenz ändern.

Start in der IDE: [docs/ide-maven.md](../../../docs/ide-maven.md). Geöffnet wird der Unterordner mit der `pom.xml`, nicht dieser Elternordner.

## Codebeispiel

`PrimitiveDemo` zeigt die acht primitiven Typen und dass eine Zuweisung den Wert kopiert. `ReferenceDemo` zeigt, dass zwei `Point`-Variablen dasselbe Objekt meinen. Main-Klasse: `de.ibb.typelab.PrimitiveDemo`.

```bash
mvn exec:java
```

## Stufe 1

Ordner `uebung-stufe-1`. `Schnitte.alsInt` schneidet die Nachkommastellen ab, ohne zu runden (`(int)`). `Schnitte.summe` addiert ein `int` und ein `long` und gibt `long` zurück.

Abnahme: `alsInt(19.99)` ist 19. `summe(350000, 384400L)` ist 734400.

## Stufe 2

Ordner `uebung-stufe-2`. `Konto` ist eine eigene Klasse. `einzahlen` erhöht den Stand. `abheben` zieht nur ab, wenn der Stand reicht, und liefert sonst `false`, ohne den Stand zu ändern.

Abnahme: Start 100, einzahlen 25 ergibt 125. Abheben von 200 bleibt 125 und liefert false. Abheben von 25 ergibt 100 und liefert true.

## Lösung

`loesung/stufe-1` und `loesung/stufe-2` erst nach dem eigenen Versuch öffnen. Prüfung: `mvn test`.
