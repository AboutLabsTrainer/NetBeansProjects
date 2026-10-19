---
name: Spring Boot Caching Project
overview: Erstelle ein lauffaehiges Maven/Spring Boot Projekt mit Caching (Caffeine), CalculatorService, CalculatorController, CacheConfig und Cache-Stats-Endpunkt basierend auf dem Tag-6-Tutorial.
todos:
  - id: pom
    content: pom.xml mit Spring Boot Parent und Dependencies erstellen
    status: completed
  - id: app
    content: CachingDemoApplication.java erstellen
    status: completed
  - id: config
    content: CacheConfig.java mit Caffeine-Konfiguration erstellen
    status: completed
  - id: service
    content: CalculatorService.java mit allen Operationen und Caching erstellen
    status: completed
  - id: controller
    content: CalculatorController.java mit allen Endpunkten erstellen
    status: completed
  - id: props
    content: application.properties erstellen
    status: completed
isProject: false
---

# Spring Boot Caching Maven Projekt

Erstelle ein vollstaendiges Spring Boot Maven-Projekt im Workspace `c:\Users\Trainer\Documents\NetBeansProjects\Spring Boot AK\05-caching` mit allen Klassen aus dem Tutorial.

## Projektstruktur

```
05-caching/
  pom.xml
  src/main/java/com/example/cachingdemo/
    CachingDemoApplication.java
    CacheConfig.java
    CalculatorService.java
    CalculatorController.java
  src/main/resources/
    application.properties
```

## Dateien

- **pom.xml**: Spring Boot 3.x Parent, Dependencies: `spring-boot-starter-web`, `spring-boot-starter-cache`, `caffeine`
- **CachingDemoApplication.java**: `@SpringBootApplication`, main-Methode (kein `@EnableCaching` hier, das steht in CacheConfig)
- **CacheConfig.java**: `@Configuration` + `@EnableCaching`, CaffeineCacheManager mit 5 Cache-Namen (`additions`, `subtractions`, `multiplications`, `divisions`, `powers`), `maximumSize(100)`, `expireAfterWrite(10, MINUTES)`, `recordStats()`
- **CalculatorService.java**: `@Service` mit `@Cacheable` fuer `add`, `subtract`, `multiply`, `divide`, `power` (jeweils eigener Cache-Name, 2s sleep Simulation), plus `@CacheEvict` `clearAllCaches`-Methode
- **CalculatorController.java**: `@RestController` `/calc` mit Endpunkten `/add`, `/subtract`, `/multiply`, `/divide`, `/power`, `/clear` (POST), `/stats` -- inklusive Zeitmessung und Cache-Stats-Ausgabe
- **application.properties**: `spring.cache.type=caffeine`
