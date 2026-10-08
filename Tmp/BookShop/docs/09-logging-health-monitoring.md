# Logging, Health Checks und Monitoring

Lern-Einheit: die Anwendung **betriebsfähig beobachtbar** machen — mit Actuator, Health Indicators, Micrometer und Logging (inkl. MDC).

Vorher: [08-flyway-schema-versionierung.md](08-flyway-schema-versionierung.md)  
Nächste Einheit: [10-spring-security-rollen.md](10-spring-security-rollen.md)  
Danach: Benutzer in MySQL  
Projektübersicht: [README](../README.md)

Im BookShop entspricht diese Einheit dem **Entwicklungsschritt 8**. Security folgt in Schritt 9.

Eine Anwendung sollte von außen beantworten können:

```text
Läuft die Anwendung?
Ist MySQL erreichbar?
Welche Version ist aktiv?
Wie viele Requests kommen an?
Wie hoch sind Speicherverbrauch und Antwortzeiten?
Was ist bei einem Fehler passiert?
```

---

## Lernziele

* Actuator-Endpunkte gezielt exponieren
* Health (technisch + fachlich) verstehen
* SLF4J-Logging und Log-Level richtig nutzen
* Korrelations-ID mit MDC und Filter
* Micrometer-Metriken lesen und eigene Counter definieren
* Sicherheitsgrundregeln für Actuator kennen

---

## Inhaltsverzeichnis

1. [Actuator ergänzen](#1-actuator-ergänzen)
2. [Sichere Grundkonfiguration](#2-sichere-grundkonfiguration)
3. [Health prüfen](#3-health-prüfen)
4. [Details im Dev-Profil](#4-details-im-dev-profil)
5. [Eigener Health Indicator](#5-eigener-health-indicator)
6. [Logging im Service](#6-logging-im-service)
7. [Log-Level](#7-log-level)
8. [Keine vertraulichen Daten](#8-keine-vertraulichen-daten)
9. [Request-ID mit MDC](#9-request-id-mit-mdc)
10. [Korrelations-ID im Logformat](#10-korrelations-id-im-logformat)
11. [Log-Level konfigurieren](#11-log-level-konfigurieren)
12. [Metriken abrufen](#12-metriken-abrufen)
13. [Eigene Metrik](#13-eigene-metrik)
14. [Health-Test](#14-health-test)
15. [Sicherheitsregeln](#15-sicherheitsregeln)
16. [Lernkontrolle](#16-lernkontrolle)
17. [Ergebnis](#17-ergebnis)
18. [Bezug zum BookShop](#18-bezug-zum-bookshop)

---

## Ausgangslage

Vorhanden: REST, Service/Repository, MySQL, JPA, Validation, Tests, Flyway.  
Jetzt: Beobachtbarkeit für Betrieb und Diagnose.

---

## 1. Actuator ergänzen

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Actuator stellt Verwaltungs- und Diagnoseinformationen unter `/actuator` bereit (mit Web-Starter).

Nach Start öffnen: `http://localhost:8080/actuator`

---

## 2. Sichere Grundkonfiguration

```properties
management.endpoints.web.exposure.include=health,info,metrics

management.endpoint.health.show-details=never
management.endpoint.health.show-components=always

management.info.env.enabled=true

info.app.name=Book API
info.app.description=REST-API für Bücher und Autoren
info.app.version=1.0.0
```

Erreichbar:

```text
GET /actuator
GET /actuator/health
GET /actuator/info
GET /actuator/metrics
```

**Nicht** unkritisch:

```properties
management.endpoints.web.exposure.include=*
```

`env`, `beans`, `configprops`, `loggers` können interne Infos offenlegen. Health-Details gehören nicht öffentlich — in Produktion idealerweise nur für autorisierte Benutzer (siehe Security).

---

## 3. Health prüfen

```bash
curl http://localhost:8080/actuator/health
```

```json
{
  "status": "UP"
}
```

`UP` = beteiligte Health Indicators halten die App für betriebsbereit.

Mit DataSource kommt automatisch ein DB-Health-Indicator. Weitere Beispiele: DiskSpace, Mongo, Elasticsearch, …

MySQL stoppen → oft `DOWN`. Bewusst testen: starten → Health → MySQL stoppen → Health erneut.

---

## 4. Details im Dev-Profil

`src/main/resources/application-dev.properties`:

```properties
management.endpoint.health.show-details=always
management.endpoint.health.show-components=always
```

Profil aktivieren:

```properties
spring.profiles.active=dev
```

```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=dev"
```

Dann enthält `/actuator/health` z. B. `db`, `diskSpace`, `ping` mit Details (je nach Boot-/DB-Setup).

---

## 5. Eigener Health Indicator

Technisch: DataSource-Check. Fachlich: Buchkatalog.

```java
package com.example.demo.monitoring;

import com.example.demo.book.BookRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public class BookCatalogHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;

    public BookCatalogHealthIndicator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        try {
            long bookCount = bookRepository.count();
            return Health.up()
                    .withDetail("bookCount", bookCount)
                    .withDetail("message", "Der Buchkatalog ist erreichbar.")
                    .build();
        } catch (DataAccessException exception) {
            return Health.down(exception)
                    .withDetail("message", "Der Buchkatalog ist nicht erreichbar.")
                    .build();
        }
    }
}
```

Komponentenname: Suffix `HealthIndicator` entfällt → `bookCatalog`.

`bookCount = 0` ist nicht automatisch `DOWN` — `DOWN` für echte Betriebsstörungen reservieren.

---

## 6. Logging im Service

SLF4J als Fassade:

```java
private static final Logger log =
        LoggerFactory.getLogger(BookService.class);
```

Beim Anlegen:

```java
log.info("Buch wird angelegt: title={}, authorId={}",
        request.title(), request.authorId());
// ...
log.info("Buch wurde angelegt: bookId={}, title={}",
        savedBook.getId(), savedBook.getTitle());
```

Bei fehlendem Autor:

```java
log.warn("Autor wurde nicht gefunden: authorId={}", id);
```

---

## 7. Log-Level

| Level | Verwendung |
|-------|------------|
| `TRACE` | sehr detaillierte interne Abläufe |
| `DEBUG` | Diagnose für Entwickler |
| `INFO` | wichtige Geschäfts-/Betriebsereignisse |
| `WARN` | ungewöhnlich, aber kontrolliert |
| `ERROR` | fehlgeschlagene Operation / Systemfehler |

Platzhalter statt Verkettung:

```java
// ungünstig
log.debug("Suche Buch " + id);

// besser
log.debug("Suche Buch {}", id);
```

---

## 8. Keine vertraulichen Daten

Nicht loggen: Passwörter, Tokens, Cookies, Kartendaten, DB-Passwörter, volle personenbezogene Sätze.

Für die Book-API reichen IDs:

```java
log.info("Buch aktualisiert: bookId={}, authorId={}", bookId, authorId);
```

---

## 9. Request-ID mit MDC

Gleichzeitige Requests → Logzeilen schwer zuordnen. Lösung: Korrelations-ID.

```java
package com.example.demo.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    public static final String HEADER_NAME = "X-Correlation-ID";
    public static final String MDC_KEY = "correlationId";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String correlationId = request.getHeader(HEADER_NAME);
        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        MDC.put(MDC_KEY, correlationId);
        response.setHeader(HEADER_NAME, correlationId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(MDC_KEY);
        }
    }
}
```

**MDC** (Mapped Diagnostic Context) ergänzt Logs um Kontext, ohne die ID durch jede Methode zu reichen.

---

## 10. Korrelations-ID im Logformat

```properties
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%thread] [%X{correlationId:-no-request}] %logger{36} - %msg%n
```

Beispielzeile enthält `[491b4399-…]`. Client kann Header setzen:

```bash
curl -i -H "X-Correlation-ID: test-request-4711" \
  http://localhost:8080/books/1
```

Antwort und Logs tragen dieselbe ID.

---

## 11. Log-Level konfigurieren

Entwicklung:

```properties
logging.level.root=INFO
logging.level.com.example.demo=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.orm.jdbc.bind=TRACE
```

Produktion: Hibernate weniger ausführlich (`WARN` / `OFF`).

---

## 12. Metriken abrufen

```bash
curl http://localhost:8080/actuator/metrics
curl http://localhost:8080/actuator/metrics/http.server.requests
```

Typische Namen: `http.server.requests`, `jvm.memory.used`, `jdbc.connections.active`, …

Metriken = fortlaufende Messwerte (Micrometer); Export z. B. nach Prometheus möglich.

---

## 13. Eigene Metrik

```java
@Component
public class BookMetrics {

    private final Counter booksCreated;

    public BookMetrics(MeterRegistry registry) {
        this.booksCreated = Counter.builder("book.catalog.created")
                .description("Anzahl neu angelegter Bücher")
                .register(registry);
    }

    public void bookCreated() {
        booksCreated.increment();
    }
}
```

Im Service nach erfolgreichem `save`: `bookMetrics.bookCreated()`.

```bash
curl http://localhost:8080/actuator/metrics/book.catalog.created
```

Zähler erst nach erfolgreichem Speichern erhöhen.

---

## 14. Health-Test

```java
@SpringBootTest
class BookCatalogHealthIndicatorTest {

    @Autowired
    private BookCatalogHealthIndicator healthIndicator;

    @Test
    void reportsUpWhenRepositoryIsAvailable() {
        Health health = healthIndicator.health();
        assertThat(health.getStatus()).isEqualTo(Status.UP);
        assertThat(health.getDetails()).containsKey("bookCount");
    }
}
```

Braucht verfügbare Testdatenbank (H2-Profil oder Testcontainers wie in [07](07-automatisierte-tests.md)).

---

## 15. Sicherheitsregeln

Vor Produktion:

* nur benötigte Endpunkte exponieren
* Health-Details nicht öffentlich
* Actuator mit Spring Security schützen
* optional separater Management-Port
* Shutdown-Endpunkt deaktiviert lassen

```properties
management.server.port=9090
```

```text
App:        http://localhost:8080
Management: http://localhost:9090/actuator
```

Separater Port allein ist **keine** Zugriffskontrolle.

---

## 16. Lernkontrolle

| Frage | Kurzantwort |
|-------|-------------|
| Was macht Actuator? | Betriebs-/Diagnose-Endpunkte |
| Warum MySQL im Health? | automatischer DataSource-Indicator |
| Log vs. Metrik? | Ereignis mit Kontext vs. fortlaufender Zahlenwert |
| Was ist MDC? | Kontextfelder für Logs (z. B. Request-ID) |
| Warum Health-Details schützen? | interne Infrastruktur-Infos |
| Was misst `http.server.requests`? | u. a. Count, Dauer, Max |

---

## 17. Ergebnis

Drei Ebenen der Beobachtbarkeit:

```text
Logs     → Was ist konkret passiert?
Health   → Ist die Anwendung betriebsbereit?
Metrics  → Wie verhält sich die Anwendung über die Zeit?
```

**Nächster Schritt:** Spring Security — siehe [10-spring-security-rollen.md](10-spring-security-rollen.md). Im BookShop: Schritt 9 und [README](../README.md).

---

## 18. Bezug zum BookShop

| Thema dieser Einheit | Im BookShop |
|----------------------|-------------|
| Actuator | `spring-boot-starter-actuator`, Exposure `health,info,metrics` |
| Info | `info.app.*` in Properties |
| Dev-Details | `application-dev.properties` |
| Fachlicher Health | `BookCatalogHealthIndicator` |
| Logging + Pattern | `BookService`, Correlation-Id im Pattern |
| MDC-Filter | `CorrelationIdFilter` |
| Eigene Metrik | `BookMetrics` / `book.catalog.created` |
| Health-Test | `BookCatalogHealthIndicatorTest` |
| Security der Endpunkte | Schritt 9: health/info öffentlich, metrics nur ADMIN |
