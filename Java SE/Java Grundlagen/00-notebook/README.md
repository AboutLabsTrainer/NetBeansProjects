# Modul 00 – Erstes Programm

## Lernziel

Ein minimales Java-Programm, Variablen und eine Berechnung mit `final` lesen und selbst formulieren.

Start in der IDE: [docs/ide-maven.md](../../../docs/ide-maven.md). Geöffnet wird der Unterordner mit der `pom.xml`, nicht dieser Elternordner.

## Codebeispiel

`JavaNotebook` gibt Personendaten, einen Cast und einen Kreisumfang aus. `MinimalProgram` ist das kleinstmögliche Programm. Main-Klasse: `de.ibb.notebook.JavaNotebook`.

```bash
mvn exec:java
```

## Stufe 1

Ordner `uebung-stufe-1`. In `Profil` die Methode `beschreibung` so füllen, dass sie `Name: Petra, Alter: 30` liefert, wenn Name und Alter so gesetzt sind. `kreisUmfang` rechnet `2 * 3.14159 * radius`. Die Konstante bleibt `final`.

Abnahme: `beschreibung` für Petra/30 ist exakt `Name: Petra, Alter: 30`. `kreisUmfang(5)` ist `31.4159`.

## Stufe 2

Ordner `uebung-stufe-2`. `Temperatur` hat weniger Vorgabe. `celsiusNachFahrenheit` rechnet `celsius * 9 / 5 + 32`. `kategorie` liefert `kalt` unter 0, `mild` von 0 bis unter 20, sonst `warm`.

Abnahme: 0 °C wird 32 °F, 100 °C wird 212 °F. −1 ist `kalt`, 10 ist `mild`, 25 ist `warm`.

## Lösung

`loesung/stufe-1` und `loesung/stufe-2` erst nach dem eigenen Versuch öffnen. Prüfung: `mvn test`.
