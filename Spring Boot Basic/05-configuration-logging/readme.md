# Spring Boot Basic – Tag 5: Konfiguration & Logging

Eine Spring-Boot-Webanwendung zur Verwaltung von Personen. Das Projekt demonstriert professionelle Konfiguration, Profile, typsichere Properties und strukturiertes Logging.

---

## Projektübersicht

| Eigenschaft | Wert |
|-------------|------|
| Framework | Spring Boot 3.2 |
| Java | 21 |
| Build | Maven |
| Port | 8095 |

---

## Architektur

```mermaid
flowchart TB
    subgraph Config [Konfiguration]
        AppProps[AppProperties]
        BaseProps[application.properties]
        DevProps[application-dev.properties]
        ProdProps[application-prod.properties]
    end

    subgraph Bootstrap [Bootstrap]
        DataBootstrap[DataBootstrap]
    end

    subgraph App [Anwendung]
        PersonVC[PersonViewController]
        PersonSvc[PersonService]
    end

    subgraph View [Präsentation]
        Thymeleaf[Thymeleaf Templates]
    end

    BaseProps --> AppProps
    DevProps --> AppProps
    ProdProps --> AppProps
    AppProps --> PersonVC
    DataBootstrap --> PersonSvc
    PersonVC --> PersonSvc
    PersonVC --> Thymeleaf
```

---

## Konfigurationssystem

### Profile

Die Anwendung nutzt Spring-Profile für unterschiedliche Umgebungen:

```mermaid
flowchart LR
    subgraph Dev [dev-Profil]
        D1[Port 8095]
        D2[Thymeleaf Cache aus]
        D3[DEBUG-Logging]
        D4[Testdaten via DataBootstrap]
    end

    subgraph Prod [prod-Profil]
        P1[Port 8095]
        P2[Thymeleaf Cache an]
        P3[WARN-Logging]
        P4[Log-Datei]
    end
```

| Profil | Verwendung | Besonderheiten |
|--------|------------|----------------|
| **dev** | Entwicklung | Thymeleaf-Cache deaktiviert, DEBUG-Logs, DataBootstrap lädt Testdaten |
| **prod** | Produktion | Cache aktiv, reduzierte Logs, Logging in Datei |

### Properties-Hierarchie

```mermaid
flowchart TD
    Base[application.properties]
    Dev[application-dev.properties]
    Prod[application-prod.properties]

    Base --> |Basis-Werte| Merged[Zusammengeführte Konfiguration]
    Dev --> |Überschreibt bei dev| Merged
    Prod --> |Überschreibt bei prod| Merged
```

- **application.properties**: Gemeinsame Basis (App-Name, Log-Pattern, eigene Properties)
- **application-dev.properties**: Entwicklungsspezifisch
- **application-prod.properties**: Produktionsspezifisch

---

## @ConfigurationProperties

Die Klasse `AppProperties` bindet typsicher Properties mit dem Präfix `app`:

```mermaid
classDiagram
    class AppProperties {
        +String name
        +String version
        +int maxPersons
        +Features features
        +Api api
    }
    class Features {
        +boolean emailEnabled
        +boolean exportEnabled
    }
    class Api {
        +String key
        +String secret
    }
    AppProperties --> Features
    AppProperties --> Api
```

| Property | Beschreibung |
|----------|--------------|
| `app.name` | Anzeigename der Anwendung |
| `app.version` | Versionsnummer |
| `app.max-persons` | Maximale Personenanzahl |
| `app.features.email-enabled` | Feature-Flag E-Mail |
| `app.features.export-enabled` | Feature-Flag Export |
| `app.api.key` / `app.api.secret` | API-Zugangsdaten (via Umgebungsvariablen) |

---

## Logging

Logging erfolgt über SLF4J mit Logback als Implementierung:

```mermaid
flowchart LR
    Code[Anwendungscode]
    SLF4J[SLF4J Facade]
    Logback[Logback]
    Console[Konsole]
    File[Log-Datei]

    Code --> SLF4J
    SLF4J --> Logback
    Logback --> Console
    Logback --> File
```

| Komponente | Log-Level (dev) | Log-Level (prod) |
|------------|-----------------|------------------|
| Root | INFO | WARN |
| com.example.helloworldapi | DEBUG | INFO |
| org.springframework.web | DEBUG | – |

---

## Datenfluss beim Start

```mermaid
sequenceDiagram
    participant Spring
    participant DataBootstrap
    participant PersonService

    Spring->>PersonService: PersonService initialisieren
    PersonService->>PersonService: log.info("PersonService initialized")

    alt dev-Profil aktiv
        Spring->>DataBootstrap: run()
        DataBootstrap->>PersonService: createPerson(Max Mustermann)
        DataBootstrap->>PersonService: createPerson(Anna Schmidt)
        DataBootstrap->>DataBootstrap: log.info("DataBootstrap completed")
    end
```

---

## Projektstruktur

```
05-configuration-logging/
├── src/main/java/com/example/helloworldapi/
│   ├── HelloWorldApiApplication.java
│   ├── bootstrap/
│   │   └── DataBootstrap.java          # Testdaten (nur dev)
│   ├── config/
│   │   └── AppProperties.java          # @ConfigurationProperties
│   ├── controller/
│   │   ├── PersonViewController.java
│   │   └── CustomErrorController.java
│   ├── model/
│   │   └── Person.java
│   ├── service/
│   │   └── PersonService.java
│   └── validation/
│       ├── ValidGermanPhone.java
│       └── GermanPhoneValidator.java
├── src/main/resources/
│   ├── application.properties
│   ├── application-dev.properties
│   ├── application-prod.properties
│   ├── logback-spring.xml
│   ├── static/css/style.css
│   └── templates/
│       ├── persons-list.html
│       ├── person-details.html
│       ├── person-form.html
│       ├── person-edit-form.html
│       └── error/
└── pom.xml
```

---

## Schnellstart

### Voraussetzungen

- JDK 21
- Maven 3.6+

### Anwendung starten

```bash
# Development (mit Testdaten, DEBUG-Logs)
mvn spring-boot:run "-Dspring-boot.run.profiles=dev"

# Production (weniger Logs, Log-Datei)
mvn spring-boot:run "-Dspring-boot.run.profiles=prod"
```

### Zugriff

- Personen-Liste: http://localhost:8095/persons
- Neue Person: http://localhost:8095/persons/new

---

## Umgebungsvariablen (optional)

Für API-Zugangsdaten in Produktion:

| Variable | Beschreibung |
|----------|--------------|
| `API_KEY` | API-Schlüssel |
| `API_SECRET` | API-Geheimnis |

Im dev-Profil werden bei fehlenden Variablen Dummy-Werte verwendet.

---

## Log-Datei

Im prod-Profil werden Logs zusätzlich in `logs/persons-app.log` geschrieben (Rolling, max. 30 Tage).
