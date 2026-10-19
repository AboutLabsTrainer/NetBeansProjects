Die Aufgabe dieses Moduls steht in der [README des Elternordners](../README.md).

# Spring Boot Basic – Integration

## Was dieses Beispiel übt

Eine Anwendung, die die Themen der vorherigen Beispiele zusammenführt: Thymeleaf-Formulare, eine REST-API, eine JAX-RS-Ressource, eine Session-Bean, einen Aspect und einen STOMP-Chat. Alle Schnittstellen sprechen denselben `PersonService` im Speicher an. Eine Datenbank gibt es nicht.

## Wichtige Klassen in Lesereihenfolge

1. `PersonManagementApplication` – Start und Component-Scan
2. `PersonService` – gemeinsame Personenliste, mit `LogExecutionTime` an `createPerson`
3. `LoggingAspect` – misst die markierten Aufrufe
4. `PersonMvcController` und `PersonFavorites` – Formular und Session-Scope
5. `PersonApiController` – JSON unter `/api/persons`
6. `JerseyConfig` und `PersonResource` – JAX-RS unter `/jaxrs`
7. `WebSocketConfig` und `ChatController` – Chat-Seite und STOMP

## Start

In NetBeans das Projekt öffnen und `PersonManagementApplication` starten, oder im Modulordner:

```bash
mvn spring-boot:run
```

Die Anwendung lauscht auf Port **8100** (`server.port` in `application.properties`).

## Ausprobieren

1. Personenliste öffnen: http://localhost:8100/persons — dort eine Person über das Formular anlegen.
2. Dieselbe Liste als JSON holen: http://localhost:8100/api/persons und über JAX-RS unter http://localhost:8100/jaxrs/persons.
3. Den Chat-Link öffnen (http://localhost:8100/chat), zwei Browserfenster verbinden und eine Nachricht schicken. Sie erscheint über `/topic/public`.
