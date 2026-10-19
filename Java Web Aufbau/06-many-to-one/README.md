# 06 Many-to-One

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

`Book` besitzt die Beziehung über `author_id`. `Author.books` ist die Gegenseite mit `mappedBy`.

## Codebeispiel

- Context-Pfad: `/Tag8`
- URL: http://localhost:8080/Tag8/jpa
- Deploy: Ordner `codebeispiel` auf Payara 6. Persistence-Unit `Tag8PU`, Provider `org.eclipse.persistence.jpa.PersistenceProvider`, JPA 3.1, JNDI `jdbc/jwebak_tag08_manytoone`. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`BookLink.attach` setzt den Autor am Buch und nimmt das Buch in `author.getBooks()` auf. Ein zweites Anhängen desselben Objekts erzeugt kein Duplikat. `null` wirft `IllegalArgumentException`.

## Stufe 2

`BookLink.move` löst das Buch vom bisherigen Autor und hängt es an den neuen. Ein Buch ohne Autor kann direkt umgezogen werden.

## Abnahme

- Stufe 1: nach zwei Aufrufen steht das Buch einmal in der Liste.
- Stufe 2: nach dem Umzug ist die alte Liste leer und die neue enthält das Buch.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
