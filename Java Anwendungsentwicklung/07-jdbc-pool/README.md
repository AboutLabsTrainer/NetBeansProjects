# 07 – JDBC Pool und DAO

Pooling, Transaktionen und DAO aus `Tag07-jdbc-pool`. MySQL, HikariCP und SLF4J bleiben im Codebeispiel.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie bauen `Person.withId` und ein DAO, das über eine `DataSource` speichert. Der Test benutzt H2, nicht den Pool gegen MySQL.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.jdbc.ConnectionPoolDemo`.

```bash
mvn -q -DskipTests compile
mvn exec:java
```

Der Lauf braucht MySQL (`ibb-db`, Benutzer `ibb-user`, Tabellen `jae07_personen` und `jae07_konten`). Kompilieren nicht. `Person` ist ein Record, `PersonDaoJdbc` bekommt die `DataSource` von außen. Weitere Mains: `TransactionDemo`, `de.ibb.jdbc.dao.DaoDemo`.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Record `Person`.

Der Konstruktor ohne ID steht. `withId` liefert eine Kopie mit der neuen ID.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `PersonenDao`.

`save` schreibt Name, Alter und E-Mail und gibt die Person mit generierter ID zurück, `count` zählt die Zeilen. H2 stellt die `DataSource`. Die Spalte heißt `lebensalter`.

## Abnahme

- Eine neue Person hat keine ID, `withId(5)` setzt sie.
- `save` gegen H2 erhöht `count` auf 1 und füllt die ID.
- Lösungen: `mvn -q test` ohne MySQL und ohne HikariCP.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
