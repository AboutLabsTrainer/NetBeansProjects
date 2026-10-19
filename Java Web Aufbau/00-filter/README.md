# 00 Filter

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein Filter entscheidet vor dem Servlet, ob der Request weiterläuft oder zum Login geht.

## Codebeispiel

- Context-Pfad: `/FilterDemo`
- URL: http://localhost:8080/FilterDemo/ , http://localhost:8080/FilterDemo/admin , http://localhost:8080/FilterDemo/login
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`AdminAccess.decide` liefert `REDIRECT_LOGIN`, wenn der Pfad mit `/admin` beginnt und kein User da ist. Sonst `CONTINUE`.

## Stufe 2

`Guard.decide` wendet dieselbe Regel auf zwei Präfixe an: `/admin` und `/api`.

## Abnahme

- Stufe 1: `/admin/home` ohne User wird umgeleitet, mit User und `/home` laufen weiter.
- Stufe 2: `/api/metrics` ohne User wird umgeleitet, mit User nicht.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
