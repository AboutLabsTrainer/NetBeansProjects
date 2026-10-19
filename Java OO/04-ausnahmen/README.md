# 04 – Ausnahmebehandlung

Mietrechner aus `05-UebungAusnahmebehandlung`: geprüfte Exception, `finally` und das Einlesen von `angebote.txt`.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie werfen eine eigene geprüfte Exception für fachliche Regeln und trennen Formatfehler von diesen Regeln.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.uebung.exceptions.MietRechner`.

```bash
mvn -q -DskipTests compile
mvn exec:java
```

`angebote.txt` liegt unter `src/main/resources` und wird über den Klassenpfad geladen (`ClassLoader.getResourceAsStream`), nicht aus dem Arbeitsverzeichnis. Eine fehlende Ressource meldet der Rechner wie eine fehlende Datei. Die `ArrayIndexOutOfBoundsException` in `provoziereRuntimeException` ist Absicht und bleibt im Beispiel stehen. `mvn exec:java` liest Tagespreis und Tage von der Konsole; das Kompilieren braucht keine Eingabe.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md).

`UngueltigeMietdauerException` steht. `Mietpreis.berechne` prüft dieselben drei Regeln wie `MietRechner.berechneMietpreis`, mit denselben Meldungen. Die Methode ist `TODO`.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Record `Angebotszeile`.

Übertragen Sie das Zerlegen einer Zeile `Bezeichnung;Preis;Tage`. Falsche Spaltenzahl oder ein schlechtes Zahlenformat ergeben eine `IllegalArgumentException`. Die Datei selbst öffnen Sie hier nicht.

## Abnahme

- Drei Tage zu 49,99 EUR ergeben `149.97`.
- `tage <= 0`, `tage > 30` und ein negativer Preis werfen `UngueltigeMietdauerException` mit der Meldung aus dem Beispiel.
- `Auto;49.99;3` wird gelesen, `KaputterEintrag;xx;3` nicht.
- Lösungen: `mvn -q test` ohne Konsoleingabe.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
