Die Aufgabe dieses Moduls steht in der [README des Elternordners](../README.md).

# Spring Security

## Was dieses Beispiel übt

Eine SecurityFilterChain mit Formular-Login, HTTP Basic und JWT, Rollen und Authorities, Methodensicherheit und einen eigenen Security-Ausdruck. Benutzer und Personen liegen in MySQL.

## Wichtige Klassen in Lesereihenfolge

1. `PersonManagementApplication` und `DataLoader` – Start und Demo-Benutzer
2. `User`, `Role`, `Authority` – Konto und Rechte
3. `CustomUserDetailsService` – lädt das Konto in den SecurityContext
4. `SecurityConfig` – zwei Filterketten: `/api/**` zustandslos mit JWT und HTTP Basic, die Webseiten mit Formular-Login, Session und CSRF
5. `JwtTokenProvider` und `JwtAuthenticationFilter` – Bearer-Token
6. `AuthApiController`, `WebController`, `AdminController`, `PersonController` – die geschützten Einstiege
7. `CustomSecurityExpressions` – Ausdruck `isOwner` für die Methodensicherheit

## Start

In NetBeans `PersonManagementApplication` starten, oder im Modulordner:

```bash
mvn spring-boot:run
```

Die Anwendung lauscht auf Port **8080** (`server.port` in `application.properties`).

## Datenbank

Die Anwendung nutzt MySQL **ibb-db** (Benutzer `ibb-user`, Tabellen `jsprbak03_users`, `jsprbak03_user_authorities`, `jsprbak03_persons`). Benutzer und Passwort der DataSource stehen in `application.properties`.

Beim Start legt `DataLoader` zwei Konten an, falls sie noch fehlen: `admin` / `admin123` (Rolle ADMIN, inklusive `DELETE_PRIVILEGES`) und `user` / `user123` (Rolle USER, ohne Löschrecht).

Tests laufen mit dem Profil `test` auf H2 (`src/test/resources/application-test.properties`), nicht gegen MySQL.

## Regeln, die `SecurityRulesTests` prüft

- `DELETE /api/persons/{id}` verlangt die Authority `DELETE_PRIVILEGES` und zusätzlich Owner oder Admin. `user` bekommt 403.
- Ein Refresh-Token öffnet keine API (401), ein Access-Token erneuert nichts. Der Claim `type` trennt beide.
- Die API liest keine Session. Ein Session-Cookie aus dem Formular-Login meldet unter `/api/**` niemanden an, deshalb braucht die API kein CSRF-Token.
- `GET /api/users/me/full` liefert kein Passwort-Feld.
- Vertrauliche Personen sieht nur ihr Owner oder ein Admin, in der Liste (`@PostFilter`) wie im Einzelabruf (`@PostAuthorize`).
- `POST /api/persons/addPerson` validiert den Body (400) und ignoriert eine mitgeschickte `id`.
- Eine fehlende Person liefert 404, nicht 500.

```bash
mvn test
```

## Ausprobieren

1. http://localhost:8080/login öffnen und sich als `user` / `user123` anmelden. Die Personenseite ist erreichbar, http://localhost:8080/admin/dashboard nicht.
2. Dieselbe Admin-Seite mit `admin` / `admin123` öffnen.
3. Ein Token holen und die API damit aufrufen:

```bash
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d "{\"username\":\"user\",\"password\":\"user123\"}"
```

Den `accessToken` als `Authorization: Bearer ...` an `GET http://localhost:8080/api/users/me` hängen.
