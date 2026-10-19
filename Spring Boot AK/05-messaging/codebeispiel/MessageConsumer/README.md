Die Aufgabe dieses Moduls steht in der [README des Elternordners](../../README.md).

# MessageConsumer

## Was dieses Beispiel übt

Ein JMS-Listener liest die Queue `order-queue`. Die eingegangenen Bestellungen bleiben in einer Liste, die ein REST-Controller ausliefert.

## Wichtige Klassen in Lesereihenfolge

1. `MessageConsumerApplication` – Start und JMS-Listener-Infrastruktur
2. `Order` – Nachrichtenkörper auf dieser Seite
3. `OrderConsumer` – Listener der Queue
4. `OrderController` – Liste der verarbeiteten Bestellungen

## Start

`MyMessageBroker` muss bereits laufen. In NetBeans `MessageConsumerApplication` starten, oder im Modulordner:

```bash
mvn spring-boot:run
```

Die Anwendung lauscht auf Port **8082** (`server.port` in `application.properties`).

## Ausprobieren

1. Dieses Modul starten und warten, bis der Listener verbunden ist.
2. Am Producer eine Bestellung senden: http://localhost:8081/api/orders/send?product=Monitor&customer=Eva&quantity=1
3. http://localhost:8082/api/orders/processed öffnen. Die Bestellung erscheint, sobald der Listener sie entgegengenommen hat.
