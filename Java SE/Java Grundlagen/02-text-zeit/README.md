# Modul 02 – Text und Zeit

## Lernziel

Namen aus einem String schneiden und einen Termin mit der Date-Time-API verschieben und formatieren.

Start in der IDE: [docs/ide-maven.md](../../../docs/ide-maven.md). Geöffnet wird der Unterordner mit der `pom.xml`, nicht dieser Elternordner.

## Codebeispiel

`StringWorkshop` trennt Vor- und Nachname und vergleicht Strings mit `equals`. `DateTimeWorkshop` rechnet Perioden und formatiert `15.03.2026 09:30`. Main-Klasse: `de.ibb.workshop.StringWorkshop`.

```bash
mvn exec:java
```

## Stufe 1

Ordner `uebung-stufe-1`. `NamensTeile.vorname` und `nachname` trennen an dem ersten Leerzeichen. `enthaelt` prüft, ob der Text die Suche enthält.

Abnahme: `Max Mustermann` wird zu `Max` und `Mustermann`. `Java macht Spaß` enthält `Java`.

## Stufe 2

Ordner `uebung-stufe-2`. `Termin.beginn` baut ein `LocalDateTime`. `erinnerung` liegt 30 Minuten davor. `deutsch` formatiert `dd.MM.yyyy HH:mm`.

Abnahme: 15.03.2026 09:30, Erinnerung 15.03.2026 09:00, Text `15.03.2026 09:30`.

## Lösung

`loesung/stufe-1` und `loesung/stufe-2` erst nach dem eigenen Versuch öffnen. Prüfung: `mvn test`.
