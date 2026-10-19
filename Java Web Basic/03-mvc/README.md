# 03 MVC

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Der Controller wählt die View. Die JSP unter `WEB-INF` ist nur über Forward erreichbar.

## Codebeispiel

- Context-Pfad: `/HelloPayara-Tag4`
- URL: http://localhost:8080/HelloPayara-Tag4/blog und http://localhost:8080/HelloPayara-Tag4/blog?id=1
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`BlogView.select` liefert `blog-list`, wenn keine Id da ist, und `blog-post`, sobald eine Id gesetzt ist.

## Stufe 2

Dieselbe Methode prüft die Id. Leer bleibt `blog-list`. Eine positive Ganzzahl wird `blog-post`. `0`, negative Zahlen und Text werden `error`.

## Abnahme

- Stufe 1: leere Id und `"3"`.
- Stufe 2: `"2"`, `"0"`, `"-1"` und `"x"`.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
