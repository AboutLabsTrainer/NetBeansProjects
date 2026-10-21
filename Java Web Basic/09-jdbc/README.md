# 09 JDBC

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

`PreparedStatement` bindet Name, Preis und Bestand der Reihe nach. Viele Zeilen laufen als Batch in einer Transaktion.

## Codebeispiel

- Context-Pfad: `/Tag10`
- URL: http://localhost:8080/Tag10/initdb und http://localhost:8080/Tag10/benchmark
- Deploy: Ordner `codebeispiel` auf Payara 6. JNDI `jdbc/ibb`, Tabelle `jwebb09_products`. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`ProductSql.insert` liefert das SQL `INSERT INTO jwebb09_products(name, price, stock) VALUES(?,?,?)` und die Parameter in der Reihenfolge Name, Preis, Bestand.

## Stufe 2

`BatchDecision.useBatch` ist ab 100 Zeilen wahr und darunter falsch.

## Abnahme

- Stufe 1: SQL-Text und die drei Parameter für `Stift`, `1.5`, `3`.
- Stufe 2: 99 einzeln, 100 als Batch.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
