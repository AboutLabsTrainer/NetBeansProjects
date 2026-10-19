# 03 – Interfaces und Enums

Mietobjekte aus `04-UebungInterfacesMietobjekte`: ein gemeinsames Interface, eine Default-Methode und ein Enum mit Rabattregel.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie implementieren ein Interface, nutzen eine Default-Methode für die Mehrwertsteuer und legen Rabattregeln in einem Enum ab.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.uebung.interfaces.MietDemo`.

```bash
mvn -q -DskipTests compile
mvn exec:java
```

`Mietbar` bündelt Preis und Beschreibung. `berechneMietpreisMitMwst` ist eine Default-Methode mit `BASIS_MWST`. `Saison` implementiert `RabattRegel`.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md).

`Mietbar` inklusive Default-Methode steht. `Auto.berechneMietpreis` und `Saison.berechneRabattProzent` sind `TODO`. Nebensaison: ab 5 Tagen 10 Prozent. Standard: ab 7 Tagen 5 Prozent. Hauptsaison: kein Rabatt.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `Bohrmaschine`.

Basis 20 EUR pro Tag, ab mehr als 1000 Watt plus 5 EUR, sonst wie im Codebeispiel. `getBeschreibung` ist vorgegeben, der Preis ist offen.

## Abnahme

- Drei Tage Auto zu 59,99 EUR netto sind `179.97`, brutto etwa `214.16`.
- `Saison.NEBENSAISON` gibt ab 5 Tagen 10 Prozent, darunter 0.
- 1200 Watt kosten 25 EUR pro Tag, 800 Watt kosten 20 EUR.
- Lösungen: `mvn -q test`.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
