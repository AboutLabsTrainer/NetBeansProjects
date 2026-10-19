# 04 JSP

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Eine JSP zeigt ein Formular. Die Antwort entsteht in Java und wird vom Servlet ausgegeben, die Seite bleibt frei von Scriptlets.

## Codebeispiel

- Context-Pfad: `/Tag5`
- URL: http://localhost:8080/Tag5/ , http://localhost:8080/Tag5/demo/el , http://localhost:8080/Tag5/demo/jstl , http://localhost:8080/Tag5/demo/include , http://localhost:8080/Tag5/demo/form
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`FormReply.thanks` liefert `Danke, Gast`, wenn der Name fehlt, sonst `Danke, ` plus getrimmter Name. `ReplyServlet` unter `/reply` ruft die Methode auf.

## Stufe 2

`thanks` verlangt einen Kommentar. Ein Name mit mehr als 40 Zeichen wird abgelehnt. Ein leerer Name bleibt `Gast`.

## Abnahme

- Stufe 1: `null` und `Eva`.
- Stufe 2: fehlender Kommentar und ein zu langer Name werfen `IllegalArgumentException`.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
