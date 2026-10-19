Die Aufgabe dieses Moduls steht in der [README des Elternordners](../../README.md).

# MessageProducer

## Was dieses Beispiel übt

Eine HTTP-Anfrage wird zur JMS-Nachricht. `JmsTemplate` übergibt die Bestellung an die Queue `order-queue`. Die Antwort kommt, sobald der Broker die Nachricht angenommen hat.

## Wichtige Klassen in Lesereihenfolge

1. `MessageProducerApplication` – Start
2. `Order` – Nachrichtenkörper
3. `OrderProducer` – Versand über `JmsTemplate`
4. `OrderController` – HTTP-Einstieg

## Start

`MyMessageBroker` muss bereits laufen. In NetBeans `MessageProducerApplication` starten, oder im Modulordner:

```bash
mvn spring-boot:run
```

Die Anwendung lauscht auf Port **8081** (`server.port` in `application.properties`).

## Ausprobieren

1. http://localhost:8081/api/orders/send?product=Laptop&customer=Max&quantity=2 aufrufen. Die Antwort hat den Status `QUEUED`.
2. Dieselbe URL mit einem anderen Produkt erneut aufrufen.
3. Am Consumer http://localhost:8082/api/orders/processed öffnen und beide Bestellungen prüfen.
