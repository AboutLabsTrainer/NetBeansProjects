# 02 – Layouts

Fenster-Trainer aus `Tag02-layouts`: BorderLayout, Mausklicks und Pfeiltasten.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie bilden die Bewegung des Labels und den Statustext der Arbeitsfläche ohne Dialog und ohne Fenster nach.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.fenstertrainer.FensterTrainer`.

```bash
mvn -q -DskipTests compile
```

`ArbeitsflaechePanel` verschiebt das Label um 5 Pixel und setzt den Status `Mausklick bei (x, y)`.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `Position`.

`bewege` kennt `links`, `rechts`, `hoch` und `runter` mit Schrittweite 5. Andere Texte lassen die Position stehen.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `Statuszeile`.

Dieselbe Meldung wie `setStatus` im Panel: `Mausklick bei (x, y)`.

## Abnahme

- Von (50, 50) geht links nach x = 45 und runter nach y = 55.
- Ein Klick bei (12, 34) ergibt `Mausklick bei (12, 34)`.
- Codebeispiel kompiliert ohne Anzeige. Lösungen: `mvn -q test`.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
