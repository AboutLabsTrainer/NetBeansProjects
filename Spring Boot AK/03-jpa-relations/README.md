# JPA-Beziehungen

## Was dieses Beispiel übt

Beziehungen zwischen `Person`, `Address` und `Order`: One-to-Many, Many-to-One, Fetch-Join, Entity-Graph, abgeleitete Query-Methoden, eine Closed Projection und Specifications für eine dynamische Suche.

## Wichtige Klassen in Lesereihenfolge

1. `PersonManagementApplication` – Start
2. `Person`, `Address`, `Order` – Entities und die Enums `AddressType` und `OrderStatus`
3. `PersonRepository` und `OrderRepository` – Abfragen und Ladeverhalten
4. `PersonService` und `OrderService` – Transaktion und Änderungen an den Entities
5. `PersonSummary` – Projection nur mit Namen und E-Mail
6. `PersonSpecifications` – Criteria für die Suche
7. `PersonController` und `OrderController` – REST, ohne eigene Repository-Aufrufe

## Start

In NetBeans `PersonManagementApplication` starten, oder im Modulordner:

```bash
mvn spring-boot:run
```

Die Anwendung lauscht auf Port **8080** (`server.port` in `application.properties`).

## Datenbank

Aktives Profil: **mysql**. Verbindungsdaten stehen in `application-mysql.properties`.

Die Anwendung nutzt die MariaDB-Datenbank **jsprbak_tag03_jpa**.

## Ausprobieren

1. Eine Person anlegen:

```bash
curl -X POST http://localhost:8080/api/persons -H "Content-Type: application/json" -d "{\"firstname\":\"Ada\",\"lastname\":\"Lovelace\",\"email\":\"ada@example.com\"}"
```

2. Zurückkommt eine Id. Damit eine Adresse hängen:

```bash
curl -X POST http://localhost:8080/api/persons/1/addresses -H "Content-Type: application/json" -d "{\"street\":\"Markt 1\",\"zipCode\":\"53111\",\"city\":\"Bonn\",\"type\":\"HOME\"}"
```

3. http://localhost:8080/api/persons/1/full und http://localhost:8080/api/persons/search/summary?lastname=Lovelace vergleichen: einmal mit Beziehungen, einmal nur die Projection. Die Id im Pfad an die Antwort aus Schritt 1 anpassen.

## Fußangeln

`Address` und `Order` nutzen Lombok-`equals`/`hashCode` über alle Felder außer der Gegenseite. Solange `id` noch null ist, gelten zwei neue Adressen im `Set` als gleich. Open Session in View ist in Spring Boot standardmäßig an: eine lazy Beziehung kann deshalb noch während der JSON-Ausgabe laden, auch ohne eigene Transaktion im Controller.
