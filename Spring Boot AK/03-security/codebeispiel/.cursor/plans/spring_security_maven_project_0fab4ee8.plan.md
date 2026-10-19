---
name: Spring Security Maven Project
overview: Create a complete Maven project combining Tag 4 (Authentication) and Tag 5 (Authorization) Spring Security tutorials with User entity, BCrypt, JWT, Method Security, and Thymeleaf login pages.
todos:
  - id: pom
    content: Create pom.xml with all dependencies
    status: completed
  - id: entities
    content: Create entity classes (User, Role, Authority, Person)
    status: completed
  - id: repositories
    content: Create repository interfaces
    status: completed
  - id: security
    content: Create security config, UserDetailsService, JWT classes, CustomSecurityExpressions
    status: completed
  - id: services
    content: Create UserService and PersonService with @PreAuthorize
    status: completed
  - id: controllers
    content: Create all controllers (Web, Person, Admin, User, AuthApi)
    status: completed
  - id: templates
    content: Create Thymeleaf templates (login, persons, admin pages)
    status: completed
  - id: config
    content: Create application.properties and DataLoader
    status: completed
  - id: test
    content: Create basic test class
    status: completed
isProject: false
---

# Spring Security Maven Project (Tag 4 + Tag 5)

Create a runnable Maven project in `c:\Users\Trainer\Documents\NetBeansProjects\Spring Boot AK\04-spring-security` combining both tutorials.

## Project Structure

```
04-spring-security/
├── pom.xml
├── src/main/java/de/ibb/boot/ak/security/
│   ├── PersonManagementApplication.java
│   ├── entity/
│   │   ├── User.java
│   │   ├── Role.java
│   │   ├── Authority.java
│   │   └── Person.java
│   ├── repository/
│   │   ├── UserRepository.java
│   │   └── PersonRepository.java
│   ├── service/
│   │   ├── UserService.java
│   │   └── PersonService.java
│   ├── security/
│   │   ├── SecurityConfig.java
│   │   ├── CustomUserDetailsService.java
│   │   ├── CustomSecurityExpressions.java
│   │   ├── CustomAuthenticationSuccessHandler.java
│   │   ├── CurrentUser.java
│   │   └── jwt/
│   │       ├── JwtTokenProvider.java
│   │       └── JwtAuthenticationFilter.java
│   ├── controller/
│   │   ├── WebController.java
│   │   ├── PersonController.java
│   │   ├── AdminController.java
│   │   ├── UserController.java
│   │   └── AuthApiController.java
│   └── DataLoader.java
├── src/main/resources/
│   ├── application.properties
│   └── templates/
│       ├── login.html
│       ├── persons.html
│       └── admin/
│           ├── dashboard.html
│           └── users.html
└── src/test/java/de/ibb/boot/ak/security/
    └── PersonManagementApplicationTests.java
```

## Key Dependencies (pom.xml)

- Spring Boot 3.2.x parent
- spring-boot-starter-web
- spring-boot-starter-security
- spring-boot-starter-data-jpa
- spring-boot-starter-thymeleaf
- spring-boot-starter-validation
- Lombok
- MariaDB driver (runtime)
- H2 database (for easy testing)
- jjwt-api/impl/jackson 0.12.3 (JWT)

## Key Implementation Details

- **User Entity**: username, password (BCrypt), role (enum), authorities (@ElementCollection), enabled, accountNonLocked
- **SecurityConfig**: URL-based auth (permitAll for /login, /api/auth/**; hasRole ADMIN for /admin/**), @EnableMethodSecurity, JWT filter, session policy STATELESS for API but form-login for web
- **CustomUserDetailsService**: loads from DB, maps role + authorities to GrantedAuthority
- **JWT**: JwtTokenProvider (generate/validate), JwtAuthenticationFilter, AuthApiController (/api/auth/login, /api/auth/refresh)
- **Authorization**: @PreAuthorize on PersonService methods, CustomSecurityExpressions with isOwner/isOwnerOrAdmin
- **DataLoader**: CommandLineRunner creating admin (all authorities) and user (read+write only) with BCrypt passwords
- **application.properties**: MariaDB connection, ddl-auto=update, JWT secret/expiration

## Notes

- The project supports both form-login (Thymeleaf) and stateless JWT (REST API)
- CSRF is disabled for API paths but handled automatically by Thymeleaf for forms
- H2 is included as fallback if MariaDB is not running (configurable via profile)
