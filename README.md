# NetBeansProjects

Private monorepo of Java training material for classroom labs, demos, and self-study. This file is the map of the workspace. Each course README lists its modules. Each module README has the goals, commands, and notes for that lab.

## Module leaves

A module folder has no `pom.xml` of its own. Open the leaf that contains `pom.xml`:

- `codebeispiel` — the working demo
- `uebung-stufe-1` — guided exercise
- `uebung-stufe-2` — transfer exercise
- `loesung/stufe-1` and `loesung/stufe-2` — solutions, after your own attempt

How to open that leaf in NetBeans, IntelliJ, or VS Code is in [docs/ide-maven.md](docs/ide-maven.md).

## Tooling

- **JDK**: compile and run course modules with JDK **21.0.11** (`JAVA_HOME`, NetBeans 31 `netbeans_jdkhome`, Payara 6). Do not compile labs with `--release 25`.
- **Jakarta web** (`Java Web Basic` and `Java Web Aufbau`): Payara Community **6.2025.10** at `C:\payara6`, **Jakarta EE 10**. API is `jakarta.jakartaee-web-api` or `jakarta.jakartaee-api` **10.0.0**, scope `provided`. Servlet 6.0, Persistence **3.1**. The JPA provider is the server EclipseLink **4.0.7**. Do not put Hibernate or EclipseLink into the WAR, and do not set `class-loader delegate="false"`. In NetBeans 31 the Payara deployer id stays `pfv5ee8`; the registered server home is Payara 6.
- **SE JPA** (`Java Anwendungsentwicklung` `08-jpa`, `09-jpa-abfragen`, `10-integration`): EclipseLink **5.0.0** and Jakarta Persistence **3.2**, `RESOURCE_LOCAL`.
- **Spring Boot JPA** (`Spring Boot AK` `01-jpa`, `02-jpa-relationen`, `03-security`, `06-actuator`): Spring Boot **3.5.16**, Java 21, Hibernate only from the Boot BOM. EclipseLink stays off this track; Boot 4 is a later course cut.
- **Footguns to keep in view**: Lombok `@Data` or `@EqualsAndHashCode` on an entity hashes mutable fields, so an element gets lost in a `HashSet` after `persist` or a change. Rules in [docs/lombok-jpa.md](docs/lombok-jpa.md). Spring Boot leaves Open Session in View on, so a lazy association can load during JSON rendering without an explicit transaction.
- **Build**: Maven (`pom.xml`) for every course leaf. JDK release is 21. `scripts/build-leaves.sh all` compiles every leaf with its test code. CI (`.github/workflows/build.yml`) runs it for the leaves a push changes; the full run starts by hand under Actions.
- **MySQL**: Docker Compose in [docker/mysql](docker/mysql) (`docker compose up -d`). Database `ibb-db`, user `ibb-user`, password `ibb-pw`. Adminer at http://localhost:8088. Payara pool `jdbc/ibb`, JDBC realm `ibb-realm`. Stop XAMPP MySQL so port 3306 is free.
- **Other servers**: embedded Tomcat via Spring Boot, ActiveMQ for messaging demos
- **IDE**: NetBeans, IntelliJ and VS Code open the folder that contains `pom.xml`. The steps are in [docs/ide-maven.md](docs/ide-maven.md). `.idea/` and build outputs are ignored

### Embedded Tomcat when switching Spring Boot labs

Opening another project does not stop the previous application. The port stays taken until that process exits. Several labs listen on **8080**, including the JPA modules in `Spring Boot AK` and `MyMessageBroker`.

1. Stop the running app before starting the next one. In Cursor, press `Ctrl+C` in the terminal that runs `mvn spring-boot:run` and wait until the prompt returns. In NetBeans, open that application's Output window and press **Stop**. Launch the main class directly so Stop ends that JVM. Stopping a forked `spring-boot:run` on Windows can leave the child process running.
2. If the port is still in use, identify the listener before ending it. In PowerShell, replace `8080` with the lab's `server.port`:

```powershell
Get-NetTCPConnection -LocalPort 8080 -State Listen -ErrorAction SilentlyContinue |
  ForEach-Object { Stop-Process -Id $_.OwningProcess -Force }
```

Do not free port 8080 blindly. The messaging broker uses it too.

Labs that must run at the same time need different `server.port` values. `Spring Boot Basic` already does this (for example 8081, 8094, 8100). There is no hook on project open that should kill whatever is listening.

## Reading order

Classroom sequence for the language and Jakarta track:

1. [Java Grundlagen](Java%20SE/Java%20Grundlagen/README.md)
2. [Java Erweiterte Techniken](Java%20SE/Java%20Erweiterte%20Techniken/README.md)
3. [Java OO](Java%20OO/README.md)
4. [Java Anwendungsentwicklung](Java%20Anwendungsentwicklung/README.md)
5. [Java Web Basic](Java%20Web%20Basic/README.md)
6. [Java Web Aufbau](Java%20Web%20Aufbau/README.md)

The Spring Boot courses sit beside that sequence. Work [Spring Boot Basic](Spring%20Boot%20Basic/README.md) before [Spring Boot AK](Spring%20Boot%20AK/README.md).

## Course tracks

### [Java SE](Java%20SE/README.md)

Core language skills before frameworks. Two numbered sequences: [Java Grundlagen](Java%20SE/Java%20Grundlagen/README.md) and [Java Erweiterte Techniken](Java%20SE/Java%20Erweiterte%20Techniken/README.md). [Java ist auch eine Insel](Java%20SE/Java%20ist%20auch%20eine%20Insel/README.md) sits outside that sequence.

[Java Grundlagen](Java%20SE/Java%20Grundlagen/README.md): [00-notebook](Java%20SE/Java%20Grundlagen/00-notebook/README.md), [01-typen](Java%20SE/Java%20Grundlagen/01-typen/README.md), [02-text-zeit](Java%20SE/Java%20Grundlagen/02-text-zeit/README.md), [03-ticketkasse](Java%20SE/Java%20Grundlagen/03-ticketkasse/README.md), [04-ablaufsteuerung](Java%20SE/Java%20Grundlagen/04-ablaufsteuerung/README.md), [05-debug](Java%20SE/Java%20Grundlagen/05-debug/README.md), [06-debug-zahlen](Java%20SE/Java%20Grundlagen/06-debug-zahlen/README.md), [07-buchverwaltung](Java%20SE/Java%20Grundlagen/07-buchverwaltung/README.md)

[Java Erweiterte Techniken](Java%20SE/Java%20Erweiterte%20Techniken/README.md): [00-listen](Java%20SE/Java%20Erweiterte%20Techniken/00-listen/README.md), [01-sets-maps](Java%20SE/Java%20Erweiterte%20Techniken/01-sets-maps/README.md), [02-generics](Java%20SE/Java%20Erweiterte%20Techniken/02-generics/README.md), [03-lambda](Java%20SE/Java%20Erweiterte%20Techniken/03-lambda/README.md), [04-funktionen](Java%20SE/Java%20Erweiterte%20Techniken/04-funktionen/README.md), [05-streams](Java%20SE/Java%20Erweiterte%20Techniken/05-streams/README.md), [06-dateien](Java%20SE/Java%20Erweiterte%20Techniken/06-dateien/README.md), [07-threads](Java%20SE/Java%20Erweiterte%20Techniken/07-threads/README.md), [08-sync](Java%20SE/Java%20Erweiterte%20Techniken/08-sync/README.md), [09-sockets](Java%20SE/Java%20Erweiterte%20Techniken/09-sockets/README.md)

### [Java OO](Java%20OO/README.md)

Vehicle and rental exercises: [00-typen](Java%20OO/00-typen/README.md), [01-static](Java%20OO/01-static/README.md), [02-vererbung](Java%20OO/02-vererbung/README.md), [03-interfaces](Java%20OO/03-interfaces/README.md), [04-ausnahmen](Java%20OO/04-ausnahmen/README.md).

### [Java Anwendungsentwicklung](Java%20Anwendungsentwicklung/README.md)

Desktop and persistence: [00-awt](Java%20Anwendungsentwicklung/00-awt/README.md), [01-swing](Java%20Anwendungsentwicklung/01-swing/README.md), [02-layouts](Java%20Anwendungsentwicklung/02-layouts/README.md), [03-tabellen](Java%20Anwendungsentwicklung/03-tabellen/README.md), [04-javafx](Java%20Anwendungsentwicklung/04-javafx/README.md), [05-contact-editor](Java%20Anwendungsentwicklung/05-contact-editor/README.md), [06-jdbc](Java%20Anwendungsentwicklung/06-jdbc/README.md), [07-jdbc-pool](Java%20Anwendungsentwicklung/07-jdbc-pool/README.md), [08-jpa](Java%20Anwendungsentwicklung/08-jpa/README.md), [09-jpa-abfragen](Java%20Anwendungsentwicklung/09-jpa-abfragen/README.md), [10-integration](Java%20Anwendungsentwicklung/10-integration/README.md).

### [Java Web Basic](Java%20Web%20Basic/README.md)

Servlet and JSP foundation: [00-hello](Java%20Web%20Basic/00-hello/README.md), [01-http](Java%20Web%20Basic/01-http/README.md), [02-servlet](Java%20Web%20Basic/02-servlet/README.md), [03-mvc](Java%20Web%20Basic/03-mvc/README.md), [04-jsp](Java%20Web%20Basic/04-jsp/README.md), [05-beans](Java%20Web%20Basic/05-beans/README.md), [06-include](Java%20Web%20Basic/06-include/README.md), [07-jstl](Java%20Web%20Basic/07-jstl/README.md), [08-pools](Java%20Web%20Basic/08-pools/README.md), [09-jdbc](Java%20Web%20Basic/09-jdbc/README.md).

### [Java Web Aufbau](Java%20Web%20Aufbau/README.md)

Next step after the basic web course: [00-filter](Java%20Web%20Aufbau/00-filter/README.md), [01-listener](Java%20Web%20Aufbau/01-listener/README.md), [02-auth](Java%20Web%20Aufbau/02-auth/README.md), [03-security](Java%20Web%20Aufbau/03-security/README.md), [04-eigene-tags](Java%20Web%20Aufbau/04-eigene-tags/README.md), [05-jpa-jdbc](Java%20Web%20Aufbau/05-jpa-jdbc/README.md), [06-many-to-one](Java%20Web%20Aufbau/06-many-to-one/README.md), [07-jpa-relationen](Java%20Web%20Aufbau/07-jpa-relationen/README.md), [08-jsf](Java%20Web%20Aufbau/08-jsf/README.md).

### [Spring Boot Basic](Spring%20Boot%20Basic/README.md)

[00-rest-api](Spring%20Boot%20Basic/00-rest-api/README.md), [01-container-di](Spring%20Boot%20Basic/01-container-di/README.md), [02-thymeleaf](Spring%20Boot%20Basic/02-thymeleaf/README.md), [03-formulare](Spring%20Boot%20Basic/03-formulare/README.md), [04-konfiguration](Spring%20Boot%20Basic/04-konfiguration/README.md), [05-di-aop](Spring%20Boot%20Basic/05-di-aop/README.md), [06-scopes](Spring%20Boot%20Basic/06-scopes/README.md), [07-websockets](Spring%20Boot%20Basic/07-websockets/README.md), [08-jax-rs](Spring%20Boot%20Basic/08-jax-rs/README.md), [09-integration](Spring%20Boot%20Basic/09-integration/README.md).

Stop the previous embedded Tomcat before starting the next lab. See [Embedded Tomcat when switching Spring Boot labs](#embedded-tomcat-when-switching-spring-boot-labs).

### [Spring Boot AK](Spring%20Boot%20AK/README.md)

[00-custom-starter](Spring%20Boot%20AK/00-custom-starter/README.md), [01-jpa](Spring%20Boot%20AK/01-jpa/README.md), [02-jpa-relationen](Spring%20Boot%20AK/02-jpa-relationen/README.md), [03-security](Spring%20Boot%20AK/03-security/README.md), [04-caching](Spring%20Boot%20AK/04-caching/README.md), [05-messaging](Spring%20Boot%20AK/05-messaging/README.md), [06-actuator](Spring%20Boot%20AK/06-actuator/README.md), [07-template-engines](Spring%20Boot%20AK/07-template-engines/README.md), [08-microservices](Spring%20Boot%20AK/08-microservices/README.md).

Stop the previous embedded Tomcat before starting the next lab. See [Embedded Tomcat when switching Spring Boot labs](#embedded-tomcat-when-switching-spring-boot-labs). Several of these modules share port 8080, including the broker inside [05-messaging](Spring%20Boot%20AK/05-messaging/README.md).

### Supporting folders

| Folder | Role |
|--------|------|
| `Mini Projekte` | Small scratch projects (e.g. `ScratchPad`) outside the main day structure |
| `Rheinwerk` | Publisher/book sample code (`Java ist auch eine Insel`, `buch_spring_boot_code`, Date4u-related demos, etc.) |
| `Teilnehmerprojekte` | Participant projects |
| `Tmp` | Local experiments and WIP. Gitignored, so nothing in it reaches the remote |

## Repository conventions

- **Source of truth** is the unpacked project tree, not archive downloads. `*.zip` / similar archives are gitignored.
- **Do not commit** Maven/Gradle/NetBeans build products (`target/`, `build/`, `dist/`, …), IDE private files, local broker data (`MyMessageBroker/data/`), or keystores/secrets (`.p12`, `.env`, …).
- Nested project READMEs document exercises in place; keep them updated when a lab’s setup or goals change.
- Commit with the training GitHub identity used for this remote (`AboutLabsTrainer`).

## Remote

Private GitHub repository: [AboutLabsTrainer/NetBeansProjects](https://github.com/AboutLabsTrainer/NetBeansProjects)
