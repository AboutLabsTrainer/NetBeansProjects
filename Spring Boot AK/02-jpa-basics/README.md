# JPA-Grundlagen

## Was dieses Beispiel übt

Eine Person als JPA-Entity, ein Spring-Data-Repository mit der abgeleiteten Abfrage `findByEmail` und einen Service als Transaktionsgrenze. Der REST-Controller spricht nur den Service an.

## Wichtige Klassen in Lesereihenfolge

1. `DemoApplication` – Start
2. `Person` – Entity der Tabelle `persons`
3. `PersonRepository` – Spring Data JPA
4. `PersonService` – Transaktion um die Anwendungsfälle
5. `PersonController` – REST unter `/api/persons`

## Start

In NetBeans `DemoApplication` starten, oder im Modulordner:

```bash
mvn spring-boot:run
```

Die Anwendung lauscht auf Port **8080** (`server.port` in `application.properties`).

## Datenbank

Aktives Profil: **mysql** (`spring.profiles.active`). Verbindungsdaten stehen in `application-mysql.properties`.

Die Anwendung nutzt die MariaDB-Datenbank **jsprbak_tag02_jpa**.

Das Profil `h2` in `application-h2.properties` ist für Tests vorgesehen (In-Memory). Die laufende Anwendung aktiviert es nicht. Tests bleiben auf H2.

## Ausprobieren

1. Nach dem Start http://localhost:8080/api/persons öffnen. Die Liste ist anfangs leer, im Log erscheint das SQL von Hibernate.
2. Eine Person anlegen:

```bash
curl -X POST http://localhost:8080/api/persons -H "Content-Type: application/json" -d "{\"firstname\":\"Ada\",\"lastname\":\"Lovelace\",\"email\":\"ada@example.com\"}"
```

3. Dieselbe Adresse erneut senden. Die eindeutige E-Mail wird abgelehnt. `GET /api/persons` zeigt den ersten Datensatz.
