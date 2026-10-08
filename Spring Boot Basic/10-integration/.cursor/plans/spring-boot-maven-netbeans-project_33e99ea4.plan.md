---
name: spring-boot-maven-netbeans-project
overview: Erstellung eines vollständigen Spring-Boot-Maven-Projekts für NetBeans auf Port 8100, angelehnt an das Tag-10-Person-Management-Beispiel (REST, Thymeleaf, AOP, Scopes, WebSockets, JAX-RS) mit Java 21 und lauffähig per NetBeans-Run und Maven.
todos:
  - id: init-maven-structure
    content: Maven-Projektstruktur und `pom.xml` mit Spring-Boot- und Jersey-Abhängigkeiten für Java 21 erstellen
    status: completed
  - id: app-skeleton
    content: Main-Class, Paketstruktur und `application.properties` mit Port 8100 und Logging aufsetzen
    status: completed
  - id: domain-service-rest
    content: "`Person`-Domain, `PersonService` und REST-Controller mit in-memory-Storage implementieren"
    status: completed
  - id: mvc-views
    content: Thymeleaf-MVC-Controller und Views für Personenliste und Formular hinzufügen
    status: completed
  - id: aop-scopes
    content: AOP-Logging und Session-Scoped `PersonFavorites` integrieren
    status: completed
  - id: websockets
    content: WebSocket-/STOMP-Konfiguration und einfache Chat-Seite implementieren
    status: completed
  - id: jaxrs
    content: JAX-RS via Jersey konfigurieren und `PersonResource` implementieren
    status: completed
  - id: verify-run
    content: Start über NetBeans und Maven testen und REST, MVC, WebSocket und JAX-RS-Endpunkte prüfen
    status: completed
isProject: false
---

## Ziel

Ein neues, lauffähiges Spring-Boot-Maven-Projekt in deinem bestehenden Ordner `10-integration` erstellen, das auf Port 8100 läuft, mit Java 21 kompatibel ist und die zentralen Bausteine des Kurs-Projekts enthält:

- REST-API mit Spring MVC
- Thymeleaf-MVC-Views
- AOP-Logging
- Bean-Scopes (u. a. Session)
- WebSockets (ein einfaches Chat-/Broadcast-Beispiel)
- JAX-RS-Endpoint via Jersey-Integration

NetBeans soll das Projekt als Maven-Projekt erkennen, und Start über NetBeans-Run sowie `mvn spring-boot:run` soll funktionieren.

## Grobe Architektur

- **Main-App**: `PersonManagementApplication` mit `@SpringBootApplication` im Paket `com.example.personmanagement` (oder ähnlich).
- **Domain**: `Person`-Klasse (id, firstname, lastname, optional weitere Felder) im Paket `domain`.
- **Service-Layer**: `PersonService` als `@Service` (Singleton), in-memory-Storage via `List`/`ConcurrentHashMap`, mit `@PostConstruct`/`@PreDestroy` und DI per Konstruktor.
- **REST-API (Spring MVC)**: `PersonApiController` als `@RestController` unter `/api/persons` mit CRUD-ähnlichen Endpoints.
- **MVC/Thymeleaf**: `PersonMvcController` als `@Controller` mit Routen wie `/persons`, Views in `src/main/resources/templates` (`persons.html`, `add-person.html` etc.).
- **AOP-Logging**: `LoggingAspect` mit `@Aspect` + `@Around`-Advice für mit Annotation markierte Methoden (`@LogExecutionTime`).
- **Scopes/Session**: `PersonFavorites` als `@Component` mit `@Scope(session)` für Favoriten-IDs.
- **WebSockets**: einfache Chat-Funktion mit einem STOMP-Endpunkt (`/ws`) und Destination `/topic/public`, plus eine statische HTML/Thymeleaf-Seite, die mit SockJS/Stomp.js verbindet.
- **JAX-RS**: `PersonResource` als `@Component @Path("/persons")` etc., konfiguriert über eine Jersey-Konfigurationsklasse.
- **Configuration**: `application.properties` mit `server.port=8100`, Logging-Level und ggf. Profil-Beispielen.

## Konkrete Schritte

1. **Maven-Basisprojekt und POM konfigurieren**
  - In `10-integration` ein Maven-Projekt-Layout anlegen (`src/main/java`, `src/main/resources`, `src/test/java`).
  - `pom.xml` erstellen mit:
    - Parent `spring-boot-starter-parent` (3.x), `java.version` auf 21.
    - Abhängigkeiten: `spring-boot-starter-web`, `spring-boot-starter-thymeleaf`, `spring-boot-starter-websocket`, `spring-boot-starter-actuator` (optional), `spring-boot-starter-aop`, `jersey-spring6`, `jersey-container-servlet-core`, Lombok (optional), Test-Starter.
    - Maven-Plugins: `spring-boot-maven-plugin` (mit Repackage) und Standard-Compiler (Source/Target 21, wenn nötig explizit).
  - Sicherstellen, dass `artifactId` und `name` NetBeans-freundlich sind (z. B. `person-management-app`).
2. **Main-Class und Grundstruktur anlegen**
  - Paketstruktur unter `src/main/java` anlegen: `com.example.personmanagement` mit Unterpaketen `config`, `controller`, `service`, `domain`, `aop`, `session`, `jaxrs`, `websocket`.
  - `PersonManagementApplication` mit `main`-Methode und `@SpringBootApplication` erstellen.
  - `application.properties` in `src/main/resources` mit:
    - `server.port=8100`
    - sinnvolle Logging-Konfiguration (`logging.level.root=INFO`, `logging.level.com.example.personmanagement=DEBUG`).
3. **Domain- und Service-Layer implementieren**
  - `Person`-POJO mit Feldern (`id`, `firstname`, `lastname`) und Standard-Getter/Setter, `toString`.
  - `PersonService` als `@Service` mit in-memory-Liste und Methoden wie `getAllPersons`, `createPerson`, `findById`, `deletePerson`.
  - `@PostConstruct` und `@PreDestroy` Methoden für Logging der Lifecycle-Events.
4. **REST-API (Spring MVC) implementieren**
  - `PersonApiController` als `@RestController` unter `/api/persons`.
  - Endpoints: `GET /api/persons`, `GET /api/persons/{id}`, `POST /api/persons`, `DELETE /api/persons/{id}`.
  - Verwendung von `PersonService` per Konstruktorinjektion.
  - Optional: `GlobalExceptionHandler` mit `@RestControllerAdvice` für einfache Fehlerrückmeldungen.
5. **Thymeleaf-MVC und Views implementieren**
  - `PersonMvcController` als `@Controller` mit Routen `/persons`, `/persons/add` (GET+POST). 
  - In `templates`: `persons.html` (Liste mit `th:each` über Personen) und `add-person.html` (Formular mit `th:object`/`th:field`).
  - Nutzung des Post-Redirect-Get-Patterns nach erfolgreichem POST.
6. **AOP-Logging und Annotation hinzufügen**
  - Custom-Annotation `@LogExecutionTime` definieren.
  - `LoggingAspect` im Paket `aop` mit `@Aspect` und `@Component`, `@Around("@annotation(LogExecutionTime)")`, Messung der Ausführungsdauer und Logging.
  - Beispielweise `@LogExecutionTime` auf Service-Methoden setzen.
7. **Bean-Scopes und Session-Komponente umsetzen**
  - `PersonFavorites` als `@Component` mit `@Scope(WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)`.
  - Methoden zum Hinzufügen/Entfernen von Favoriten-IDs.
  - Integration in `PersonMvcController` (z. B. Buttons, um Personen als Favorit zu markieren, Anzeige in der Liste).
8. **WebSocket-/STOMP-Konfiguration und Endpoints**
  - WebSocket-Konfigurationsklasse (`@Configuration`, `@EnableWebSocketMessageBroker`) im Paket `websocket`.
  - STOMP-Endpunkte definieren (z. B. `setApplicationDestinationPrefixes("/app")`, `registerStompEndpoints("/ws").withSockJS()`).
  - `ChatController` mit `@MessageMapping("/chat.send")` und `@SendTo("/topic/public")`.
  - Einfache Chat-View (z. B. `chat.html`) mit Einbindung von SockJS und Stomp.js, Buttons/Inputs für Nachrichten.
9. **JAX-RS (Jersey) integrieren**
  - Jersey-Konfigurationsklasse (`@Configuration`, `@ApplicationPath("/jaxrs")`, Registrierung von `PersonResource`).
  - `PersonResource` mit `@Component`, `@Path("/persons")`, `@GET`, `@Produces(MediaType.APPLICATION_JSON)`, Delegation an `PersonService`.
  - Nutzung von `@PostConstruct` in `PersonResource` zur Demonstration der Jakarta-Integration.
10. **Konfiguration, Logging und (optional) Actuator feinjustieren**
  - Logging-Levels für relevante Pakete anpassen.
  - Optional: Actuator-Endpunkte (`management.endpoints.web.exposure.include=health,info`).
  - Optional: Beispiel für Profile (`application-dev.properties` vs. `application-prod.properties`).
11. **NetBeans-Integration sicherstellen**
  - Prüfen, dass die Projektstruktur von NetBeans korrekt erkannt wird (Maven-Projekt importierbar).
  - Sicherstellen, dass `PersonManagementApplication` als Main-Class für den NetBeans-Run verwendet wird (über `spring-boot-maven-plugin` oder IDE-Einstellung).
12. **Start, Tests und Verifikation**
  - Projekt mit `mvn spring-boot:run` und via NetBeans-Run starten.
  - Über `http://localhost:8100/api/persons` und `http://localhost:8100/persons` prüfen, ob REST- und MVC-Ebene funktionieren.
  - WebSocket-Chat-Seite aufrufen (z. B. `/chat`) und Nachrichten-Austausch testen.
  - JAX-RS-Endpoint unter `http://localhost:8100/jaxrs/persons` prüfen.

## Hinweise

- Wir orientieren uns eng an den Beispielen und Konzepten aus dem Artikel zu Tag 10 ([Integration & Abschluss](https://www.java-developer.online/tag-10-integration-abschluss-finale/)), verzichten aber im ersten Schritt auf vollständige 1:1-Replikation aller Details, um das Projekt übersichtlich zu halten.
- Erweiterungen (mehr Felder, komplexere Scopes, zusätzliche AOP-Aspekte etc.) können im Anschluss schrittweise ergänzt werden, sobald das Grundprojekt stabil läuft.

