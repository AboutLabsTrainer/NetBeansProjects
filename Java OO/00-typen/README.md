# 00 – Typen, Konstruktoren und Casts

Fuhrpark aus der früheren Übung `01-UebungFahrzeugeTypen`: Upcast in ein `Fahrzeug[]`, Downcast und der Cast von primitiven Werten.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie unterscheiden Upcast, `instanceof` und Downcast von einem expliziten Cast zwischen primitiven Typen und von Auto-Boxing.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.uebung.fahrzeuge.FahrzeugDemo`.

```bash
mvn -q -DskipTests compile
mvn exec:java
```

Lesen Sie `Fahrzeug`, `Pkw`, `Lkw` und danach `FahrzeugDemo`. Die auskommentierte Zuweisung `Long l1 = 42` bleibt stehen: ein `int`-Literal wird nicht automatisch zu `Long`.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `MietpreisRechner`.

`alsShort` castet ein `int` explizit nach `short`, so wie `tageAlsShort` in der Demo. `berechneMietpreis` multipliziert Tagessatz und Tage. Gerüst und Signaturen stehen, die beiden Methoden sind `TODO`. Das Projekt kompiliert und hat keine prüfenden Tests.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `WrapperTage`.

Übertragen Sie die `Long`-Zeile der Demo auf eine Methode: aus dem `int` wird zuerst ein `long`, danach ein `Long`. Weniger Gerüst als in Stufe 1.

## Abnahme

- Codebeispiel startet `FahrzeugDemo` auf der Konsole.
- `berechneMietpreis(49.99, (short) 5)` ergibt `249.95`.
- `alsLong(42)` ergibt den Wrapper `42`.
- Lösungen: `mvn -q test` ohne Datenbank und ohne Bildschirm.

## Lösung erst nach eigenem Versuch

Erst öffnen, wenn Stufe 1 und Stufe 2 selbst laufen: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
