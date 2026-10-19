# Modul 04 – Ablaufsteuerung

## Lernziel

Verzweigungen und eine Bewertung ohne Scanner formulieren.

Start in der IDE: [docs/ide-maven.md](../../../docs/ide-maven.md). Geöffnet wird der Unterordner mit der `pom.xml`, nicht dieser Elternordner.

## Codebeispiel

`AblaufsteuerungDemo` prüft eine Note, nennt den Wochentag per `switch` und lässt eine Geheimzahl raten. Main-Klasse: `de.ibb.flow.AblaufsteuerungDemo`.

```bash
mvn exec:java
```

## Stufe 1

Ordner `uebung-stufe-1`. `Steuerung.notenText` liefert für 1 und 2 `Sehr gut / Gut`, für 3 und 4 `Ausreichend`, für 5 und 6 `Nicht bestanden`, sonst `Ungültige Note`. `wochentag` nennt Montag bis Sonntag für 1 bis 7, sonst `Ungültiger Tag`.

Abnahme: Note 1 ergibt `Sehr gut / Gut`, Note 4 `Ausreichend`, Note 6 `Nicht bestanden`, Note 0 `Ungültige Note`. Tag 1 ist `Montag`, Tag 7 `Sonntag`.

## Stufe 2

Ordner `uebung-stufe-2`. `Ratspiel.hinweis` vergleicht Tipp und Geheimzahl und liefert `zu klein`, `zu gross` oder `getroffen`.

Abnahme: Geheimzahl 4: Tipp 2 ist `zu klein`, Tipp 6 ist `zu gross`, Tipp 4 ist `getroffen`.

## Lösung

`loesung/stufe-1` und `loesung/stufe-2` erst nach dem eigenen Versuch öffnen. Prüfung: `mvn test`.
