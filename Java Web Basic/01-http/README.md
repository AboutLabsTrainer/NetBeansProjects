# 01 HTTP

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

HTTP-Methode und Session-Merker für das zuletzt angesehene Produkt unterscheiden.

## Codebeispiel

- Context-Pfad: `/HelloPayara`
- URL: http://localhost:8080/HelloPayara/ , http://localhost:8080/HelloPayara/products , http://localhost:8080/HelloPayara/product/1 , http://localhost:8080/HelloPayara/lastViewed , http://localhost:8080/HelloPayara/login , http://localhost:8080/HelloPayara/dashboard , http://localhost:8080/HelloPayara/method-echo
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`MethodLabel.of` übersetzt GET, POST, PUT und DELETE in `Lesen`, `Senden`, `Ersetzen` und `Löschen`. Andere Werte und `null` ergeben `Unbekannt`. Groß- und Kleinschreibung spielen keine Rolle.

## Stufe 2

`LastViewed.remember` speichert eine Produkt-Id nur, wenn sie größer als 0 ist. Sonst bleibt der bisherige Wert, auch wenn er `null` ist.

## Abnahme

- Stufe 1: die vier Methoden und ein unbekannter Wert.
- Stufe 2: Id 9 ersetzt 4, Id 0 und negative Ids ändern nichts.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
