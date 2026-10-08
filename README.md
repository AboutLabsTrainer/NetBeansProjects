# NetBeansProjects

Private monorepo of Java training material for classroom labs, demos, and self-study. Projects are organized by course track and day/tag so they open cleanly in **NetBeans** (and usually also in IntelliJ or VS Code/Cursor with Maven or Ant).

Most lab folders include their own `README.md` with goals, commands, and notes. Use this file as the map of the workspace; open the nested README when you work on a specific exercise.

## Tooling

- **JDK**: version varies by project (check each `pom.xml` or NetBeans project properties)
- **Build**: Maven (`pom.xml`) for most web/Spring modules; Ant/NetBeans (`build.xml` + `nbproject/`) for many SE/OO labs
- **Servers** (where needed): Payara/GlassFish-style Java EE runtime, embedded Tomcat via Spring Boot, ActiveMQ for messaging demos
- **IDE**: NetBeans is the primary target; `.idea/` and build outputs are ignored

## Course tracks

### `Java SE`

Core language skills before frameworks.

| Subfolder | Contents |
|-----------|----------|
| `Java Grundlagen` | Notebook-style intro, types, text/time tools, control flow, debugging exercises, small apps (e.g. ticket counter, book management) |
| `Java Erweiterte Techniken` | Day labs on lists, sets/maps, generics, functional interfaces, streams, files, threads, synchronization, sockets |
| `Java ist auch eine Insel` | Sample programs and modules inspired by the Tutego “Insel” book |

### `Java OO`

Progressive object-oriented exercises around a vehicle / rental domain:

1. Types and classes  
2. `static` members  
3. Inheritance  
4. Interfaces (rental objects)  
5. Exception handling  

### `Java Anwendungsentwicklung`

Desktop and persistence track:

- UI: AWT, Swing, layouts, JavaFX, ContactEditor sample  
- Data: JDBC, connection pools, transactions/DAO patterns  
- ORM: JPA entity lifecycle, relationships, JPQL  
- Closing integration day

### `Java Web Basic`

Servlet/JSP foundation (roughly one topic per `TagXX-…` folder):

Hello webapp → HTTP → Servlets → simple MVC → JSP → JavaBeans → includes → JSTL → pools → JDBC  

Also includes material from *Professionell entwickeln mit Java EE 8*.

### `Java Web Aufbau`

Next step after the basic web course:

Filters, listeners, authentication, security, custom tags, JPA/JDBC, many-to-one and broader JPA relations, JSF  

Again accompanied by Java EE 8 book sample trees where useful.

### `Spring Boot Basic`

Spring Boot fundamentals in numbered modules:

REST APIs → container & DI → Thymeleaf controllers → forms/MVC → configuration & logging → DI/AOP → scopes → WebSockets → JAX-RS → integration  

### `Spring Boot AK`

Advanced Spring Boot modules:

Custom config / auto-starters → JPA basics & relations → Spring Security → caching → messaging & email (incl. ActiveMQ broker demo) → Actuator → template engines and a small microservices set (Eureka, API gateway, user/order services)

### Supporting folders

| Folder | Role |
|--------|------|
| `Mini Projekte` | Small scratch projects (e.g. `ScratchPad`) outside the main day structure |
| `Rheinwerk` | Publisher/book sample code (`buch_spring_boot_code`, Date4u-related demos, etc.) |
| `Tmp` | Experiments and WIP (`BookShop`, `DockerJavaEE`, quick Spring Boot trials) |
| `Debug` | Ad-hoc debugging space |

## Repository conventions

- **Source of truth** is the unpacked project tree, not archive downloads. `*.zip` / similar archives are gitignored.
- **Do not commit** Maven/Gradle/NetBeans build products (`target/`, `build/`, `dist/`, …), IDE private files, local broker data (`MyMessageBroker/data/`), or keystores/secrets (`.p12`, `.env`, …).
- Nested project READMEs document exercises in place; keep them updated when a lab’s setup or goals change.
- Commit with the training GitHub identity used for this remote (`AboutLabsTrainer`).

## Remote

Private GitHub repository: [AboutLabsTrainer/NetBeansProjects](https://github.com/AboutLabsTrainer/NetBeansProjects)
