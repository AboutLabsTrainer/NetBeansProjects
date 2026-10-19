# 07 JSTL

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Die Warenkorbseite rechnet Zeilenbetrag, Steuer und Versand in Java. Die JSP gibt die Werte mit JSTL aus, ohne Scriptlet.

## Codebeispiel

- Context-Pfad: `/Tag8`
- URL: http://localhost:8080/Tag8/ und http://localhost:8080/Tag8/cart
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`PriceCalc.line` multipliziert Preis und Menge und rundet auf zwei Nachkommastellen, `HALF_UP`.

## Stufe 2

`PriceCalc.freeShipping` ist wahr, wenn der Gesamtbetrag größer als 50 ist. 50 selbst bleibt kostenpflichtig. `null` ist kein Freibetrag.

## Abnahme

- Stufe 1: 1,25 mal 4 ist 5,00. 0,1 mal 3 ist 0,30.
- Stufe 2: 50,00 nein, 50,01 ja.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
