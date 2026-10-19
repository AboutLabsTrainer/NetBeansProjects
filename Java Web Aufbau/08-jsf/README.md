# 08 JSF

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Die JSF-Seite bindet das Formular an eine View-Scoped Bean. Die Prüfung von Name, Preis und Bestand liegt in Java, bevor gespeichert wird.

## Codebeispiel

- Context-Pfad: `/Tag10JSFPrimeFaces`
- URL: http://localhost:8080/Tag10JSFPrimeFaces/product-list.xhtml
- Deploy: Ordner `codebeispiel` auf Payara 6. Persistence-Unit `Tag10JSFPrimeFacesPU`, Provider `org.eclipse.persistence.jpa.PersistenceProvider`, JPA 3.1, JNDI `jdbc/jwebak_tag10_jsf`. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`ProductForm.errors` sammelt `name`, wenn der Name leer ist, und `price`, wenn der Preis fehlt oder negativ ist. Ein gültiges Paar ergibt eine leere Liste.

## Stufe 2

Dieselbe Prüfung nimmt den Bestand dazu. `stock` kommt in die Liste, wenn er fehlt oder negativ ist. 0 ist erlaubt.

## Abnahme

- Stufe 1: Leername und negativer Preis ergeben `name` und `price`.
- Stufe 2: Bestand `-1` ergibt `stock`, Bestand `0` bleibt gültig.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
