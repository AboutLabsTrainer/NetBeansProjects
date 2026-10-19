# 05 JPA und JDBC

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Dieselbe Produkttabelle läuft einmal über JPA und einmal über JDBC. EclipseLink kommt vom Server, nicht aus dem WAR.

## Codebeispiel

- Context-Pfad: `/Tag7`
- URL: http://localhost:8080/Tag7/ , http://localhost:8080/Tag7/jpa , http://localhost:8080/Tag7/jdbc
- Deploy: Ordner `codebeispiel` auf Payara 6. Persistence-Unit `Tag7PU`, Provider `org.eclipse.persistence.jpa.PersistenceProvider`, JPA 3.1, JNDI `jdbc/jwebak_tag07_jpa`. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`PriceRule.accept` erlaubt 0 und positive Preise. `null` und negative Werte fallen durch.

## Stufe 2

`StockSql.updateStock` liefert `UPDATE products SET stock = ? WHERE id = ?`. Der erste Parameter ist der Bestand, der zweite die Id.

## Abnahme

- Stufe 1: `0` ja, `-0.01` nein.
- Stufe 2: Bestand 4 und Id 9 stehen in dieser Reihenfolge in den Parametern.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
