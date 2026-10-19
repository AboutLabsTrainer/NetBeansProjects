---
name: Tag 1 REST API Nachbau
overview: "Lauffähiges Maven/Spring-Boot-Projekt erstellen, das die Tag-1-Anleitung exakt abbildet: Hello World, Person-API mit 100 Datafaker-Personen, Paginierung und Suche – NetBeans-kompatibel."
todos: []
isProject: false
---

# Tag 1 Spring Boot REST API – exakter Nachbau


Der Artikel beschreibt kein separates „Mini-Challenge“-Kapitel, sondern ein aufbauendes Tag-1-Projekt. Es wird **vollständig** nachgebaut (Grundlagen + Professional + Bonus).

---

## Zielbild


| Bereich          | Inhalt                                                                                                |
| ---------------- | ----------------------------------------------------------------------------------------------------- |
| **Grundlagen**   | Spring Boot Projekt, HelloController (`/hello`), Person-Model, PersonController mit JSON, GET nach ID |
| **Professional** | Datafaker-Dependency, 100 Personen mit deutschen Namen im PersonController                            |
| **Bonus**        | Paginierung (`page`, `size`), Suche nach Namen (Firstname/Lastname, case-insensitive)                 |


---

## Projektstruktur (Maven, NetBeans-kompatibel)

```
01-rest-api/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── <groupId>/<artifactId>/
    │   │       ├── RestApiApplication.java      (Main + @SpringBootApplication)
    │   │       ├── controller/
    │   │       │   ├── HelloController.java     (GET /hello)
    │   │       │   └── PersonController.java   (GET /api/persons, /api/persons/{id}, Pagination, Suche)
    │   │       └── model/
    │   │           └── Person.java             (id, firstname, lastname, email)
    │   └── resources/
    │       └── application.properties          (optional: server.port)
    └── test/
        └── java/... (optional, leer oder Standard-SpringBootTest)
```

- **GroupId/ArtifactId:** z. B. `com.example` / `rest-api` oder `de.ibb.rest` / `rest-api` – konsistent in `pom.xml` und Package-Pfad.
- **Java:** 17 (laut Artikel).
- **Spring Boot:** 3.2.x (aktuell stabil, Maven-kompatibel).

---

## 1. `pom.xml`

- **Parent:** `spring-boot-starter-parent` (festes Boot-Release).
- **Dependencies:**
  - `spring-boot-starter-web` (REST + Tomcat + Jackson).
  - `spring-boot-devtools` (optional).
  - **Datafaker:** z. B. `net.datafaker:datafaker` (Version aus Maven Central, z. B. 2.x).
- **Packaging:** `jar`.
- **Build:** `spring-boot-maven-plugin` für `mvn spring-boot:run` und fat-JAR.

NetBeans: Projekt als „Open Project“ öffnen → Maven lädt Dependencies automatisch.

---

## 2. Main-Klasse

- Eine Klasse mit `@SpringBootApplication` (z. B. `RestApiApplication.java`).
- `public static void main(String[] args)` mit `SpringApplication.run(...)`.
- Liegt im Basis-Package, damit alle `@RestController`/`@Component` gefunden werden.

---

## 3. `HelloController`

- **Klasse:** `@RestController`.
- **Methode:** `@GetMapping("/hello")` → returns `String` (z. B. `"Hello World"`).
- Kein weiterer Pfad-Prefix.

Erwartetes Verhalten: `GET http://localhost:8080/hello` → `"Hello World"`.

---

## 4. Model `Person`

- **Felder:** `Long id`, `String firstname`, `String lastname`, `String email`.
- **Java-Bean-konform:** Default-Konstruktor, Getter/Setter (für Jackson).
- Package: z. B. `...model`.

---

## 5. `PersonController`

- **Basis-URL:** `@RequestMapping("/api/persons")`.

**Daten:** Eine `List<Person>` mit **100 Einträgen**, erzeugt im Code (z. B. im Konstruktor oder statischer Block) mit **Datafaker**:

- `Faker` mit `Locale.GERMANY`.
- `faker.name().firstName()`, `faker.name().lastName()`, `faker.internet().emailAddress()`.
- IDs 1–100.

**Endpoints (exakt wie im Artikel):**


| Methode | URL                        | Beschreibung                                                                                                                                                       |
| ------- | -------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| GET     | `/api/persons`             | Alle Personen; optional Paginierung (siehe unten).                                                                                                                 |
| GET     | `/api/persons/{id}`        | Eine Person per `@PathVariable Long id`; wenn nicht gefunden: `null` oder leere Antwort (Artikel erwähnt „nicht ideal“, kein explizites Error-Handling für Tag 1). |
| GET     | `/api/persons?page=&size=` | Paginierung: nur `page` und `size` auslesen, entsprechende Teilmenge der Liste zurückgeben (ohne Spring Data – manuell aus der Liste sublist).                     |
| GET     | `/api/persons?name=...`    | Suche: Parameter `name`; Filter: Firstname ODER Lastname enthält `name` (case-insensitive); Stream/Filter wie im Artikel.                                          |


**Implementierungsdetails:**

- `@GetMapping` für alle Personen; `@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size` – wenn gesetzt, Sub-Liste berechnen (z. B. `page * size` bis `min((page+1)*size, list.size())`).
- `@GetMapping("/{id}")` mit `@PathVariable Long id` → `persons.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null)`.
- Optional gleicher Endpoint oder kombinierbar: Suche mit `@RequestParam(required = false) String name`; wenn `name` gesetzt: Liste filtern (firstname/lastname contains ignoreCase), dann ggf. paginieren.

Artikel-Logik: Paginierung und Suche können kombiniert werden (erst filtern, dann paginieren).

---

## 6. Konfiguration

- **application.properties:** Optional `server.port=8080` (Default) oder abweichend, falls Port-Konflikt.

---

## 7. Abnahme / Test (nach Implementierung)

- Projekt in NetBeans öffnen → „Build with Dependencies“ / „Clean and Build“.
- „Run“ → Spring Boot startet (z. B. Port 8080).
- **Manuell prüfen:**
  - `GET /hello` → „Hello World“.
  - `GET /api/persons` → JSON mit 100 Personen (deutsche Namen).
  - `GET /api/persons/1` → eine Person als JSON.
  - `GET /api/persons?page=0&size=10` → 10 Personen.
  - `GET /api/persons?name=Mueller` (oder Teil eines Namens) → gefilterte Liste.

---

## Kurzfassung der zu erstellenden Dateien


| Datei                                                                                   | Inhalt                                                                              |
| --------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| [pom.xml](01-rest-api/pom.xml)                                                          | Maven mit Spring Boot Parent, spring-boot-starter-web, devtools, datafaker, Plugin  |
| [RestApiApplication.java](01-rest-api/src/main/java/.../RestApiApplication.java)        | @SpringBootApplication + main                                                       |
| [HelloController.java](01-rest-api/src/main/java/.../controller/HelloController.java)   | GET /hello                                                                          |
| [Person.java](01-rest-api/src/main/java/.../model/Person.java)                          | id, firstname, lastname, email + Default-Konstruktor + Getter/Setter                |
| [PersonController.java](01-rest-api/src/main/java/.../controller/PersonController.java) | 100 Personen (Datafaker DE), /api/persons, /api/persons/{id}, page/size, name-Suche |
| [application.properties](01-rest-api/src/main/resources/application.properties)         | optional server.port                                                                |


Package-Namen werden mit der gewählten GroupId/ArtifactId konsistent gewählt (z. B. `com.example.restapi`).

Damit ist die Tag-1-Anleitung exakt nachgebaut und als lauffähiges Maven-Projekt in NetBeans nutzbar.
