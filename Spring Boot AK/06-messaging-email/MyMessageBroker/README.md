# MyMessageBroker

## Was dieses Beispiel übt

Ein eingebetteter ActiveMQ-Broker als Spring-Bean. Producer und Consumer verbinden sich mit seinem TCP-Connector. Dieses Modul sendet und liest keine Bestellungen.

## Wichtige Klassen in Lesereihenfolge

1. `MessageBrokerApplication` – Start des Prozesses
2. `BrokerConfig` – Broker-Bean, Connector und Datenverzeichnis

## Start

Zuerst dieses Modul starten, danach Producer und Consumer. In NetBeans `MessageBrokerApplication` ausführen, oder im Modulordner:

```bash
mvn spring-boot:run
```

Der HTTP-Port ist **8080**. Der Broker lauscht auf **tcp://localhost:61616** (`broker.url` in `application.properties`).

## Ausprobieren

1. Das Modul starten und im Log prüfen, dass der Connector auf Port 61616 steht.
2. `MessageProducer` starten und http://localhost:8081/api/orders/send?product=Laptop&customer=Max&quantity=2 aufrufen.
3. `MessageConsumer` starten, falls noch nicht geschehen, und http://localhost:8082/api/orders/processed öffnen. Die Bestellung aus Schritt 2 steht in der Liste.
