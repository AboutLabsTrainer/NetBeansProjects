# Tag 3 – cURL-Tests

Voraussetzung: App läuft auf `http://localhost:8080` (`mvn spring-boot:run`).

## Person mit mehreren Adressen anlegen

```bash
curl -X POST http://localhost:8080/api/persons \
  -H "Content-Type: application/json" \
  -d "{
    \"firstname\": \"Max\",
    \"lastname\": \"Mustermann\",
    \"email\": \"max@example.com\",
    \"addresses\": [
      {
        \"street\": \"Hauptstraße 1\",
        \"city\": \"Berlin\",
        \"zipCode\": \"10115\",
        \"country\": \"Deutschland\",
        \"type\": \"HOME\"
      },
      {
        \"street\": \"Geschäftsweg 42\",
        \"city\": \"München\",
        \"zipCode\": \"80333\",
        \"country\": \"Deutschland\",
        \"type\": \"WORK\"
      }
    ]
  }"
```

## Adresse hinzufügen

```bash
curl -X POST http://localhost:8080/api/persons/1/addresses \
  -H "Content-Type: application/json" \
  -d "{
    \"street\": \"Lieferadresse 7\",
    \"city\": \"Hamburg\",
    \"zipCode\": \"20095\",
    \"country\": \"Deutschland\",
    \"type\": \"SHIPPING\"
  }"
```

## Order anlegen

```bash
curl -X POST http://localhost:8080/api/persons/1/orders \
  -H "Content-Type: application/json" \
  -d "{
    \"orderNumber\": \"ORD-1001\",
    \"price\": 49.90,
    \"status\": \"PENDING\"
  }"
```

## Abfragen

```bash
curl http://localhost:8080/api/persons
curl http://localhost:8080/api/persons/1
curl http://localhost:8080/api/persons/1/full
curl http://localhost:8080/api/persons/with-addresses

curl "http://localhost:8080/api/persons/search/by-lastname?lastname=Mustermann"
curl "http://localhost:8080/api/persons/search/by-email?email=max@example.com"
curl "http://localhost:8080/api/persons/search/by-city?city=Berlin"
curl "http://localhost:8080/api/persons/search/by-firstname?firstname=max"
curl "http://localhost:8080/api/persons/search/dynamic?lastname=Mustermann&city=Berlin"
curl "http://localhost:8080/api/persons/search/summary?lastname=Mustermann"

curl "http://localhost:8080/api/orders/by-status?status=PENDING"
curl http://localhost:8080/api/orders/by-person/1
curl http://localhost:8080/api/orders/latest
```

## Pagination

```bash
curl "http://localhost:8080/api/persons?page=0&size=5&sortField=lastname&sortDir=asc"
```

## MariaDB statt H2

In `application.properties` Profil ändern:

```properties
spring.profiles.active=mysql
```

Datenbank `persondb` muss existieren.
