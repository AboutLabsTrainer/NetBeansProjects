# 06 Dateien

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

Text wird mit NIO.2 und UTF-8 geschrieben und wieder gezählt. Die zweite Stufe schreibt Personen als CSV und liest dieselben Zeilen zurück.

## Codebeispiel

`Main` startet das Menü der drei Level. Die Beispieldateien liegen unter `data/` relativ zum Ordner `codebeispiel`.

Main-Klasse: `de.ibb.fileio.Main`

Im Ordner `codebeispiel`: `mvn exec:java`

## Stufe 1

`DateiHelfer`

- `zeilen` zählt die Zeilen einer UTF-8-Datei über `Files.lines`.
- `schreibe` legt fehlende Elternordner an und schreibt die Liste neu (vorhandener Inhalt wird ersetzt).
- `lesbar` ist wahr, wenn die Datei existiert und lesbar ist.

Abnahme: die Zeilen `Zeile 1`, `Zeile 2`, `Zeile 3`, `äöü` ergeben die Anzahl `4` und denselben Inhalt beim erneuten Lesen. Die geschriebene Datei ist lesbar. Ein Pfad, den es nicht gibt, ist nicht lesbar.

## Stufe 2

`PersonenDatei` schreibt und liest `Name;Alter;Stadt`. Die erste Zeile ist immer diese Überschrift. `lesePersonen` überspringt die Überschrift und leere Zeilen. Eine Zahl, die sich nicht parsen lässt, wird Alter `0`. Fehlt die Stadt, ist sie `""`.

Abnahme: `Mia;31;Köln` und `Leo;x;Bonn` werden zu Mia/31/Köln und Leo/0/Bonn. `schreibePersonen` und danach `lesePersonen` liefert dieselbe Liste zurück, einschließlich `ä` in einem Namen.

## Abnahme

Die Werte oben, in einem temporären Verzeichnis. Gerüste: `mvn -q -DskipTests compile`, ohne Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
