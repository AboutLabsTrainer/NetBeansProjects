---
name: Add source comments
overview: Add German Javadoc and targeted inline comments across the starter and demo main sources so the auto-configuration flow and greeting assembly are self-explanatory, matching the existing German documentation style.
todos:
  - id: autoconfig-comments
    content: Document GreetingAutoConfiguration annotations/conditions and AutoConfiguration.imports
    status: completed
  - id: service-comments
    content: Document DefaultGreetingService assembly order; minor GreetingProperties/Service clarifications if needed
    status: completed
  - id: demo-comments
    content: Document DemoApplication, GreetingController, and application.properties
    status: completed
isProject: false
---

# Add self-explanatory source comments

## Approach

Match the existing German Javadoc style already used in [`GreetingService.java`](greeting-spring-boot-starter/src/main/java/de/ibb/greeting/GreetingService.java) and [`GreetingProperties.java`](greeting-spring-boot-starter/src/main/java/de/ibb/greeting/GreetingProperties.java). Comment **what and why** for Spring Boot mechanics and greeting assembly; skip trivial getters/setters and test classes (they already use `@DisplayName`).

## Files to update

### 1. Starter auto-configuration (highest teaching value)

[`GreetingAutoConfiguration.java`](greeting-spring-boot-starter/src/main/java/de/ibb/greeting/GreetingAutoConfiguration.java)

- Expand class Javadoc: role of `@AutoConfiguration`, registration via `AutoConfiguration.imports`, and how properties bind.
- Comment each condition briefly:
  - `@ConditionalOnClass` — only when API is on classpath
  - `@EnableConfigurationProperties` — binds `greeting.*`
  - `@ConditionalOnMissingBean` — user bean overrides default
  - `@ConditionalOnProperty(..., matchIfMissing = true)` — enabled by default when property omitted
- Short method Javadoc on `greetingService(...)`.

[`AutoConfiguration.imports`](greeting-spring-boot-starter/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports)

- Add a `#` comment that this Boot 3 file replaces deprecated `spring.factories` and lists the auto-config class.

### 2. Greeting assembly logic

[`DefaultGreetingService.java`](greeting-spring-boot-starter/src/main/java/de/ibb/greeting/DefaultGreetingService.java)

- Expand class Javadoc (implements contract, driven by `GreetingProperties`).
- Constructor: why null properties are rejected.
- `greet`: short step comments for build order — prefix → message → separator → name → suffix → optional uppercase → emoji wrap **after** uppercase (so emoji is not uppercased).

[`GreetingService.java`](greeting-spring-boot-starter/src/main/java/de/ibb/greeting/GreetingService.java) / [`GreetingProperties.java`](greeting-spring-boot-starter/src/main/java/de/ibb/greeting/GreetingProperties.java)

- Leave largely as-is (already well documented); only tiny clarifications if a field comment is misleading (e.g. that `enabled` is enforced by auto-config conditions, not by the service itself).

### 3. Demo consumer

[`DemoApplication.java`](greeting-spring-boot-starter-demo/src/main/java/de/ibb/demo/DemoApplication.java)

- Class Javadoc: demo app that consumes the starter with no manual `@Bean` for `GreetingService`.
- Comment `CommandLineRunner`: injects auto-configured service and logs sample greets + REST hint.

[`GreetingController.java`](greeting-spring-boot-starter-demo/src/main/java/de/ibb/demo/GreetingController.java)

- Class Javadoc: REST facade over injected `GreetingService`.
- Brief method docs for `GET /greet/{name}` and `GET /greet` (default `"World"`).

[`application.properties`](greeting-spring-boot-starter-demo/src/main/resources/application.properties)

- Section comments for server port and `greeting.*` / `greeting.format.*` keys.

## Out of scope

- Test sources (already descriptive via `@DisplayName`)
- POM/XML files
- Behavior changes — comments only

## Comment style rules

- German, consistent with existing Javadoc
- Prefer class/method Javadoc over noisy line-by-line narration
- Inline comments only where order or Spring conditions are non-obvious
- No comments that merely restate the code (`// return result`)
