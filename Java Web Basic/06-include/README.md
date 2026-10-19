# 06 Include

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

`<%@ include %>` kopiert zur Übersetzungszeit, `<jsp:include>` zur Laufzeit. Texte, die in die Seite wandern, werden maskiert.

## Codebeispiel

- Context-Pfad: `/Tag7`
- URL: http://localhost:8080/Tag7/demo.jsp
- Deploy: Ordner `codebeispiel` auf Payara 6. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`HtmlEscaper.escape` macht aus `null` einen leeren Text. `&` wird zuerst zu `&amp;`, danach `<` zu `&lt;` und `>` zu `&gt;`.

## Stufe 2

Dieselbe Methode maskiert zusätzlich `"` als `&quot;` und `'` als `&#39;`.

## Abnahme

- Stufe 1: `a&b<c>` wird `a&amp;b&lt;c&gt;`.
- Stufe 2: Anführungszeichen sind maskiert, das Ampersand in der Entity bleibt einfach.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
