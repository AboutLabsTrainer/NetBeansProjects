# Messaging

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Producer und Consumer teilen sich eine Warteschlange. Die Übung hält die Queue im Speicher, ohne ActiveMQ.

## Codebeispiel

Broker, Producer, Consumer und Mail bleiben in `codebeispiel`. Ports nicht umlegen.

| Modul | Main-Klasse | server.port |
| --- | --- | --- |
| MyMessageBroker | `de.ibb.boot.ak.messaging.MessageBrokerApplication` | **8080** |
| MessageProducer | `de.ibb.boot.ak.messaging.MessageProducerApplication` | **8081** |
| MessageConsumer | `de.ibb.boot.ak.messaging.MessageConsumerApplication` | **8082** |
| SpringMailService | `de.ibb.boot.ak.messaging.SpringMailServiceApplication` | **8083** |

Broker-Transport `broker.url`: **tcp://localhost:61616**. Diesen Port nicht ändern.

Im Ordner `codebeispiel`, ein Modul je Terminal:

```bash
mvn -pl MyMessageBroker spring-boot:run
```

## Stufe 1

`POST /api/orders?text=A-100` legt den Text auf die Queue `orders` und gibt die Liste zurueck.

Port **8310** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8310/api/orders`

## Stufe 2

Zweite Queue `invoices` über `POST /api/invoices?text=R-1`.

Port **8311** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8311/api/invoices`

## Abnahme

Im Ordner `uebung-stufe-1` und `uebung-stufe-2`:

```bash
mvn -q -DskipTests compile
```

Im Ordner `loesung/stufe-1` und `loesung/stufe-2`:

```bash
mvn -q test
```

## Lösung erst nach eigenem Versuch

Die Lösungen liegen unter `loesung/stufe-1` und `loesung/stufe-2`. Erst öffnen, wenn die eigene Übung kompiliert und das erwartete Verhalten zeigt.
