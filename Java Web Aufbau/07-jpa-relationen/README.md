# 07 JPA-Relationen

Start in der IDE: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

`User` und `UserProfile` sind `@OneToOne`. Viele `Order`-Zeilen zeigen mit `@ManyToOne` auf denselben User. Beide Seiten bleiben gleich.

## Codebeispiel

- Context-Pfad: `/javawebaufbau-tag9`
- URL: http://localhost:8080/javawebaufbau-tag9/ und http://localhost:8080/javawebaufbau-tag9/orders
- Deploy: Ordner `codebeispiel` auf Payara 6. Persistence-Unit `JavaWebAufbauPU`, Provider `org.eclipse.persistence.jpa.PersistenceProvider`, JPA 3.1, JNDI `jdbc/jwebak_tag09_relations`. `mvn -q -DskipTests compile` oder `mvn -q package`.

## Stufe 1

`OrderLink.add` legt die Order in `user.getOrders()` und setzt `order.setUser`. Dieselbe Order landet nur einmal in der Liste.

## Stufe 2

`ProfileLink.bind` setzt das Profil am User und den User am Profil.

## Abnahme

- Stufe 1: zwei Aufrufe mit derselben Order ergeben eine Listenlänge 1, `getUser` zeigt zurück.
- Stufe 2: `getProfile` und `getUser` zeigen aufeinander.
- Übung: `mvn -q -DskipTests compile`. Lösung: `mvn -q test` ohne Payara und ohne MySQL.

## Lösung erst nach eigenem Versuch

`loesung/stufe-1` und `loesung/stufe-2` erst öffnen, wenn die eigene Übung kompiliert.
