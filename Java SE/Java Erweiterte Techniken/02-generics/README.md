# 02 Generics

IDE und Maven: [../../../docs/ide-maven.md](../../../docs/ide-maven.md)

## Lernziel

Eine Sammlung nennt ihren Elementtyp. Summe und Filter brauchen dann keinen Cast. Die zweite Stufe trennt ein Paar in zwei Typparameter und summiert eine Liste von `Number`.

## Codebeispiel

`BestellServiceLegacy` lässt Raw Types und Casts absichtlich stehen. `BestellService` ist die typsichere Fassung. Das Beispiel bleibt in diesem Zustand. Die Übung baut das typsichere Buch neu.

Main-Klasse: `de.ibb.BestellServiceTest`

Im Ordner `codebeispiel`: `mvn exec:java`

## Stufe 1

`BestellBuch` nimmt nur `Posten`. `gesamtwert` addiert die Preise. `ueber(minWert)` liefert die Posten, deren Preis **strikt größer** als `minWert` ist, in der Einfügereihenfolge.

Abnahme: Preise 100, 200 und 300 ergeben `600.0`. Preise 10, 50 und 100, gefiltert mit `40`, liefern zwei Posten: Mittel 50 und Teuer 100. Ein leeres Buch hat den Gesamtwert `0`.

## Stufe 2

`Paar<A,B>` merkt sich zwei Werte. `tauschen` liefert ein neues Paar mit vertauschten Seiten, das alte Paar bleibt. `ZahlenListe<T extends Number>` hat `add`, `summe` als `double` und `maximum`. Die leere Liste hat das Maximum `null`.

Abnahme: `Paar("id", 7).tauschen()` hat zuerst `7` und dann `"id"`. Die Integer 10, 3, 8 haben die Summe `21.0` und das Maximum `10`. Die Double 1.5 und 2.5 haben die Summe `4.0` und das Maximum `2.5`.

## Abnahme

Die Werte oben. Gerüste: `mvn -q -DskipTests compile`, ohne Testklassen.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2`. Prüfen mit `mvn -q test`.
