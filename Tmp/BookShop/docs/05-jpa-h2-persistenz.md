# Persistenz mit H2, JPA und Spring Data

Lern-Einheit: den flüchtigen In-Memory-Speicher durch eine relationale Datenbank ersetzen — mit **H2**, **Jakarta Persistence** und **Spring Data JPA**.

Vorher: [04-bean-validation-fehlerbehandlung.md](04-bean-validation-fehlerbehandlung.md)  
Nächste Einheit: [06-author-book-manytoone.md](06-author-book-manytoone.md)  
Danach: automatisierte Tests (`@DataJpaTest`, `@WebMvcTest`, …)  
Projektübersicht: [README](../README.md)

Im BookShop entspricht diese Einheit dem **Entwicklungsschritt 5** (JPA). Dort läuft später MySQL + Flyway statt H2 zur Laufzeit; H2 bleibt für Tests. Die Ideen sind dieselben.

Typische Kette:

```text
HTTP → Controller → Service → Repository → Datenbank
```

Das Handbuch behandelt genau diese Abfolge: relationale Datenbanken, Jakarta Persistence, Spring Data JPA, Repositories und transaktionale Zugriffe.

---

## Lernziele

* Bücher in H2 persistieren (zur Laufzeit)
* IDs von der Datenbank erzeugen lassen
* `JpaRepository` für CRUD nutzen
* Entity, Request-DTO und Response-DTO trennen
* `@Transactional` und Dirty Checking verstehen
* Derived Query Methods und häufige Anfängerfehler kennen

---

## Inhaltsverzeichnis

1. [Ziel](#1-ziel)
2. [Dependencies](#2-dependencies)
3. [Datenbank konfigurieren](#3-datenbank-konfigurieren)
4. [Entity statt Record](#4-entity-statt-record)
5. [JPA-Annotationen](#5-jpa-annotationen)
6. [Leerer Konstruktor](#6-leerer-konstruktor)
7. [Repository](#7-repository)
8. [Vorhandene CRUD-Methoden](#8-vorhandene-crud-methoden)
9. [Response-DTO](#9-response-dto)
10. [Request-DTO](#10-request-dto)
11. [Service auf JPA umstellen](#11-service-auf-jpa-umstellen)
12. [DI auf Repository-Ebene](#12-di-auf-repository-ebene)
13. [`@Transactional`](#13-transactional)
14. [Warum Update oft kein `save()` braucht](#14-warum-update-oft-kein-save-braucht)
15. [Controller](#15-controller)
16. [Startdaten](#16-startdaten)
17. [API testen](#17-api-testen)
18. [H2-Konsole](#18-h2-konsole)
19. [Derived Query](#19-derived-query)
20. [SQL im Hintergrund](#20-sql-im-hintergrund)
21. [Häufige Anfängerfehler](#21-häufige-anfängerfehler)
22. [Lernkontrolle](#22-lernkontrolle)
23. [Praxisaufgabe: ISBN](#23-praxisaufgabe-isbn)
24. [Bezug zum BookShop](#24-bezug-zum-bookshop)

---

## 1. Ziel

Nach diesem Schritt:

* bleiben Bücher während der Laufzeit in H2 gespeichert,
* erzeugt die Datenbank IDs,
* führt `JpaRepository` die CRUD-Operationen aus,
* enthält der Service die Geschäftslogik,
* bleibt der Controller weitgehend unverändert.

H2 läuft hier als **In-Memory**-Datenbank. Nach einem Neustart sind die Daten wieder weg.

---

## 2. Dependencies

Mindestens in der `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Was die neuen Dependencies liefern

`spring-boot-starter-data-jpa` bringt u. a.:

* Jakarta Persistence API
* Hibernate als Persistence Provider
* Spring Data JPA
* Transaktionsunterstützung

`h2` liefert die Datenbank.

Spring Boot erkennt die Dependencies und konfiguriert DataSource, EntityManager und Transaktionsmanagement weitgehend automatisch.

---

## 3. Datenbank konfigurieren

`src/main/resources/application.properties`:

```properties
spring.application.name=book-api

spring.datasource.url=jdbc:h2:mem:bookdb
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

| Property | Bedeutung |
|----------|-----------|
| `jdbc:h2:mem:bookdb` | H2-In-Memory-DB namens `bookdb` |
| `ddl-auto=create-drop` | Tabellen beim Start anlegen, beim Beenden entfernen |
| `show-sql=true` | SQL im Log anzeigen |

Für Lernzwecke hilfreich. In Produktion: Schemaänderungen mit **Flyway** oder **Liquibase**, typisch `ddl-auto=validate`.

---

## 4. Entity statt Record

Der bisherige `Book`-Record eignet sich als API-Objekt. Für JPA besser eine normale Klasse:

```java
package com.example.demo.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    protected BookEntity() {
        // Von JPA benötigt.
    }

    public BookEntity(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void update(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
```

---

## 5. JPA-Annotationen

| Annotation | Rolle |
|------------|--------|
| `@Entity` | persistente JPA-Entity (≈ Tabellenzeile) |
| `@Table(name = "books")` | Tabellenname |
| `@Id` | Primärschlüssel |
| `@GeneratedValue(IDENTITY)` | ID von der Datenbank |
| `@Column(...)` | Spalteneigenschaften |

```java
@Column(nullable = false, length = 200)
```

* `nullable = false` — kein `NULL` in der DB  
* `length = 200` — maximale Spaltenlänge  

**Wichtig:** DB-Regeln ersetzen Bean Validation nicht. Beide Ebenen haben unterschiedliche Aufgaben.

---

## 6. Leerer Konstruktor

```java
protected BookEntity() {
}
```

JPA braucht einen parameterlosen Konstruktor. `protected` reicht.

Fachlicher Konstruktor für Anwendungscode:

```java
new BookEntity(request.title(), request.author());
```

---

## 7. Repository

```java
package com.example.demo.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository
        extends JpaRepository<BookEntity, Long> {
}
```

Vollständiges Repository — **keine** eigene Implementierung. Spring Data erzeugt sie zur Laufzeit.

`JpaRepository<BookEntity, Long>`:

* Entity-Typ: `BookEntity`
* ID-Typ: `Long`

---

## 8. Vorhandene CRUD-Methoden

Unter anderem:

```java
bookRepository.findAll();
bookRepository.findById(id);
bookRepository.save(entity);
bookRepository.delete(entity);
bookRepository.existsById(id);
bookRepository.count();
```

Kein manuelles SQL. Intern: EntityManager → Hibernate → SQL.

---

## 9. Response-DTO

Entity nicht direkt nach außen geben:

```java
package com.example.demo.book;

public record BookResponse(
        Long id,
        String title,
        String author
) {
    public static BookResponse from(BookEntity entity) {
        return new BookResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor()
        );
    }
}
```

Drei Modelle:

```text
BookRequest  → eingehende API-Daten
BookEntity   → Datenbankmodell
BookResponse → ausgehende API-Daten
```

So ändern DB-Details den API-Vertrag nicht unkontrolliert.

---

## 10. Request-DTO

```java
package com.example.demo.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequest(

        @NotBlank(message = "Der Titel darf nicht leer sein.")
        @Size(max = 200, message = "Der Titel darf höchstens 200 Zeichen enthalten.")
        String title,

        @NotBlank(message = "Der Autor darf nicht leer sein.")
        @Size(max = 100, message = "Der Autor darf höchstens 100 Zeichen enthalten.")
        String author
) {
}
```

Validierungsgrenzen sollten zu den Spaltenlängen passen — der Client erhält möglichst früh eine klare Fehlermeldung.

---

## 11. Service auf JPA umstellen

```java
package com.example.demo.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponse> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::from)
                .toList();
    }

    public BookResponse findById(Long id) {
        return BookResponse.from(findEntityById(id));
    }

    @Transactional
    public BookResponse create(BookRequest request) {
        BookEntity entity = new BookEntity(
                request.title(),
                request.author()
        );

        BookEntity savedEntity = bookRepository.save(entity);
        return BookResponse.from(savedEntity);
    }

    @Transactional
    public BookResponse update(Long id, BookRequest request) {
        BookEntity entity = findEntityById(id);
        entity.update(request.title(), request.author());
        return BookResponse.from(entity);
    }

    @Transactional
    public void delete(Long id) {
        BookEntity entity = findEntityById(id);
        bookRepository.delete(entity);
    }

    private BookEntity findEntityById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
```

---

## 12. DI auf Repository-Ebene

```java
public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
}
```

Spring erzeugt die Repository-Implementierung und injiziert sie — analog Controller → Service.

```text
BookController
      ↓
BookService
      ↓
BookRepository
      ↓
EntityManager / Hibernate
      ↓
H2-Datenbank
```

---

## 13. `@Transactional`

Klassenebene:

```java
@Transactional(readOnly = true)
```

Lesen default read-only. Schreiben überschreibt:

```java
@Transactional
public BookResponse create(...)
```

Transaktion = logische Arbeitseinheit:

```text
begin → SQL-Operationen → commit
```

Bei Fehler:

```text
begin → SQL-Operationen → rollback
```

---

## 14. Warum Update oft kein `save()` braucht

```java
@Transactional
public BookResponse update(Long id, BookRequest request) {
    BookEntity entity = findEntityById(id);
    entity.update(request.title(), request.author());
    return BookResponse.from(entity);
}
```

Die Entity wurde in der Transaktion geladen → **managed** im Persistence Context.

Hibernate erkennt Änderungen (**Dirty Checking**) und erzeugt beim Commit z. B.:

```sql
update books
set title = ?, author = ?
where id = ?
```

Zusätzliches `bookRepository.save(entity)` ist hier normalerweise nicht nötig.

---

## 15. Controller

```java
package com.example.demo.book;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(
            @Valid @RequestBody BookRequest request
    ) {
        BookResponse createdBook = bookService.create(request);
        URI location = URI.create("/books/" + createdBook.id());
        return ResponseEntity.created(location).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request
    ) {
        return ResponseEntity.ok(bookService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```

Der Controller kennt `BookRequest`, `BookResponse`, `BookService` — **nicht** Repository, EntityManager, SQL, H2 oder Hibernate.

---

## 16. Startdaten

```java
package com.example.demo.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookDataInitializer {

    @Bean
    CommandLineRunner initializeBooks(BookRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new BookEntity(
                        "Spring Boot lernen",
                        "Anna Beispiel"
                ));
                repository.save(new BookEntity(
                        "Modernes Java",
                        "Max Mustermann"
                ));
            }
        };
    }
}
```

`CommandLineRunner` läuft nach dem Start des Anwendungskontexts — praktisch für Demo/Lernprojekte.

Im BookShop übernehmen oft Flyway-Migrationen + Seed-Daten diese Rolle.

---

## 17. API testen

```bash
curl -i http://localhost:8080/books
```

```bash
curl -i \
  -X POST \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Data JPA","author":"Chris Beispiel"}' \
  http://localhost:8080/books
```

```bash
curl -i \
  -X PUT \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Data JPA kompakt","author":"Chris Beispiel"}' \
  http://localhost:8080/books/3
```

```bash
curl -i -X DELETE http://localhost:8080/books/3
```

> **BookShop:** Lesen mit `reader`/`admin`, Schreiben mit `admin` (HTTP Basic).

---

## 18. H2-Konsole

Während die App läuft: `http://localhost:8080/h2-console`

```text
JDBC URL: jdbc:h2:mem:bookdb
User Name: sa
Password: (leer)
```

```sql
SELECT * FROM books;
SELECT * FROM books WHERE author = 'Anna Beispiel';
```

Nur für Entwicklung — in Produktion nicht öffentlich aktivieren.

---

## 19. Derived Query

```java
public interface BookRepository
        extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByAuthorIgnoreCase(String author);
}
```

Methodename → sinngemäß:

```sql
select * from books where lower(author) = lower(?)
```

Service / Controller:

```java
public List<BookResponse> findByAuthor(String author) {
    return bookRepository.findByAuthorIgnoreCase(author)
            .stream()
            .map(BookResponse::from)
            .toList();
}

@GetMapping("/search")
public List<BookResponse> searchByAuthor(@RequestParam String author) {
    return bookService.findByAuthor(author);
}
```

```bash
curl "http://localhost:8080/books/search?author=Anna%20Beispiel"
```

---

## 20. SQL im Hintergrund

Beim Start ungefähr:

```sql
create table books (
    id bigint generated by default as identity,
    title varchar(200) not null,
    author varchar(100) not null,
    primary key (id)
);
```

Weitere typische Statements: `insert`, `select`, `update`, `delete`.

Spring Data stellt die Repository-Abstraktion bereit; Hibernate als JPA-Provider macht ORM und SQL-Erzeugung.

---

## 21. Häufige Anfängerfehler

| Fehler | Besser |
|--------|--------|
| Entity als API-Modell | Request DTO ↔ Entity ↔ Response DTO |
| Repository im Controller | Service für Regeln, Transaktionen, Mapping |
| `ddl-auto=create-drop` in Produktion | `validate` + Flyway/Liquibase |
| Blind jedes Mal `save()` | Dirty Checking bei managed Entities |
| Nur DB- oder nur API-Validierung | `@NotBlank` **und** `@Column(nullable = false)` |

---

## 22. Lernkontrolle

| Frage | Kurzantwort |
|-------|-------------|
| Was macht `JpaRepository<BookEntity, Long>`? | Typisierte CRUD-Operationen für Entity + Long-ID. |
| Warum kein Record als Entity? | JPA braucht Konstruktor-/Zustandsmodell; Klasse ist idiomatischer. |
| Warum kein `AtomicLong` mehr? | Die DB erzeugt die IDs. |
| Warum oft kein `save()` beim Update? | Managed Entity + Dirty Checking. |
| Aufgabe von `@Transactional`? | Atomare Arbeitseinheit mit Commit/Rollback. |

---

## 23. Praxisaufgabe: ISBN

Entity:

```java
@Column(nullable = false, unique = true, length = 17)
private String isbn;
```

Request:

```java
@NotBlank(message = "Die ISBN darf nicht leer sein.")
String isbn
```

Repository:

```java
boolean existsByIsbn(String isbn);
```

Service vor dem Speichern:

```java
if (bookRepository.existsByIsbn(request.isbn())) {
    throw new DuplicateIsbnException(request.isbn());
}
```

Handler → `409 Conflict`.

### Nächster Schritt

1:n Autor–Buch mit `@ManyToOne` — siehe [06-author-book-manytoone.md](06-author-book-manytoone.md).

Danach automatisierte Tests — siehe [07-automatisierte-tests.md](07-automatisierte-tests.md):

* `@DataJpaTest` (Repository)
* Mockito / Service-Tests
* `@WebMvcTest` + `MockMvc` (Webschicht)

Im BookShop: WebMvc-Tests und Application-Tests mit Profil `test` (H2) — siehe [README](../README.md).

---

## 24. Bezug zum BookShop

| Thema dieser Einheit | Im BookShop |
|----------------------|-------------|
| JPA-Starter | `spring-boot-starter-data-jpa` |
| H2 | Test-Scope / Profil `test` |
| Runtime-DB | MySQL + Flyway, `ddl-auto=validate` |
| Entity | `BookEntity`, `AuthorEntity` |
| Repository | `BookRepository`, `AuthorRepository` |
| DTOs | `BookRequest` / `BookResponse` |
| Transaktionen | `@Transactional` auf `BookService` |
| Dirty Checking | `update(...)` ohne explizites `save` |
| ISBN-Konflikt | `existsByIsbn` + `DuplicateIsbnException` → 409 |
| Derived Queries | z. B. `findByAuthorId` |

Diese Datei hält die **Lehrversion mit H2 In-Memory** fest; der laufende BookShop-Code ist die fortgeschrittene Variante derselben Kette.
