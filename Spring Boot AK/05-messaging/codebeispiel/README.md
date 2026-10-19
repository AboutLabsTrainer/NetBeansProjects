Die Aufgabe dieses Moduls steht in der [README des Elternordners](../README.md).

# Tag 7: Messaging & Email

Maven-Multi-Modul-Projekt mit Broker, Producer, Consumer und Mail.

## Module

| Modul | Port | Funktion |
|-------|------|----------|
| **MyMessageBroker** | 8080 / 61616 | Embedded ActiveMQ Broker |
| **MessageProducer** | 8081 | Sendet Bestellungen an die Queue |
| **MessageConsumer** | 8082 | Verarbeitet Bestellungen aus der Queue |
| **SpringMailService** | 8083 | Email-Versand (sync / async / HTML) |

## Voraussetzungen

- Java JDK 21
- Maven 3.8+
- Docker (nur für MailDev / Email-Tests)

## Startreihenfolge

In NetBeans: Parent-Projekt öffnen, dann jedes Modul einzeln starten (**Run**).

Oder per Maven (je ein Terminal):

```bash
cd MyMessageBroker && mvn spring-boot:run
cd MessageProducer && mvn spring-boot:run
cd MessageConsumer && mvn spring-boot:run
cd SpringMailService && mvn spring-boot:run
```

**Wichtig:** Broker zuerst starten, danach Producer und Consumer.

## Messaging testen

```bash
curl "http://localhost:8081/api/orders/send?product=Laptop&customer=Max&quantity=2"
```

Verarbeitete Bestellungen:

```bash
curl "http://localhost:8082/api/orders/processed"
```

## Email testen

MailDev starten:

```bash
docker run -p 1080:1080 -p 1025:1025 maildev/maildev
```

Web-UI: http://localhost:1080

```bash
# Einfache Text-Email
curl "http://localhost:8083/api/email/send?to=test@test.de"

# HTML-Email
curl "http://localhost:8083/api/email/html?to=test@test.de&name=Max"

# Async-Email (Response kommt sofort)
curl "http://localhost:8083/api/email/async?to=test@test.de&name=Max"
```

## Build aller Module

```bash
mvn clean package -DskipTests
```
