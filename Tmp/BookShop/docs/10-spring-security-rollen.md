# Spring Security mit Rollen

Lern-Einheit: die REST-API absichern — Authentifizierung und Autorisierung trennen.

```text
Authentifizierung → Wer stellt die Anfrage?
Autorisierung     → Was darf dieser Benutzer?
```

Vorher: [09-logging-health-monitoring.md](09-logging-health-monitoring.md)  
Danach: Benutzer und Rollen in MySQL  
Projektübersicht / API-Regeln: [README](../README.md)

Im BookShop entspricht diese Einheit dem **Entwicklungsschritt 9**.

Für den Einstieg:

* HTTP Basic Authentication
* zwei Benutzer im Arbeitsspeicher
* Rollen `READER` und `ADMIN`
* geschützte Actuator-Endpunkte

Später: In-Memory-Benutzer durch MySQL ersetzen.

Quellen: [Spring Boot Security](https://docs.spring.io/spring-boot/reference/web/spring-security.html), [Basic Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/basic.html), [RFC 7617](https://datatracker.ietf.org/doc/html/rfc7617).

---

## Lernziele

* `SecurityFilterChain` und Zugriffsregeln verstehen
* HTTP Basic + Stateless REST
* `401` vs. `403`
* Rollen / `ROLE_`-Präfix
* Actuator-Regeln mit `EndpointRequest`
* Security in `@WebMvcTest` mit `httpBasic(...)`

---

## Inhaltsverzeichnis

1. [Dependencies](#1-dependencies)
2. [SecurityConfig](#2-securityconfig)
3. [Bedeutung der Konfiguration](#3-bedeutung-der-konfiguration)
4. [Warum CSRF deaktivieren?](#4-warum-csrf-deaktivieren)
5. [Passwörter speichern](#5-passwörter-speichern)
6. [API testen](#6-api-testen)
7. [Aktueller Benutzer](#7-aktueller-benutzer)
8. [`401` und `403`](#8-401-und-403)
9. [Reihenfolge der Regeln](#9-reihenfolge-der-regeln)
10. [Security-Tests](#10-security-tests)
11. [Ergebnis](#11-ergebnis)
12. [Bezug zum BookShop](#12-bezug-zum-bookshop)

---

## 1. Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

Ohne eigene Konfiguration schützt Boot die gesamte Webanwendung und erzeugt einen User `user` mit zufälligem Passwort im Startlog.

---

## 2. SecurityConfig

```text
src/main/java/com/example/demo/security/SecurityConfig.java
```

```java
package com.example.demo.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(EndpointRequest.to("health", "info")).permitAll()
                        .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/books/**", "/authors/**")
                        .hasAnyRole("READER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/books/**", "/authors/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/books/**", "/authors/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/books/**", "/authors/**")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails reader = User.builder()
                .username("reader")
                .password(passwordEncoder.encode("reader-pass"))
                .roles("READER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin-pass"))
                .roles("READER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(reader, admin);
    }
}
```

Eine eigene `SecurityFilterChain` ersetzt die Boot-Standardregeln. `EndpointRequest` matcht Actuator-Endpunkte auch bei geändertem Basispfad.

---

## 3. Bedeutung der Konfiguration

### Stateless

Keine Server-Session — jeder Request trägt Credentials erneut (passt zu REST + HTTP Basic).

### HTTP Basic

```http
Authorization: Basic <Base64>
```

Spring authentifiziert und legt ein `Authentication`-Objekt im `SecurityContext` ab. Base64 ist **keine** Verschlüsselung — außerhalb lokaler Entwicklung nur über **HTTPS**.

| Benutzer | Passwort | Rollen |
|----------|----------|--------|
| `reader` | `reader-pass` | `READER` |
| `admin` | `admin-pass` | `READER`, `ADMIN` |

---

## 4. Warum CSRF deaktivieren?

CSRF ist vor allem relevant, wenn der Browser Cookies/Sessions automatisch mitschickt.

Unsere API ist stateless für Clients wie `curl` / Postman. CSRF nur dann dauerhaft aus, wenn die Clients **nicht** browserbasiert mit Cookie-Auth arbeiten. Späteres Browser-Frontend → Entscheidung neu bewerten.

---

## 5. Passwörter speichern

`DelegatingPasswordEncoder` speichert Hashes mit Präfix, z. B. `{bcrypt}$2a$10$...`. Passwörter werden nicht entschlüsselt, sondern gegen den Hash geprüft.

Klartext in der Config nur für die Übung — später Hashes in der Datenbank.

---

## 6. API testen

### Ohne Anmeldung

```bash
curl -i http://localhost:8080/books
```

→ `401 Unauthorized` + `WWW-Authenticate: Basic`

### Reader lesen

```bash
curl -i -u reader:reader-pass http://localhost:8080/books
```

→ `200 OK`

### Reader schreiben

```bash
curl -i -u reader:reader-pass -X POST \
  -H "Content-Type: application/json" \
  -d '{"title":"Effective Java","authorId":1}' \
  http://localhost:8080/books
```

→ `403 Forbidden` (authentifiziert, aber keine `ADMIN`-Rolle)

### Admin schreiben

```bash
curl -i -u admin:admin-pass -X POST \
  -H "Content-Type: application/json" \
  -d '{"title":"Effective Java","authorId":1}' \
  http://localhost:8080/books
```

→ `201 Created` + `Location`

### Health öffentlich

```bash
curl -i http://localhost:8080/actuator/health
```

### Metrics nur Admin

| Aufruf | Erwartung |
|--------|-----------|
| ohne Auth | `401` |
| `reader` | `403` |
| `admin` | `200` |

Mit eigener `SecurityFilterChain` musst du Actuator-Regeln selbst setzen — die automatische Actuator-Sicherung greift dann nicht mehr.

---

## 7. Aktueller Benutzer

```java
@RestController
@RequestMapping("/security")
public class CurrentUserController {

    @GetMapping("/me")
    public CurrentUserResponse currentUser(Authentication authentication) {
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return new CurrentUserResponse(authentication.getName(), roles);
    }

    public record CurrentUserResponse(String username, List<String> authorities) {
    }
}
```

```bash
curl -u admin:admin-pass http://localhost:8080/security/me
```

```json
{
  "username": "admin",
  "authorities": ["ROLE_ADMIN", "ROLE_READER"]
}
```

`roles("ADMIN")` → Authority `ROLE_ADMIN`. Deshalb matcht `.hasRole("ADMIN")` intern auf `ROLE_ADMIN`.

---

## 8. `401` und `403`

| Status | Bedeutung | Beispiel |
|--------|-----------|----------|
| `401 Unauthorized` | nicht / falsch authentifiziert | kein oder falsches Passwort |
| `403 Forbidden` | authentifiziert, nicht berechtigt | Reader löscht ein Buch |

Der Name „Unauthorized“ ist historisch etwas irreführend — fachlich oft fehlende Authentifizierung.

---

## 9. Reihenfolge der Regeln

```text
spezifische Regeln
        ↓
allgemeine Regeln
        ↓
anyRequest()
```

`anyRequest()` zuerst würde die folgenden Matcher wirkungslos machen.

---

## 10. Security-Tests

```java
@WebMvcTest(BookController.class)
@Import({ SecurityConfig.class, GlobalExceptionHandler.class })
class BookControllerTest {
```

```java
import static org.springframework.security.test.web.servlet.request
        .SecurityMockMvcRequestPostProcessors.httpBasic;
```

Beispiele:

* ohne Auth → `401`, `verifyNoInteractions(bookService)`
* Reader + GET → `200`
* Reader + POST → `403`, kein Service-Aufruf
* Admin + POST → `201` + `Location`

`httpBasic(...)` setzt den korrekten `Authorization`-Header ([Testing HTTP Basic](https://docs.spring.io/spring-security/reference/servlet/test/mockmvc/http-basic.html)).

---

## 11. Ergebnis

```text
HTTP Request
  → Security Filter Chain
  → Authentifizierung
  → Autorisierung
  → Controller → Service → Repository → MySQL
```

Verantwortung:

| Baustein | Aufgabe |
|----------|---------|
| `SecurityFilterChain` | Welche URL darf welche Rolle? |
| `UserDetailsService` | Welche Benutzer gibt es? |
| `PasswordEncoder` | Wie werden Passwörter geprüft? |
| Controller | Fachliche Ausführung |

**Nächster Ausbau:** Benutzer, Passwort-Hashes und Rollen in MySQL; eigener `UserDetailsService`.

---

## 12. Bezug zum BookShop

| Thema dieser Einheit | Im BookShop |
|----------------------|-------------|
| Config | `security/SecurityConfig.java` |
| Benutzer | `reader` / `admin` In-Memory |
| `/security/me` | `CurrentUserController` |
| Actuator | health/info öffentlich, metrics → ADMIN |
| Tests | `BookControllerTest`, `AuthorControllerTest` mit `httpBasic` |
| ISBN im Request | bleibt Pflichtfeld (`BookRequest`) |

Weitere curl-Beispiele und API-Tabelle: [README Abschnitt Security](../README.md#8-entwicklungsschritt-9--spring-security-mit-rollen).
