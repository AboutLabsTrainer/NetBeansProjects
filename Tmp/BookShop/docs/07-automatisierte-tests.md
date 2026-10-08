# Automatisierte Tests: Web, Service, Repository

Lern-Einheit: die Anwendung schichtgerecht absichern —

```text
Controller → Web-Slice-Test (@WebMvcTest)
Service    → Unit-Test mit Mockito
Repository → Integrationstest mit MySQL/Testcontainers
```

Vorher: [06-author-book-manytoone.md](06-author-book-manytoone.md)  
Nächste Einheit: [08-flyway-schema-versionierung.md](08-flyway-schema-versionierung.md)  
Danach: Actuator, Logs und Health Checks  
Projektübersicht: [README](../README.md)

Im BookShop gibt es bereits WebMvc- und Application-Tests (teils mit H2 im Profil `test`). Diese Einheit beschreibt die **didaktische Ideallinie** inkl. Testcontainers gegen MySQL.

Spring unterstützt dafür **Test Slices** wie `@WebMvcTest` und `@DataJpaTest` — nicht bei jedem Test die vollständige Anwendung starten.

---

## Lernziele

* passende Testart pro Schicht wählen
* `@DataJpaTest` + Testcontainers MySQL
* Service isoliert mit `@Mock` / `@InjectMocks`
* Controller mit `@WebMvcTest`, `@MockitoBean` und `MockMvc`
* Validation und HTTP-Vertrag absichern
* wissen, warum nicht alles `@SpringBootTest` sein sollte

---

## Inhaltsverzeichnis

1. [Testabhängigkeiten](#1-testabhängigkeiten)
2. [Repository-Integrationstest](#2-repository-integrationstest)
3. [Zweiter Repository-Test](#3-zweiter-repository-test)
4. [Service-Unit-Test](#4-service-unit-test)
5. [Erfolgreiches Anlegen im Service](#5-erfolgreiches-anlegen-im-service)
6. [Controller mit `@WebMvcTest`](#6-controller-mit-webmvctest)
7. [Validierung testen](#7-validierung-testen)
8. [POST 201 + Location](#8-post-201--location)
9. [Tests starten](#9-tests-starten)
10. [Welcher Test findet welchen Fehler?](#10-welcher-test-findet-welchen-fehler)
11. [Warum nicht alles `@SpringBootTest`?](#11-warum-nicht-alles-springboottest)
12. [Ergebnis und nächster Schritt](#12-ergebnis-und-nächster-schritt)
13. [Bezug zum BookShop](#13-bezug-zum-bookshop)

---

## 1. Testabhängigkeiten

In der `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-testcontainers</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>mysql</artifactId>
    <scope>test</scope>
</dependency>
```

MySQL-Treiber bleibt (Runtime). **Docker muss laufen**, damit Testcontainers MySQL starten kann.

Warum nicht nur H2 für Repository-Tests? Unterschiedliche SQL-Dialekte — ein Test kann unter H2 grün und unter MySQL rot sein.

---

## 2. Repository-Integrationstest

```text
src/test/java/com/example/demo/book/BookRepositoryTest.java
```

```java
package com.example.demo.book;

import com.example.demo.author.AuthorEntity;
import com.example.demo.author.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.4");

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findByAuthorIdReturnsBooksOfAuthor() {
        AuthorEntity author = authorRepository.save(
                new AuthorEntity("Robert C. Martin")
        );

        bookRepository.save(new BookEntity("Clean Code", author));
        bookRepository.save(new BookEntity("Clean Architecture", author));

        List<BookEntity> result =
                bookRepository.findByAuthorId(author.getId());

        assertThat(result)
                .hasSize(2)
                .extracting(BookEntity::getTitle)
                .containsExactlyInAnyOrder(
                        "Clean Code",
                        "Clean Architecture"
                );
    }
}
```

### `@DataJpaTest`

Lädt nur die JPA-Schicht: Entities, Repositories, EntityManager, Transaktionen.  
**Nicht:** Controller, Services, kompletter Webserver → schneller als `@SpringBootTest`.

### `@ServiceConnection`

Spring Boot liest JDBC-URL, User, Passwort, Treiber aus dem Container — keine manuelle Test-DB-Konfiguration nötig.

### `Replace.NONE`

Verhindert, dass Spring die DB durch eine eingebettete Testdatenbank ersetzt. Wir wollen den MySQL-Container.

### Transaktionen

`@DataJpaTest` rollt nach jedem Test typischerweise zurück — Tests beeinflussen sich nicht gegenseitig.

---

## 3. Zweiter Repository-Test

```java
@Test
void findByUnknownAuthorIdReturnsEmptyList() {
    List<BookEntity> result =
            bookRepository.findByAuthorId(999L);

    assertThat(result).isEmpty();
}
```

Das Repository wirft bei fehlenden Büchern **keine** Exception — es liefert eine leere Liste.  
Ob eine unbekannte Autor-ID fachlich ein Fehler ist, entscheidet der **Service**.

---

## 4. Service-Unit-Test

Unabhängig von Spring und MySQL — Repositories als Mockito-Mocks.

```text
src/test/java/com/example/demo/book/BookServiceTest.java
```

```java
package com.example.demo.book;

import com.example.demo.author.AuthorNotFoundException;
import com.example.demo.author.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void createWithUnknownAuthorThrowsException() {
        when(authorRepository.findById(99L))
                .thenReturn(Optional.empty());

        BookRequest request = new BookRequest("Unbekanntes Buch", 99L);

        assertThatThrownBy(() -> bookService.create(request))
                .isInstanceOf(AuthorNotFoundException.class)
                .hasMessageContaining("99");

        verify(bookRepository, never()).save(any(BookEntity.class));
    }
}
```

| Annotation | Rolle |
|------------|--------|
| `@ExtendWith(MockitoExtension.class)` | reiner JUnit/Mockito-Test, kein Spring |
| `@Mock` | kontrollierbares Ersatzobjekt |
| `@InjectMocks` | Service mit Mock-Konstruktorabhängigkeiten |
| `verify(..., never())` | Verhalten: ohne Autor kein `save` |

---

## 5. Erfolgreiches Anlegen im Service

```java
@Test
void createStoresBookWithSelectedAuthor() {
    AuthorEntity author = new AuthorEntity("Robert C. Martin");

    when(authorRepository.findById(1L))
            .thenReturn(Optional.of(author));

    when(bookRepository.save(any(BookEntity.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

    BookRequest request = new BookRequest("Clean Code", 1L);
    BookResponse response = bookService.create(request);

    assertThat(response.title()).isEqualTo("Clean Code");
    assertThat(response.author().name()).isEqualTo("Robert C. Martin");

    ArgumentCaptor<BookEntity> captor =
            ArgumentCaptor.forClass(BookEntity.class);

    verify(bookRepository).save(captor.capture());

    BookEntity storedBook = captor.getValue();
    assertThat(storedBook.getTitle()).isEqualTo("Clean Code");
    assertThat(storedBook.getAuthor()).isSameAs(author);
}
```

`ArgumentCaptor` untersucht das an das Repository übergebene Objekt: Autor geladen, Titel übernommen, richtiges DTO zurück.

---

## 6. Controller mit `@WebMvcTest`

HTTP-Schicht ohne echten Service/DB.

```text
src/test/java/com/example/demo/book/BookControllerTest.java
```

```java
package com.example.demo.book;

import com.example.demo.author.AuthorResponse;
import com.example.demo.error.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@Import(GlobalExceptionHandler.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void getBookReturnsJson() throws Exception {
        BookResponse response = new BookResponse(
                1L,
                "Clean Code",
                new AuthorResponse(7L, "Robert C. Martin")
        );

        when(bookService.findById(1L)).thenReturn(response);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(
                        MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author.id").value(7))
                .andExpect(jsonPath("$.author.name")
                        .value("Robert C. Martin"));

        verify(bookService).findById(1L);
    }
}
```

### `@WebMvcTest`

Lädt Webschicht: Controller, JSON, Bean Validation, Spring MVC, MockMvc — **nicht** den echten Service.

### `@MockitoBean` vs. `@Mock`

| Annotation | Kontext |
|------------|---------|
| `@Mock` | reiner Mockito-Unit-Test |
| `@MockitoBean` | Mock **im Spring-Testkontext** |

Bei Spring Boot 3.4 / Framework 6.2: `@MockitoBean` / `@MockitoSpyBean` sind die modernen Varianten; `@MockBean` / `@SpyBean` sind veraltet.

`@Import(GlobalExceptionHandler.class)` — damit Validation-/ProblemDetail-Antworten im Slice greifen.

---

## 7. Validierung testen

```java
@Test
void createBookWithInvalidDataReturnsBadRequest() throws Exception {
    String json = """
            {
              "title": "",
              "authorId": null
            }
            """;

    mockMvc.perform(
                    post("/books")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
            )
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.title").value("Validierungsfehler"))
            .andExpect(jsonPath("$.errors.title").exists())
            .andExpect(jsonPath("$.errors.authorId").exists());

    verifyNoInteractions(bookService);
}
```

Entscheidend: `verifyNoInteractions(bookService)` — bei ungültigen Daten darf der Service nicht aufgerufen werden.

---

## 8. POST 201 + Location

```java
@Test
void createBookReturns201AndLocationHeader() throws Exception {
    BookResponse response = new BookResponse(
            10L,
            "Clean Code",
            new AuthorResponse(1L, "Robert C. Martin")
    );

    when(bookService.create(any(BookRequest.class)))
            .thenReturn(response);

    String json = """
            {
              "title": "Clean Code",
              "authorId": 1
            }
            """;

    mockMvc.perform(
                    post("/books")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json)
            )
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/books/10"))
            .andExpect(jsonPath("$.id").value(10))
            .andExpect(jsonPath("$.title").value("Clean Code"));

    verify(bookService).create(any(BookRequest.class));
}
```

Sichert den API-Vertrag: `201`, `Location`, JSON, Service-Aufruf.

> **BookShop mit Security:** Tests brauchen oft `httpBasic(...)` und `@Import(SecurityConfig)` — siehe [10-spring-security-rollen.md](10-spring-security-rollen.md) und bestehende `BookControllerTest`.

---

## 9. Tests starten

```powershell
.\mvnw.cmd test
```

```bash
./mvnw test
# oder: mvn test
```

Erfolg ungefähr:

```text
Tests run: 7, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

Repository-Test mit Testcontainers: erster Lauf länger (Docker-Image).

---

## 10. Welcher Test findet welchen Fehler?

| Fehler | Passender Test |
|--------|----------------|
| Falscher Repository-Methodenname | `@DataJpaTest` |
| Fehlerhaftes Entity-Mapping | `@DataJpaTest` |
| MySQL-spezifisches SQL-Problem | Testcontainers |
| Falsche Geschäftsregel | Mockito-Service-Test |
| `save` trotz fehlendem Autor | Mockito + `verify(never())` |
| Falscher HTTP-Status | `@WebMvcTest` |
| Falsches JSON | `MockMvc` + `jsonPath` |
| Validation fehlt | `MockMvc` + `verifyNoInteractions` |
| Falscher `Location`-Header | `MockMvc` |

---

## 11. Warum nicht alles `@SpringBootTest`?

`@SpringBootTest` startet nahezu die gesamte Anwendung — sinnvoll für wenige End-to-End-Systemtests, übertrieben für jede Klasse.

Gute Faustregel — kleinste nötige Umgebung:

```text
Service-Regel          → Mockito-Unit-Test
Controller-Verhalten   → @WebMvcTest
Repository und Mapping → @DataJpaTest + MySQL-Testcontainer
```

Vorteile: schneller, präziser, leichter zu verstehen und zu diagnostizieren.

---

## 12. Ergebnis und nächster Schritt

Drei Testebenen:

```text
BookControllerTest  → HTTP, JSON, Validation
BookServiceTest     → Geschäftslogik isoliert
BookRepositoryTest  → JPA und MySQL
```

**Nächster Schritt:** Datenbankschema-Versionierung mit **Flyway** — siehe [08-flyway-schema-versionierung.md](08-flyway-schema-versionierung.md). Im BookShop bereits Schritt 7 — siehe [README](../README.md).

---

## 13. Bezug zum BookShop

| Thema dieser Einheit | Im BookShop |
|----------------------|-------------|
| WebMvc-Tests | `BookControllerTest`, `AuthorControllerTest` (mit Security) |
| Application-Test | `BookShopApplicationTests` |
| Health-Test | `BookCatalogHealthIndicatorTest` |
| Test-DB | oft H2 + Profil `test` + Flyway-Migrationen |
| Testcontainers MySQL | in der README als nächster Schritt genannt; hier die Lehrvariante |
| `@MockitoBean` | moderne Spring-Boot-3.4-Variante für Web-Slices |

Diese Datei hält die **Lehrstrategie der drei Testebenen** fest; der laufende BookShop-Code deckt Teile davon bereits ab und kann schrittweise um Testcontainers ergänzt werden.
