# 01 – Swing

Swing-Farbmischer aus `Tag01-swing`: drei Regler setzen Rot, Grün und Blau.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie rechnen aus den drei Sliderwerten eine Farbe, ohne das Fenster zu öffnen.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.swingfarbmischer.SwingFarbMischer`.

```bash
mvn -q -DskipTests compile
```

`FarbMischerFrame.updateColor` liest die drei Slider von 0 bis 255. Swing liegt im JDK, zusätzliche Bibliotheken braucht das Beispiel nicht.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `RgbMischung`.

`hex` begrenzt jeden Kanal auf 0 bis 255 und liefert `#RRGGBB` in Großbuchstaben.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `Helligkeit`.

Eine kleine Übertragung der Vorschau: dunkel, wenn `0,299·R + 0,587·G + 0,114·B` kleiner als 128 ist.

## Abnahme

- `hex(255, 0, 15)` ist `#FF000F`.
- Schwarz ist dunkel, Weiß nicht.
- Codebeispiel kompiliert ohne Anzeige. Lösungen: `mvn -q test`.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
