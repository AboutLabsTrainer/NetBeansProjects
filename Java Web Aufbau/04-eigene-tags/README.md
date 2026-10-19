# 04 Eigene Tags

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein Simple-Tag kürzt den Body. Ein zweites Tag wiederholt ihn. Die Regeln stehen in Java, die JSP ruft nur das Tag auf.

## Codebeispiel

- Context-Pfad: `/Tag56`
- URL: http://localhost:8080/Tag56/ , http://localhost:8080/Tag56/demo-simple.jsp , http://localhost:8080/Tag56/demo-classic.jsp
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`Truncate.shorten` lässt einen kurzen Text stehen. Ist er länger als `max`, bleiben `max - 1` Zeichen und `…`. Bei `max <= 1` bleibt nur `…`. `null` wird ein leerer Text.

## Stufe 2

`Repeat.times` hängt den Text `count` Mal an. `count <= 0` oder `null` ergibt einen leeren Text.

## Abnahme

- Stufe 1: `Hallo` mit 4 wird `Hal…`, mit 1 wird `…`.
- Stufe 2: `ab` dreimal wird `ababab`.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
