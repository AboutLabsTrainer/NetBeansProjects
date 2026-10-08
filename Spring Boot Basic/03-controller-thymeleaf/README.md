# Spring Boot Basic – Tag 3: Thymeleaf & MVC Views

Von JSON zu HTML: Dieselbe Anwendung liefert einmal **JSON** (für APIs) und einmal **HTML** (für den Browser) – über getrennte Controller, aber denselben Service.

---

## Was dieses Projekt macht

- **REST-API** (Tag 1/2): `GET /api/persons` liefert Personen als JSON.
- **Web-UI** (Tag 3): `GET /persons` liefert eine HTML-Tabelle, erzeugt mit Thymeleaf.
- Beide Wege nutzen denselben **PersonService** (100 Test-Personen im Speicher, CRUD, Suche, Sortierung).

Unterschied: **@RestController** gibt Objekte zurück (→ JSON), **@Controller** gibt View-Namen zurück (→ Thymeleaf rendert HTML).

---

## Architektur-Überblick

```mermaid
flowchart TB
    subgraph Browser
        User[Browser / Mensch]
    end

    subgraph App[Spring Boot Anwendung]
        subgraph Web[Web-Schicht]
            PVC[PersonViewController]
            PC[PersonController]
            HC[HelloController]
        end
        PS[PersonService]
        subgraph Model[Modell]
            P[Person]
        end
        subgraph Views[Templates]
            List[persons-list.html]
            Detail[person-details.html]
        end
    end

    User -->|"GET /persons"| PVC
    User -->|"GET /api/persons"| PC
    User -->|"GET /hello"| HC

    PVC --> PS
    PC --> PS
    PS --> P

    PVC --> List
    PVC --> Detail
```

- **PersonViewController** (@Controller): HTML-Views, nutzt Thymeleaf-Templates.
- **PersonController** (@RestController): JSON-API für Programme.
- **PersonService**: gemeinsame Logik für beide; **Person** ist das Datenmodell.

---

## Ablauf: Request → HTML vs. JSON

```mermaid
sequenceDiagram
    participant Browser
    participant PersonViewController as ViewController
    participant PersonController as RESTController
    participant PersonService as PersonService
    participant Thymeleaf as Thymeleaf

    Note over Browser,Thymeleaf: HTML-Ansicht (Mensch)
    Browser->>PersonViewController: GET /persons
    PersonViewController->>PersonService: getSortedPersons / searchPersons
    PersonService-->>PersonViewController: List Person
    PersonViewController->>PersonViewController: Pagination, Model befüllen
    PersonViewController->>Thymeleaf: persons-list
    Thymeleaf-->>Browser: HTML

    Note over Browser,Thymeleaf: JSON-API (Programm)
    Browser->>PersonController: GET /api/persons
    PersonController->>PersonService: getAllPersons()
    PersonService-->>PersonController: List Person
    PersonController-->>Browser: JSON
```

- **/persons**: View-Controller → Service → Model → Thymeleaf → HTML.
- **/api/persons**: REST-Controller → Service → JSON (kein Template).

---

## Projektstruktur

```mermaid
flowchart LR
    subgraph Java[src/main/java/.../tag3]
        App[SpringBootBasicTag3Application]
        subgraph Controller[controller]
            Hello[HelloController]
            PersonREST[PersonController]
            PersonView[PersonViewController]
        end
        PersonService[PersonService]
        Person[model/Person]
    end

    subgraph Resources[src/main/resources]
        Props[application.properties]
        subgraph Templates[templates]
            ListHtml[persons-list.html]
            DetailHtml[person-details.html]
        end
        subgraph Static[static/css]
            CSS[style.css]
        end
    end

    App --> Controller
    App --> PersonService
    PersonView --> ListHtml
    PersonView --> DetailHtml
    ListHtml --> CSS
    DetailHtml --> CSS
```

| Pfad | Beschreibung |
|------|--------------|
| `SpringBootBasicTag3Application.java` | Einstiegspunkt, startet die Anwendung |
| `controller/HelloController` | GET /hello (Text) |
| `controller/PersonController` | REST: GET/POST/PUT/DELETE /api/persons (JSON) |
| `controller/PersonViewController` | HTML: /, /persons, /persons/{id} (Thymeleaf) |
| `service/PersonService` | In-Memory-Daten, 100 Personen, CRUD, Suche, Sortierung |
| `model/Person` | Datenmodell (id, firstname, lastname, email) |
| `templates/persons-list.html` | Tabelle mit Pagination, Suche, Sortierung |
| `templates/person-details.html` | Detailseite einer Person |
| `static/css/style.css` | Layout und Styling der Web-UI |

---

## Endpoints

| URL | Typ | Beschreibung |
|-----|-----|--------------|
| `/` | HTML | Redirect auf /persons |
| `/persons` | HTML | Personen-Liste (Tabelle, Pagination, Suche, Sortierung) |
| `/persons/{id}` | HTML | Detailseite einer Person |
| `/persons?search=…&sort=…&order=…&page=…&size=…` | HTML | Liste mit Filtern |
| `/api/persons` | JSON | Alle Personen (REST) |
| `/api/persons/{id}` | JSON | Eine Person (REST) |
| `/hello` | Text | Einfacher Test-Endpoint |

---

## Schnellstart

1. **In NetBeans:** File → Open Project → Ordner `03-controller-thymeleaf` wählen.
2. **Starten:** Rechtsklick auf Projekt → Run (oder im Projektordner: `mvn spring-boot:run`).
3. **Im Browser (Port 8081):**
   - http://localhost:8081/ → Redirect zu /persons  
   - http://localhost:8081/persons → Personen-Liste  
   - http://localhost:8081/persons/1 → Person-Details  
   - http://localhost:8081/api/persons → JSON-API  
   - http://localhost:8081/hello → Test  

---

## Technologie-Stack

- **Spring Boot 3.3.5**, Java 21  
- **Spring Web** (MVC, REST)  
- **Thymeleaf** (HTML-Templates)  
- **Spring Boot DevTools** (Hot Reload)  
- **Maven** (Build, Abhängigkeiten)  

---

## Konzepte in Kürze

- **@Controller** liefert View-Namen → Thymeleaf rendert HTML aus `templates/`.
- **@RestController** liefert Objekte → Spring serialisiert zu JSON.
- **Model**: Controller füllt das Model, Thymeleaf liest es in den Templates (z. B. `th:text`, `th:each`, `th:href`).
- **Ein Service für zwei Wege**: Web-UI und REST-API teilen sich den PersonService, keine doppelte Logik.
