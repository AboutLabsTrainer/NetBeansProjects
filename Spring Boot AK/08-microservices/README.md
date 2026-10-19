# Microservices

Vor dem Start die vorherige Anwendung beenden. Jedes Programm startet einen eigenen eingebetteten Tomcat. Bleibt der vorige Prozess liegen, ist der Port belegt.

IDE und Maven: [Maven-Projekt in der IDE starten](../../docs/ide-maven.md).

## Lernziel

Ein Dienst bietet `/api/users` an und baut den Pfad eines anderen Dienstes. Eureka, Gateway und Feign bleiben im Codebeispiel.

## Codebeispiel

Startreihenfolge im Codebeispiel: Eureka, User, Order, Gateway. Nicht alle zugleich mit einer Template-Engine auf demselben Port.

| Ordner | Main-Klasse | server.port |
| --- | --- | --- |
| eureka-server | `de.ibb.eureka.EurekaServerApplication` | **8761** |
| user-service | `de.ibb.userservice.UserServiceApplication` | **8081** |
| order-service | `de.ibb.orderservice.OrderServiceApplication` | **8082** |
| api-gateway | `de.ibb.gateway.ApiGatewayApplication` | **8080** |

Im Ordner `codebeispiel/eureka-server`:

```bash
mvn spring-boot:run
```

## Stufe 1

`DownstreamPaths.users` macht aus einer Basis-URL `.../api/users`. `GET /api/users` liefert `Ada`.

Port **8316** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8316/api/users`

## Stufe 2

`DownstreamPaths.orders` und `GET /api/orders` mit dem Wert `A-100`.

Port **8317** (Übung und Lösung, nicht gleichzeitig starten).

```bash
mvn spring-boot:run
```

`http://localhost:8317/api/orders`

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
