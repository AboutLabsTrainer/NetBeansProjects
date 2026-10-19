# 01 – Statische Elemente

Fuhrpark-Statistik aus `02-UebungFahrzeugeStatic`: ein Zähler für alle Objekte und der Bruttopreis über die Konstante `MWST`.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie trennen Instanzfelder von `static`-Zählern und `static`-Methoden und nutzen eine `static final`-Konstante in der Preisformel.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.uebung.fahrzeuge.FahrzeugDemo`.

```bash
mvn -q -DskipTests compile
mvn exec:java
```

Der Konstruktor erhöht `anzahlFahrzeuge`. `miete` und `rueckgabe` ändern `anzahlVermietet` nur beim tatsächlichen Wechsel. `berechneMietpreisBrutto` gibt es als Instanzmethode und als statische Methode.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `Mietwagen`.

Der Zähler im Konstruktor steht bereits. Beide `berechneMietpreisBrutto`-Methoden sind `TODO`: Netto mal `(1 + MWST)`, bei `tage <= 0` der Wert `0`.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `Werkzeug`.

Übertragen Sie `miete` und `rueckgabe` auf ein Werkzeug. Ein zweiter Aufruf darf den statischen Zähler nicht noch einmal ändern.

## Abnahme

- Drei Tage zu 49,99 EUR brutto sind etwa `178.46`.
- Fünf Tage zu 39,99 EUR über die statische Methode sind etwa `237.94`.
- Zwei `Mietwagen` erhöhen den Zähler um 2.
- Zweimal `miete` am selben Werkzeug zählt einmal.
- Lösungen: `mvn -q test`.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
