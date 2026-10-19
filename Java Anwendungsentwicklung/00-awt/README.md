# 00 – AWT

AWT-Farbspiel aus `Tag01-awt`: Fenster, Zeichenfläche und eine Zufallsfarbe.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie begrenzen Farbkanäle auf den Bereich, den die Zufallsfarbe benutzt, und merken sich gesetzte Punkte unabhängig vom Fenster.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.awtfarbspiel.AwtFarbSpiel`.

```bash
mvn -q -DskipTests compile
```

`FarbFenster` mischt Rot, Grün und Blau mit Werten von 0 bis 255. `Zeichenflaeche` sammelt Mauspunkte und kann sie löschen. Swing und AWT kommen aus dem JDK.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `Farbkanal`.

`begrenze` schneidet einen Kanal auf 0 bis 255 zu. `rgb` ruft diese Methode bereits für alle drei Kanäle auf.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `Punktliste`.

Übertragen Sie die Punktliste der Zeichenfläche: hinzufügen, zählen, löschen. Ohne `Canvas` und ohne Fenster.

## Abnahme

- Werte unter 0 werden 0, Werte über 255 werden 255.
- Zwei Punkte zählen 2, nach `loeschen` wieder 0.
- Codebeispiel kompiliert ohne Anzeige. Lösungen: `mvn -q test`.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
