# 1:n-Beziehung: Autor und Bücher

Lern-Einheit: die relationale Struktur mit JPA abbilden —

```text
Ein Autor → mehrere Bücher
Ein Buch → genau ein Autor
```

Vorher: [05-jpa-h2-persistenz.md](05-jpa-h2-persistenz.md)  
Nächste Einheit: [07-automatisierte-tests.md](07-automatisierte-tests.md)  
Danach: Schema-Versionierung mit Flyway  
Projektübersicht: [README](../README.md)

Im BookShop entspricht diese Einheit dem **Entwicklungsschritt 6** (Author–Book ManyToOne).

Das Handbuch behandelt dafür JPA-Entities, Beziehungen, Lazy Loading, Repositories und Transaktionen.

---

## Lernziele

* `@ManyToOne` und `@JoinColumn` verstehen
* `FetchType.LAZY` und Mapping innerhalb von Transaktionen
* Request mit `authorId` statt eingebettetem Autorobjekt
* Response mit verschachteltem `AuthorResponse`
* Autor zuerst laden, bevor ein Buch gespeichert wird
* `GET /books?authorId=` als Derived Query

---

## Inhaltsverzeichnis

1. [Zielmodell](#1-zielmodell)
2. [`AuthorEntity`](#2-authorentity)
3. [`BookEntity` anpassen](#3-bookentity-anpassen)
4. [`@ManyToOne`](#4-manytoone)
5. [Warum `LAZY`?](#5-warum-lazy)
6. [Author-Repository](#6-author-repository)
7. [Request-DTO](#7-request-dto)
8. [Response-DTOs](#8-response-dtos)
9. [Warum keine Entities nach außen?](#9-warum-keine-entities-nach-außen)
10. [Autor-Exception](#10-autor-exception)
11. [`BookService` erweitern](#11-bookservice-erweitern)
12. [Warum zuerst den Autor laden?](#12-warum-zuerst-den-autor-laden)
13. [Autor-API](#13-autor-api)
14. [Ablauf testen](#14-ablauf-testen)
15. [Kontrolle in der Datenbank](#15-kontrolle-in-der-datenbank)
16. [Tabellen- und Spaltennamen](#16-tabellen--und-spaltennamen)
17. [MySQL-Konfiguration](#17-mysql-konfiguration)
18. [Lernkontrolle](#18-lernkontrolle)
19. [Praxisaufgabe: Bücher nach Autor](#19-praxisaufgabe-bücher-nach-autor)
20. [Bezug zum BookShop](#20-bezug-zum-bookshop)

---

## 1. Zielmodell

```text
authors
┌────┬──────────────────┐
│ id │ name             │
├────┼──────────────────┤
│ 1  │ Robert C. Martin │
└────┴──────────────────┘

books
┌────┬────────────┬───────────┐
│ id │ title      │ author_id │
├────┼────────────┼───────────┤
│ 1  │ Clean Code │ 1         │
└────┴────────────┴───────────┘
```

Auf Java-Seite:

```text
BookEntity ── many-to-one ──> AuthorEntity
```

---

## 2. `AuthorEntity`

```java
package com.example.demo.author;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    protected AuthorEntity() {
        // Für JPA
    }

    public AuthorEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }
}
```

Zunächst nur die Richtung **Buch → Autor**. Eine Collection `List<BookEntity>` im Autor ist nicht nötig — das hält das Modell einfach.

---

## 3. `BookEntity` anpassen

```java
package com.example.demo.book;

import com.example.demo.author.AuthorEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    protected BookEntity() {
        // Für JPA
    }

    public BookEntity(String title, AuthorEntity author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void update(String title, AuthorEntity author) {
        this.title = title;
        this.author = author;
    }
}
```

(ISBN ggf. wie in der Praxisaufgabe aus [05](05-jpa-h2-persistenz.md) ergänzen.)

---

## 4. `@ManyToOne`

```java
@ManyToOne(fetch = FetchType.LAZY, optional = false)
```

Bedeutung:

* Viele Bücher können denselben Autor referenzieren.
* Ein Buch **muss** einen Autor haben (`optional = false`).
* Der Autor wird zunächst verzögert geladen.

Fremdschlüssel:

```java
@JoinColumn(name = "author_id")
```

```text
BookEntity.author
        ↓
books.author_id
        ↓
authors.id
```

---

## 5. Warum `LAZY`?

Bei `bookRepository.findAll()` muss Hibernate nicht zwingend alle Autorendaten sofort laden.

Mit `LAZY` erst beim Zugriff:

```java
book.getAuthor().getName();
```

Das kann unnötige DB-Zugriffe vermeiden. Mapping zum DTO sollte deshalb **innerhalb einer Transaktion** erfolgen (sonst LazyInitializationException außerhalb der Session).

---

## 6. Author-Repository

```java
package com.example.demo.author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository
        extends JpaRepository<AuthorEntity, Long> {
}
```

Optional später:

```java
boolean existsByNameIgnoreCase(String name);
Optional<AuthorEntity> findByNameIgnoreCase(String name);
```

---

## 7. Request-DTO

Der Client sendet die **Autor-ID**, nicht das komplette Autorobjekt:

```java
package com.example.demo.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookRequest(

        @NotBlank(message = "Der Titel darf nicht leer sein.")
        @Size(max = 200, message = "Der Titel darf höchstens 200 Zeichen enthalten.")
        String title,

        @NotNull(message = "Die Autor-ID ist erforderlich.")
        @Positive(message = "Die Autor-ID muss größer als 0 sein.")
        Long authorId
) {
}
```

```json
{
  "title": "Clean Code",
  "authorId": 1
}
```

---

## 8. Response-DTOs

### Autor

```java
package com.example.demo.author;

public record AuthorResponse(Long id, String name) {
    public static AuthorResponse from(AuthorEntity entity) {
        return new AuthorResponse(entity.getId(), entity.getName());
    }
}
```

### Buch

```java
package com.example.demo.book;

import com.example.demo.author.AuthorResponse;

public record BookResponse(
        Long id,
        String title,
        AuthorResponse author
) {
    public static BookResponse from(BookEntity entity) {
        return new BookResponse(
                entity.getId(),
                entity.getTitle(),
                AuthorResponse.from(entity.getAuthor())
        );
    }
}
```

API-Antwort:

```json
{
  "id": 1,
  "title": "Clean Code",
  "author": {
    "id": 1,
    "name": "Robert C. Martin"
  }
}
```

---

## 9. Warum keine Entities nach außen?

Technisch möglich, aber problematisch:

* DB-Modell und API eng gekoppelt
* Lazy-Proxys stören Serialisierung
* bidirektionale Beziehungen → Endlosschleifen
* interne Felder können nach außen gelangen
* Entity-Änderungen ändern die API automatisch

Sauber:

```text
Request DTO → Entity → Response DTO
```

---

## 10. Autor-Exception

```java
package com.example.demo.author;

public class AuthorNotFoundException extends RuntimeException {

    private final Long authorId;

    public AuthorNotFoundException(Long authorId) {
        super("Autor mit ID " + authorId + " wurde nicht gefunden.");
        this.authorId = authorId;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
```

Im `GlobalExceptionHandler`:

```java
@ExceptionHandler(AuthorNotFoundException.class)
public ProblemDetail handleAuthorNotFound(AuthorNotFoundException exception) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            exception.getMessage()
    );
    problem.setTitle("Autor nicht gefunden");
    problem.setProperty("authorId", exception.getAuthorId());
    return problem;
}
```

---

## 11. `BookService` erweitern

```java
package com.example.demo.book;

import com.example.demo.author.AuthorEntity;
import com.example.demo.author.AuthorNotFoundException;
import com.example.demo.author.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(
            BookRepository bookRepository,
            AuthorRepository authorRepository
    ) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookResponse> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::from)
                .toList();
    }

    public BookResponse findById(Long id) {
        return BookResponse.from(findBookEntity(id));
    }

    @Transactional
    public BookResponse create(BookRequest request) {
        AuthorEntity author = findAuthorEntity(request.authorId());
        BookEntity book = new BookEntity(request.title(), author);
        return BookResponse.from(bookRepository.save(book));
    }

    @Transactional
    public BookResponse update(Long id, BookRequest request) {
        BookEntity book = findBookEntity(id);
        AuthorEntity author = findAuthorEntity(request.authorId());
        book.update(request.title(), author);
        return BookResponse.from(book);
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.delete(findBookEntity(id));
    }

    private BookEntity findBookEntity(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    private AuthorEntity findAuthorEntity(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }
}
```

---

## 12. Warum zuerst den Autor laden?

```java
AuthorEntity author = authorRepository.findById(request.authorId())
        .orElseThrow(...);

BookEntity book = new BookEntity(request.title(), author);
```

Der Service prüft:

* Existiert der Autor?
* Ist die Beziehung gültig?
* Kann das Buch gespeichert werden?

Unbekannte Autor-ID → `404 Not Found`.

---

## 13. Autor-API

### Request

```java
public record AuthorRequest(
        @NotBlank(message = "Der Name darf nicht leer sein.")
        @Size(max = 150, message = "Der Name darf höchstens 150 Zeichen enthalten.")
        String name
) {
}
```

### Service

```java
@Service
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorResponse> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorResponse::from)
                .toList();
    }

    @Transactional
    public AuthorResponse create(AuthorRequest request) {
        AuthorEntity author = new AuthorEntity(request.name());
        return AuthorResponse.from(authorRepository.save(author));
    }
}
```

### Controller

```java
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorResponse> findAll() {
        return authorService.findAll();
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> create(
            @Valid @RequestBody AuthorRequest request
    ) {
        AuthorResponse author = authorService.create(request);
        URI location = URI.create("/authors/" + author.id());
        return ResponseEntity.created(location).body(author);
    }
}
```

---

## 14. Ablauf testen

### Autor anlegen

```bash
curl -i \
  -X POST \
  -H "Content-Type: application/json" \
  -d '{"name":"Robert C. Martin"}' \
  http://localhost:8080/authors
```

```json
{
  "id": 1,
  "name": "Robert C. Martin"
}
```

### Buch anlegen

```bash
curl -i \
  -X POST \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","authorId":1}' \
  http://localhost:8080/books
```

```json
{
  "id": 1,
  "title": "Clean Code",
  "author": {
    "id": 1,
    "name": "Robert C. Martin"
  }
}
```

### Unbekannten Autor

```bash
curl -i \
  -X POST \
  -H "Content-Type: application/json" \
  -d '{"title":"Unbekanntes Buch","authorId":999}' \
  http://localhost:8080/books
```

```text
HTTP/1.1 404 Not Found
```

```json
{
  "type": "about:blank",
  "title": "Autor nicht gefunden",
  "status": 404,
  "detail": "Autor mit ID 999 wurde nicht gefunden.",
  "authorId": 999
}
```

> **BookShop:** Lesen mit `reader`/`admin`, Schreiben mit `admin`.

---

## 15. Kontrolle in der Datenbank

Nach erfolgreichem POST:

```text
authors:  id=1, name=Robert C. Martin
books:    id=1, title=Clean Code, author_id=1
```

Join:

```sql
SELECT
    b.id,
    b.title,
    a.name AS author
FROM books b
JOIN authors a ON a.id = b.author_id;
```

```text
id | title      | author
1  | Clean Code | Robert C. Martin
```

(phpMyAdmin, H2-Konsole oder anderes SQL-Tool.)

---

## 16. Tabellen- und Spaltennamen

Groß-/Kleinschreibung kann je nach OS und MySQL-Einstellungen relevant sein. Explizite Namen reduzieren Überraschungen:

```java
@Table(name = "books")
@Column(name = "title")
@JoinColumn(name = "author_id")
@Table(name = "authors")
```

---

## 17. MySQL-Konfiguration

Beispiel (Lernbetrieb):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jpadb
spring.datasource.username=root
spring.datasource.password=DEIN_PASSWORT

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

`ddl-auto=update` ist praktisch zum Lernen. Später:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

plus Flyway/Liquibase — siehe [08-flyway-schema-versionierung.md](08-flyway-schema-versionierung.md); im BookShop bereits Schritt 7.

---

## 18. Lernkontrolle

| Frage | Kurzantwort |
|-------|-------------|
| Welche Seite hat den FK? | `books.author_id` |
| Welche Annotation? | `@ManyToOne` |
| Was macht `@JoinColumn`? | Attribut `author` ↔ Spalte `author_id` |
| Warum Autor im Service laden? | Existenz prüfen + managed Instanz zuordnen |
| Warum weiterhin DTOs? | DB-Modell und REST-API trennen |
| Warum `@Transactional`? | Laden/Ändern/Mapping in einer Transaktion |

---

## 19. Praxisaufgabe: Bücher nach Autor

Repository:

```java
List<BookEntity> findByAuthorId(Long authorId);
```

Service:

```java
public List<BookResponse> findByAuthorId(Long authorId) {
    if (!authorRepository.existsById(authorId)) {
        throw new AuthorNotFoundException(authorId);
    }
    return bookRepository.findByAuthorId(authorId)
            .stream()
            .map(BookResponse::from)
            .toList();
}
```

Controller:

```java
@GetMapping(params = "authorId")
public List<BookResponse> findByAuthorId(@RequestParam Long authorId) {
    return bookService.findByAuthorId(authorId);
}
```

```text
GET /books?authorId=1
```

### Nächster Schritt

Automatisierte Tests der Schichten — siehe [07-automatisierte-tests.md](07-automatisierte-tests.md):

* Repository mit MySQL/Testcontainers
* Service mit Mockito
* Controller mit `@WebMvcTest` und `MockMvc`

---

## 20. Bezug zum BookShop

| Thema dieser Einheit | Im BookShop |
|----------------------|-------------|
| `AuthorEntity` / `BookEntity` | `author/`, `book/` mit `@ManyToOne` LAZY |
| `authorId` im Request | `BookRequest.authorId` (+ ISBN) |
| Nested Response | `BookResponse` enthält `AuthorResponse` |
| Autor-API | `AuthorController` `/authors` |
| `AuthorNotFoundException` | im `GlobalExceptionHandler` |
| Bücher nach Autor | `GET /books?authorId=` |
| Schema | Flyway + `ddl-auto=validate` (nicht `update`) |

Diese Datei hält die **Lehrversion der 1:n-Beziehung** fest; der laufende Code ist die fortgeschrittene Variante.
