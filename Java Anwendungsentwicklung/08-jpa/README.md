# 08 – JPA

JPA-Einführung aus `Tag08-jpa`. EclipseLink 5.0.0, Jakarta Persistence 3.2 und `RESOURCE_LOCAL` bleiben. Das Beispiel wechselt nicht auf Hibernate.

So öffnen Sie ein Blatt in NetBeans, IntelliJ oder VS Code: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Sie bauen die Anzeige der Entity nach und rechnen einen Altersdurchschnitt als reine Funktion, ohne eine Persistence Unit zu starten.

## Codebeispiel

Ordner [`codebeispiel`](codebeispiel/README.md). Main-Klasse: `de.ibb.jpa.HalloJPA`.

```bash
mvn -q -DskipTests compile
mvn exec:java
```

`persistence.xml` nennt den Provider `org.eclipse.persistence.jpa.PersistenceProvider`, Transaktionen `RESOURCE_LOCAL` und MySQL `ibb-db` (Benutzer `ibb-user`, Tabelle `jae08_personen`). Der Lauf braucht diese Datenbank. Kompilieren nicht. Weitere Mains: `EntityLifecycleDemo`, `JpaCrudDemo`.

## Stufe 1

Ordner [`uebung-stufe-1`](uebung-stufe-1/README.md), Klasse `PersonenEintrag`.

`anzeige` nutzt dasselbe Format wie `Person.toString`: `Person[id=…, name=…, alter=…, email=…]`.

## Stufe 2

Ordner [`uebung-stufe-2`](uebung-stufe-2/README.md), Klasse `AltersSchnitt`.

Durchschnitt einer Liste von Altern, dieselbe Kennzahl wie `AVG(p.alter)` im späteren JPQL-Beispiel, hier ohne Abfrage. Eine leere Liste ergibt 0.

## Abnahme

- Die Anzeige von Max, 28, enthält `id=4` und die E-Mail.
- Der Schnitt aus 28 und 34 ist 31.
- Lösungen: `mvn -q test` ohne MySQL und ohne Persistence Unit.

## Lösung erst nach eigenem Versuch

Erst nach dem eigenen Versuch: [`loesung/stufe-1`](loesung/stufe-1/README.md), [`loesung/stufe-2`](loesung/stufe-2/README.md).
