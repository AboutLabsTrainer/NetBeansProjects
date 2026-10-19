# 06 – JDBC

JDBC aus `Tag06-jdbc`: Verbindung, `PreparedStatement` und die Personen-Tabelle. Der MySQL-Treiber bleibt im Codebeispiel.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie bauen die SQL-Texte aus dem Beispiel und speichern eine Person über ein kleines DAO in einer H2-Datenbank im Speicher.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.jdbc.HalloJDBC`.

```bash
mvn -q -DskipTests compile
mvn exec:java
```

`mvn exec:java` spricht MySQL auf `localhost` an (`jae_tag06_jdbc`) und läuft ohne diesen Server nicht. Kompilieren braucht den Server nicht. Weitere Mains: `CrudBeispiel`, `SqlInjectionDemo`.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `PersonenSql`.

`createTableSql`, `insertSql` und `selectAlleSql` liefern die Texte aus `HalloJDBC`, einschließlich der Spalte `` `alter` ``. Noch keine Verbindung.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `PersonenDao`.

Dieselbe Idee wie das Insert mit Platzhaltern, gegen H2 im Speicher. Die Spalte heißt hier `lebensalter`, damit das Schlüsselwort `alter` nicht im Weg steht. H2 steht nur in dieser Übung und in der Lösung, nicht statt MySQL im Codebeispiel.

## Abnahme

- Das Insert hat drei Platzhalter und die Spalte `` `alter` ``.
- Das DAO speichert `Max Mustermann` in H2 und liest den Namen wieder.
- Lösungen: `mvn -q test` ohne MySQL.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
