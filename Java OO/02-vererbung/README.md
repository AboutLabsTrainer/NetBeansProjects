# 02 – Vererbung und Polymorphie

Fuhrpark aus `03-UebungFahrzeugeVererbung`: `Pkw` und `Lkw` erweitern `Fahrzeug` und überschreiben die Ausgabe.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie schreiben `extends` und `super(...)`, überschreiben eine Methode und nutzen den Basistyp für unterschiedliche Laufzeittypen.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.uebung.fahrzeuge.FahrzeugDemo`.

```bash
mvn -q -DskipTests compile
mvn exec:java
```

`druckeDaten` der Unterklasse ruft `super.druckeDaten()` auf. Das Array `Fahrzeug[]` speichert beide Unterklassen; `instanceof` entscheidet den Downcast.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md). `Fahrzeug.basis()` liefert die beiden Zeilen, die `druckeDaten` im Beispiel druckt. In `Pkw.beschreibung()` hängen Sie Überschrift und Türzahl an. Die Methode ist `TODO` und gibt den Text zurück, damit sie ohne Konsole prüfbar ist.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md). Dieselbe Form für `Lkw`: Überschrift `=== LKW ===` und die Zeile `max. Ladung: … t`. Weniger Kommentare als in Stufe 1.

## Abnahme

- Ein `Pkw` mit Kennzeichen `B-AB 1234`, Baujahr 2015 und 5 Türen nennt diese drei Angaben.
- Ein `Lkw` nennt Kennzeichen, Baujahr und Zuladung.
- Lösungen: `mvn -q test`.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
