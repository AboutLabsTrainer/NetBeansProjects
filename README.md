# NetBeansProjects

Private monorepo of Java training material for classroom labs, demos, and self-study. Each course is a sequence of modules `00`–`0x`. A module folder is not itself a build. Open the leaf that contains `pom.xml`: `codebeispiel`, `uebung-stufe-1`, `uebung-stufe-2`, or `loesung/stufe-1` and `loesung/stufe-2`.

Most lab folders include their own `README.md` with goals, commands, and notes. Use this file as the map of the workspace; open the nested README when you work on a specific exercise.

## Tooling

- **JDK**: compile and run course modules with JDK **21.0.11** (`JAVA_HOME`, NetBeans 31 `netbeans_jdkhome`, Payara 6). Do not compile labs with `--release 25`.
- **Jakarta web** (`Java Web Basic` and `Java Web Aufbau`): Payara Community **6.2025.10** at `C:\payara6`, **Jakarta EE 10**. API is `jakarta.jakartaee-web-api` or `jakarta.jakartaee-api` **10.0.0**, scope `provided`. Servlet 6.0, Persistence **3.1**. The JPA provider is the server EclipseLink **4.0.7**. Do not put Hibernate or EclipseLink into the WAR, and do not set `class-loader delegate="false"`. In NetBeans 31 the Payara deployer id stays `pfv5ee8`; the registered server home is Payara 6.
- **SE JPA** (`Java Anwendungsentwicklung` `08-jpa`, `09-jpa-abfragen`, `10-integration`): EclipseLink **5.0.0** and Jakarta Persistence **3.2**, `RESOURCE_LOCAL`.
- **Spring Boot JPA** (`Spring Boot AK` `01-jpa`, `02-jpa-relationen`, `03-security`, `06-actuator`): Spring Boot **3.5.16**, Java 21, Hibernate only from the Boot BOM. EclipseLink stays off this track; Boot 4 is a later course cut.
- **Footguns to keep in view**: Lombok `@EqualsAndHashCode` on an entity whose `id` is still null makes `Set` membership wrong before the first persist. Spring Boot leaves Open Session in View on, so a lazy association can load during JSON rendering without an explicit transaction.
- **Build**: Maven (`pom.xml`) for every course leaf. JDK release is 21.
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

## Course tracks

### `Java SE`

Core language skills before frameworks. `Java ist auch eine Insel` is not part of the numbered sequence.

- `Java Grundlagen`: `00-notebook`, `01-typen`, `02-text-zeit`, `03-ticketkasse`, `04-ablaufsteuerung`, `05-debug`, `06-debug-zahlen`, `07-buchverwaltung`
- `Java Erweiterte Techniken`: `00-listen`, `01-sets-maps`, `02-generics`, `03-lambda`, `04-funktionen`, `05-streams`, `06-dateien`, `07-threads`, `08-sync`, `09-sockets`

### `Java OO`

Vehicle and rental exercises: `00-typen`, `01-static`, `02-vererbung`, `03-interfaces`, `04-ausnahmen`.

### `Java Anwendungsentwicklung`

Desktop and persistence: `00-awt`, `01-swing`, `02-layouts`, `03-tabellen`, `04-javafx`, `05-contact-editor`, `06-jdbc`, `07-jdbc-pool`, `08-jpa`, `09-jpa-abfragen`, `10-integration`.

### `Java Web Basic`

Servlet and JSP foundation: `00-hello`, `01-http`, `02-servlet`, `03-mvc`, `04-jsp`, `05-beans`, `06-include`, `07-jstl`, `08-pools`, `09-jdbc`.

### `Java Web Aufbau`

Next step after the basic web course: `00-filter`, `01-listener`, `02-auth`, `03-security`, `04-eigene-tags`, `05-jpa-jdbc`, `06-many-to-one`, `07-jpa-relationen`, `08-jsf`.

### `Spring Boot Basic`

`00-rest-api`, `01-container-di`, `02-thymeleaf`, `03-formulare`, `04-konfiguration`, `05-di-aop`, `06-scopes`, `07-websockets`, `08-jax-rs`, `09-integration`.

Stop the previous embedded Tomcat before starting the next lab. See [Embedded Tomcat when switching Spring Boot labs](#embedded-tomcat-when-switching-spring-boot-labs).

### `Spring Boot AK`

`00-custom-starter`, `01-jpa`, `02-jpa-relationen`, `03-security`, `04-caching`, `05-messaging`, `06-actuator`, `07-template-engines`, `08-microservices`.

Stop the previous embedded Tomcat before starting the next lab. See [Embedded Tomcat when switching Spring Boot labs](#embedded-tomcat-when-switching-spring-boot-labs). Several of these modules share port 8080, including the broker inside `05-messaging`.

### Supporting folders

| Folder | Role |
|--------|------|
| `Mini Projekte` | Small scratch projects (e.g. `ScratchPad`) outside the main day structure |
| `Rheinwerk` | Publisher/book sample code (`Java ist auch eine Insel`, `buch_spring_boot_code`, Date4u-related demos, etc.) |
| `Tmp` | Experiments and WIP (`BookShop`, `DockerJavaEE`, quick Spring Boot trials) |
| `Debug` | Ad-hoc debugging space |

## Repository conventions

- **Source of truth** is the unpacked project tree, not archive downloads. `*.zip` / similar archives are gitignored.
- **Do not commit** Maven/Gradle/NetBeans build products (`target/`, `build/`, `dist/`, …), IDE private files, local broker data (`MyMessageBroker/data/`), or keystores/secrets (`.p12`, `.env`, …).
- Nested project READMEs document exercises in place; keep them updated when a lab’s setup or goals change.
- Commit with the training GitHub identity used for this remote (`AboutLabsTrainer`).

## Remote

Private GitHub repository: [AboutLabsTrainer/NetBeansProjects](https://github.com/AboutLabsTrainer/NetBeansProjects)
