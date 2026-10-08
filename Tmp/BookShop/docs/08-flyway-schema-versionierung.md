# Datenbankschema mit Flyway versionieren

Lern-Einheit: Schemaänderungen nachvollziehbar im Git-Repository halten — statt Hibernate das Schema still zu verändern.

```text
Java-Entity beschreibt das Objektmodell
Flyway verwaltet das Datenbankschema
Hibernate prüft, ob beides zusammenpasst
```

Vorher: [07-automatisierte-tests.md](07-automatisierte-tests.md)  
Nächste Einheit: [09-logging-health-monitoring.md](09-logging-health-monitoring.md)  
Danach: Spring Security  
Projektübersicht: [README](../README.md)

Im BookShop entspricht diese Einheit dem **Entwicklungsschritt 7** (`validate` + Flyway-Migrationen).

Das Handbuch behandelt Flyway als Werkzeug für inkrementelle Datenbankmigrationen.

---

## Lernziele

* `ddl-auto=update` durch `validate` + Flyway ersetzen
* Migrationsdateien und Namensschema verstehen
* Entity und SQL-Migration abgleichen
* bestehende DB: Neuaufbau vs. Baseline
* Prüfsummen und die Regel „ausgeführte Migrationen nicht ändern“
* Zusammenspiel mit Testcontainers

---

## Inhaltsverzeichnis

1. [Abhängigkeiten](#1-abhängigkeiten)
2. [Hibernate auf Validierung](#2-hibernate-auf-validierung)
3. [Migrationsverzeichnis](#3-migrationsverzeichnis)
4. [Erste Migration](#4-erste-migration)
5. [Entity und Migration abgleichen](#5-entity-und-migration-abgleichen)
6. [Bestehende Datenbank](#6-bestehende-datenbank)
7. [Startdaten in Migrationen](#7-startdaten-in-migrationen)
8. [Historie ansehen](#8-historie-ansehen)
9. [Zentrale Regel](#9-zentrale-regel)
10. [Nächste Schemaänderung](#10-nächste-schemaänderung)
11. [Testcontainers und Flyway](#11-testcontainers-und-flyway)
12. [Typische Fehler](#12-typische-fehler)
13. [Lernkontrolle](#13-lernkontrolle)
14. [Konkrete Aufgabe](#14-konkrete-aufgabe)
15. [Bezug zum BookShop](#15-bezug-zum-bookshop)

---

## Ausgangslage

Bisher oft:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Bequem in der Entwicklung — professionell sollten Schemaänderungen versioniert und reproduzierbar sein.

---

## 1. Abhängigkeiten

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>

<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-mysql</artifactId>
</dependency>
```

Für MySQL braucht es zusätzlich zu `flyway-core` das Modul `flyway-mysql`. Spring Boot führt Migrationen beim Start automatisch aus.

MySQL-Treiber bleibt (Runtime).

Quellen: [Spring Boot Database Initialization](https://docs.spring.io/spring-boot/3.4/how-to/data-initialization.html), [Flyway MySQL](https://documentation.red-gate.com/fd/mysql-277579322.html).

---

## 2. Hibernate auf Validierung

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jpadb
spring.datasource.username=root
spring.datasource.password=DEIN_PASSWORT

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
```

Entscheidend:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

Hibernate **ändert** die DB nicht mehr. Beim Start prüft es u. a.:

* Tabellen vorhanden?
* Spalten vorhanden?
* Datentypen grundsätzlich kompatibel?
* Fremdschlüsselspalten / Beziehungen?

Nur **einen** Mechanismus für Schemaerzeugung nutzen: bei Flyway nicht parallel Hibernate-DDL, `schema.sql` o. Ä. das Schema verändern lassen.

---

## 3. Migrationsverzeichnis

```text
src/main/resources/
└── db/
    └── migration/
        └── V1__create_initial_schema.sql
```

Standardort: `classpath:db/migration`.

Namensschema:

```text
V<VERSION>__<BESCHREIBUNG>.sql
```

Beispiele:

```text
V1__create_initial_schema.sql
V2__insert_reference_data.sql
V3__add_isbn_to_books.sql
V4_1__create_book_indexes.sql
```

Zwischen Version und Beschreibung: **zwei Unterstriche**.

---

## 4. Erste Migration

`V1__create_initial_schema.sql`:

```sql
CREATE TABLE authors (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,

    CONSTRAINT pk_authors
        PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    author_id BIGINT NOT NULL,

    CONSTRAINT pk_books
        PRIMARY KEY (id),

    CONSTRAINT fk_books_author
        FOREIGN KEY (author_id)
        REFERENCES authors (id),

    INDEX idx_books_author_id (author_id)
) ENGINE = InnoDB;
```

Index auf `author_id` unterstützt z. B. `WHERE author_id = 1`.

---

## 5. Entity und Migration abgleichen

### `AuthorEntity` (Auszug)

```java
@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;
    // ...
}
```

### `BookEntity` (Auszug)

```java
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;
    // ...
}
```

| Migration | Entity |
|-----------|--------|
| `authors` | `@Table(name = "authors")` |
| `books` | `@Table(name = "books")` |
| `VARCHAR(150)` | `length = 150` |
| `VARCHAR(200)` | `length = 200` |
| `author_id` | `@JoinColumn(name = "author_id")` |
| `AUTO_INCREMENT` | `GenerationType.IDENTITY` |

---

## 6. Bestehende Datenbank

Existieren Tabellen schon, ohne `flyway_schema_history`, scheitert Flyway oft mit:

```text
Non-empty schema without schema history table
```

Siehe [Flyway Error Codes](https://documentation.red-gate.com/fd/error-codes-277579361.html).

### Weg A: Lern-DB neu aufbauen (bevorzugt ohne wichtige Daten)

```sql
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
```

Reihenfolge wichtig wegen FK. Dann:

```powershell
.\mvnw.cmd spring-boot:run
```

Flyway führt `V1` aus. Erwartete Tabellen:

```text
authors
books
flyway_schema_history
```

### Weg B: Bestehendes Schema übernehmen

```properties
spring.flyway.baseline-on-migrate=true
spring.flyway.baseline-version=1
```

Sinngemäß: vorhandenes Schema = Version 1; nur Migrationen ab **V2** laufen künftig. `V1` wird auf dieser DB nicht ausgeführt — auf einer leeren DB schon.

Danach `baseline-on-migrate` wieder `false` setzen oder entfernen.

Hinweis: `baseline-on-migrate` ist praktisch, schwächt aber eine Schutzmaßnahme — vorsichtig einsetzen ([Baseline On Migrate](https://documentation.red-gate.com/fd/flyway-baseline-on-migrate-setting-277578974.html)).

Für Lernprojekte ohne erhaltenswerte Daten: **Weg A**.

---

## 7. Startdaten in Migrationen

`CommandLineRunner`-Initialisierer optional entfernen. Stattdessen z. B.:

```text
V2__insert_demo_data.sql
```

```sql
INSERT INTO authors (name)
VALUES ('Robert C. Martin');

INSERT INTO authors (name)
VALUES ('Christian Ullenboom');

INSERT INTO books (title, author_id)
SELECT 'Clean Code', id
FROM authors
WHERE name = 'Robert C. Martin';

INSERT INTO books (title, author_id)
SELECT 'Java ist auch eine Insel', id
FROM authors
WHERE name = 'Christian Ullenboom';
```

Beim ersten Start: `V1` dann `V2`. Wenn `V1` schon angewendet: nur noch `V2`.

---

## 8. Historie ansehen

Tabelle `flyway_schema_history` (z. B. in phpMyAdmin):

| installed_rank | version | description | script | success |
| ---: | ---: | --- | --- | ---: |
| 1 | 1 | create initial schema | `V1__create_initial_schema.sql` | 1 |
| 2 | 2 | insert demo data | `V2__insert_demo_data.sql` | 1 |

Flyway erkennt darüber: welche Migrationen gelaufen sind, was als Nächstes ansteht, Erfolg, und ob eine Datei nachträglich verändert wurde (Prüfsumme). Siehe [Schema History Table](https://documentation.red-gate.com/fd/flyway-schema-history-table-273973417.html).

---

## 9. Zentrale Regel

Eine auf einer dauerhaften DB ausgeführte Migration **nicht mehr ändern**.

Falsch: `V1` nachträglich editieren.  
Richtig: neue Datei, z. B. `V3__add_isbn_to_books.sql`.

Nachträgliche Änderungen → oft **checksum mismatch** ([Validate](https://documentation.red-gate.com/fd/validate-277578898.html)).

---

## 10. Nächste Schemaänderung

Beispiel ISBN:

```text
V3__add_isbn_to_books.sql
```

```sql
ALTER TABLE books
    ADD COLUMN isbn VARCHAR(17) NULL;
```

Entity:

```java
@Column(name = "isbn", length = 17)
private String isbn;
```

Zuerst `NULL`, falls Bestandsdaten ohne ISBN existieren. Später:

```text
V4__make_book_isbn_required.sql
```

```sql
ALTER TABLE books
    MODIFY isbn VARCHAR(17) NOT NULL;

CREATE UNIQUE INDEX uk_books_isbn
    ON books (isbn);
```

```text
V1: Grundschema
V2: Daten
V3: ISBN-Spalte
V4: ISBN verpflichtend und eindeutig
```

---

## 11. Testcontainers und Flyway

```text
MySQL-Container startet
        ↓
Flyway führt V1, V2, V3 ... aus
        ↓
Hibernate validiert die Entities
        ↓
@DataJpaTest führt Repository-Tests aus
```

So testest du JPA **und**, ob Migrationen eine lauffähige DB erzeugen.

Im Test weiterhin:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

---

## 12. Typische Fehler

| Fehler | Typische Ursache |
|--------|------------------|
| `Unsupported Database: MySQL` | `flyway-mysql` fehlt |
| `Schema-validation: missing table` | Migration nicht gelaufen / falsches Verzeichnis |
| `Detected applied migration not resolved locally` | Datei gelöscht oder umbenannt |
| `Migration checksum mismatch` | Ausgeführte Migration geändert |
| Tabelle existiert bereits | Flyway ohne Baseline auf bestehende DB |

Richtiges Verzeichnis: `src/main/resources/db/migration/`.

---

## 13. Lernkontrolle

| Frage | Kurzantwort |
|-------|-------------|
| Aufgabe von Flyway? | Schema per versionierter Migrationen erzeugen/ändern |
| Aufgabe von Hibernate jetzt? | Entities abbilden + Schema validieren |
| Warum nicht `ddl-auto=update`? | Keine saubere Versionierung/Reproduzierbarkeit |
| Was ist `flyway_schema_history`? | Ausgeführte Migrationen inkl. Prüfsumme |
| Warum `V1` nicht ändern? | Andere Umgebungen + Checksum-Validierung |
| Neue Spalte? | Neue Migration, z. B. `V3__add_isbn_to_books.sql` |

---

## 14. Konkrete Aufgabe

1. Flyway-Abhängigkeiten ergänzen  
2. `ddl-auto` auf `validate` setzen  
3. `db/migration` anlegen  
4. `V1__create_initial_schema.sql` erstellen  
5. Bei entbehrlichen Daten Tabellen löschen  
6. Anwendung starten  
7. `flyway_schema_history` prüfen  
8. Autor und Buch über REST anlegen  
9. Neu starten und prüfen: Tabellen und Daten bleiben  

### Nächster Schritt

**Spring Boot Actuator**, strukturierte Logs und Health Checks — siehe [09-logging-health-monitoring.md](09-logging-health-monitoring.md). Im BookShop: Schritt 8.

---

## 15. Bezug zum BookShop

| Thema dieser Einheit | Im BookShop |
|----------------------|-------------|
| Dependencies | `flyway-core`, `flyway-mysql` |
| `ddl-auto` | `validate` |
| Migrationen | `src/main/resources/db/migration/` |
| Historie | `flyway_schema_history` in MySQL |
| Tests | gleiche Migrationen unter Profil `test` (H2) |
| ISBN / Erweiterungen | weitere `V…`-Dateien statt Entity-DDL |

Quellen (Auswahl):

* [Spring Boot Database Initialization](https://docs.spring.io/spring-boot/3.4/how-to/data-initialization.html)
* [Flyway Error Codes](https://documentation.red-gate.com/fd/error-codes-277579361.html)
* [Baseline On Migrate](https://documentation.red-gate.com/fd/flyway-baseline-on-migrate-setting-277578974.html)
* [Schema History Table](https://documentation.red-gate.com/fd/flyway-schema-history-table-273973417.html)
* [Validate](https://documentation.red-gate.com/fd/validate-277578898.html)
* [MySQL](https://documentation.red-gate.com/fd/mysql-277579322.html)
