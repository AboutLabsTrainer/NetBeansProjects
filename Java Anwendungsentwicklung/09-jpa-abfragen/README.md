# 09 – JPA-Abfragen

JPQL, Beziehungen und Repository aus `Tag09-jpa`. Dieselbe JPA-Strecke: EclipseLink 5.0.0, Jakarta Persistence 3.2, `RESOURCE_LOCAL`.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie schreiben die JPQL-Texte aus der Demo auf und summieren Bestellbeträge, ohne den EntityManager zu starten.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.jpa.JpqlDemo`.

```bash
mvn -q -DskipTests compile
mvn exec:java
```

Die Persistence Unit bleibt `RESOURCE_LOCAL` mit EclipseLink und MySQL `ibb-db` (Benutzer `ibb-user`, Tabellen `jae09_personen`, `jae09_adressen`, `jae09_bestellungen`). Der Lauf braucht die Datenbank. Weitere Mains: `RelationshipDemo`, `de.ibb.jpa.repository.RepositoryDemo`. Die Abfrage älterer Personen lautet `SELECT p FROM Person p WHERE p.alter > :minAlter ORDER BY p.alter`.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `JpqlKatalog`.

`aelterAls` und `anzahl` liefern die beiden JPQL-Texte aus `JpqlDemo`.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `BestellSumme`.

Summe der `Betrag`-Felder einer Bestellungsliste. Leere Liste ergibt 0.

## Abnahme

- Die Altersabfrage filtert `p.alter` und sortiert danach.
- 10,00 plus 2,50 ergibt 12,50.
- Lösungen: `mvn -q test` ohne MySQL.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
