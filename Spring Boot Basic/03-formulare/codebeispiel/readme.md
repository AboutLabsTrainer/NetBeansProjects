Die Aufgabe dieses Moduls steht in der [README des Elternordners](../README.md).

## Spring Boot Basic – Tag 4 (Thymeleaf Forms & MVC)

Dieses Projekt ist eine kleine Spring‑Boot‑Anwendung, die das komplette MVC‑Pattern mit Thymeleaf‑Formularen demonstriert.  
Sie bietet ein HTML‑Interface für eine Personenverwaltung (CRUD: Create, Read, Update, Delete) inklusive Validierung, Custom Validator und eigenen Error‑Pages.

---

## Zielsetzung

- HTML‑Formulare mit Thymeleaf (`th:object`, `th:field`, `th:action`)
- Vollständiges CRUD über HTML (kein REST‑Frontend nötig)
- Bean Validation (Standard‑Annotations + Custom Validator für Telefonnummern)
- Whitespace‑Trimming mit `@InitBinder`
- Benutzerfreundliche 404/500‑Errorseiten
- Ausführung als Maven‑Projekt in NetBeans mit JDK 21 und Lombok

---

## Technischer Stack

- **Sprache**: Java 21  
- **Framework**: Spring Boot 3.2.x (Spring Web MVC, Validation)  
- **Template Engine**: Thymeleaf  
- **Build‑Tool**: Maven  
- **IDE‑Ziel**: Apache NetBeans 28  
- **Hilfsbibliothek**: Lombok 1.18.34  

---

## Projektstruktur (vereinfacht)

- `com.example.helloworldapi.HelloWorldApiApplication` – Spring‑Boot‑Main‑Klasse  
- `com.example.helloworldapi.controller`  
  - `PersonViewController` – Web‑Controller für HTML‑Views  
  - `CustomErrorController` – Routing auf eigene Fehlerseiten  
- `com.example.helloworldapi.model`  
  - `Person` – Fachmodell inkl. Bean‑Validierung  
- `com.example.helloworldapi.service`  
  - `PersonService` – In‑Memory‑CRUD mit 3 Testpersonen  
- `com.example.helloworldapi.validation`  
  - `ValidGermanPhone` – Custom Annotation  
  - `GermanPhoneValidator` – Validierungslogik für deutsche Telefonnummern  
- `src/main/resources`  
  - `application.properties` – u. a. `server.port=8094`  
  - `static/css/style.css` – Basislayout (Tabellen, Formulare, Buttons, Error‑Seiten)  
  - `templates/` – Thymeleaf‑Templates (`persons-list.html`, `person-details.html`, `person-form.html`, `person-edit-form.html`, `error/*.html`)  

---

## Architekturüberblick

### Request‑Flow (Browser → Controller → Service)

```mermaid
flowchart LR
  browser[Browser] --> personsRoutes["/persons\n/ persons/{id}\n/ persons/new ..."]
  personsRoutes --> viewController[PersonViewController]
  viewController --> personService[PersonService]
  personService --> personsStore[InMemory Person-Liste]
```

- **Browser** ruft URLs wie `/persons`, `/persons/1`, `/persons/new` auf.  
- **PersonViewController** verarbeitet GET/POST‑Requests und wählt das passende Thymeleaf‑Template.  
- **PersonService** kapselt die Business‑Logik und arbeitet auf einer In‑Memory‑Liste von `Person`.  
- **Templates** binden Daten über `th:object`/`th:field` an das Modell.  

### Wichtige Endpoints (HTML‑Interface)

- `GET /persons` – Personenliste (`persons-list.html`)  
- `GET /persons/{id}` – Detailansicht (`person-details.html`)  
- `GET /persons/new` – leeres Formular (`person-form.html`)  
- `POST /persons/new` – anlegen + Redirect `/persons`  
- `GET /persons/{id}/edit` – Bearbeiten‑Formular (`person-edit-form.html`)  
- `POST /persons/{id}/edit` – aktualisieren + Redirect `/persons/{id}`  
- `POST /persons/{id}/delete` – löschen + Redirect `/persons`  

---

## Datenmodell & Validierung

### `Person`

- `Long id`  
- `String firstname`  
  - `@NotBlank`, `@Size(2, 50)`, `@Pattern` (nur Buchstaben + Leerzeichen/Bindestrich)  
- `String lastname`  
  - analoge Regeln wie `firstname`  
- `String email`  
  - `@NotBlank`, `@Email`  
- `String phone`  
  - `@ValidGermanPhone` (optional, wenn gesetzt, wird Format geprüft)  

Lombok‑Annotations auf der Klasse:

- `@Data` – Getter/Setter, `equals`, `hashCode`, `toString`  
- `@NoArgsConstructor` – parameterloser Konstruktor  
- `@AllArgsConstructor` – Konstruktor mit allen Feldern (wird u. a. im `PersonService` genutzt)  

### Custom Validator: `@ValidGermanPhone`

- Annotation `ValidGermanPhone` mit `@Constraint(validatedBy = GermanPhoneValidator.class)`  
- `GermanPhoneValidator` implementiert `ConstraintValidator<ValidGermanPhone, String>`  
- Logik (vereinfacht):  
  - `null` oder leer → gültig (Pflichtfeld wird über `@NotBlank` gesteuert)  
  - entfernt Leerzeichen, `-`, `/`  
  - prüft, dass die Nummer mit `+49` oder `0` beginnt und eine sinnvolle Länge hat  

---

## Controller & Fehlermanagement

### `PersonViewController`

Aufgaben:

- Routing der HTML‑Endpoints (`/persons`, `/persons/{id}`, `/persons/new`, `/persons/{id}/edit`, `/persons/{id}/delete`)  
- Anbindung der `Person`‑Objekte an die Formulare (`th:object="${person}"`)  
- Validierung mittels `@Valid` + `BindingResult`  
- Whitespace‑Trimming für alle `String`‑Felder:

  ```java
  @InitBinder
  public void initBinder(WebDataBinder binder) {
      StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
      binder.registerCustomEditor(String.class, stringTrimmer);
  }
  ```

- Konstruktor‑Injection mit Lombok:

  ```java
  @Controller
  @RequiredArgsConstructor
  public class PersonViewController {
      private final PersonService personService;
      ...
  }
  ```

### `CustomErrorController`

- Implementiert `ErrorController` und mapped `/error` auf:  
  - `error/404.html` bei HTTP 404  
  - `error/500.html` bei HTTP 500  
  - `error/generic.html` für alle anderen Fehler  

Damit werden die Standard‑Whitelabel‑Fehlerseiten von Spring Boot ersetzt.  

---

## Styling & User Experience

`static/css/style.css` definiert u. a.:

- zentrierten Inhalt (`.container`)  
- Tabellen‑Layout für die Liste (`table`, `th`, `td`)  
- Formular‑Layout (`.form-group`, `.form-actions`)  
- Buttons (`.btn-primary`, `.btn-secondary`, `.btn-danger`)  
- Hervorhebung von Fehlern (`.error`, `.error-message`)  

Die Styles nutzen neutrale Kontraste; es werden keine expliziten Farbcodes im Mermaid‑Diagramm verwendet, sondern die Standardfarben des Mermaid‑Themes.  

---

## Konfiguration & Start

- **Port**:  
  `src/main/resources/application.properties`

  ```properties
  server.port=8094
  ```

- **Build & Start mit Maven (Kommandozeile)**:

  ```bash
  mvn clean install
  mvn spring-boot:run
  ```

- **URL im Browser**:  
  `http://localhost:8094/persons`  

---

## NetBeans‑Eigenheiten (Maven + Lombok + JDK 21)

Bei der Entwicklung in Apache NetBeans 28 sind einige Besonderheiten aufgetreten, die für ähnliche Projekte wichtig sind.

### 1. Maven‑Projekt vs. Ant‑Projekt (Annotation Processing)

- In Ant‑basierten Java‑Projekten gibt es im Project‑Properties‑Dialog:  
  `Build → Compiling` mit Checkboxen:
  - **Enable Annotation Processing**
  - **Enable Annotation Processing in Editor**
- In Maven‑Projekten (wie diesem) **existieren diese Schalter nicht mehr**.  
  Annotation Processing wird ausschließlich über Maven bzw. die `pom.xml` gesteuert.

### 2. Lombok‑Konfiguration für Maven + JDK 21

In der `pom.xml` wird Lombok so eingebunden:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.34</version>
    <scope>provided</scope>
</dependency>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.13.0</version>
    <configuration>
        <compilerArgs>
            <arg>-proc:full</arg>
        </compilerArgs>
        <annotationProcessorPaths>
            <path>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.34</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

Wichtig:

- `scope=provided`: Lombok wird nicht ins JAR gepackt.  
- `annotationProcessorPaths` mit `<path>`‑Elementen: von NetBeans erwartetes Format.  
- `-proc:full`: erzwingt vollständiges Annotation Processing bei neueren JDKs (21/23).  

### 3. JDK‑Version (IDE vs. Maven)

- Das Projekt ist auf **Java 21** eingestellt (`<java.version>21</java.version>`).  
- In NetBeans muss unter `Project Properties → Build → Compile` die **Java Platform** auf ein JDK 21 gesetzt sein.  
- Beim Start über NetBeans wird zusätzlich per `JAVA_HOME` ein JDK 21 verwendet.

Wenn IDE und Maven unterschiedliche JDKs nutzen, können schwer verständliche Compiler‑Fehler auftreten.  

### 4. Editor‑Fehlalarme mit Lombok

NetBeans erkennt Lombok‑Code im Editor nicht immer vollständig. Typische, aber harmlose Meldungen:

- „Variable `personService` nicht im Standardkonstruktor initialisiert“ in `PersonViewController`  
  → der von `@RequiredArgsConstructor` generierte Konstruktor wird im Editor nicht erkannt.  
- „Konstruktor `Person(...)` kann nicht auf die angegebenen Typen angewendet werden“ in `PersonService`  
  → `@AllArgsConstructor` wird vom Editor ignoriert, der Maven‑Build funktioniert dennoch.

Solange `mvn clean compile` bzw. ein NetBeans‑„Clean and Build“ fehlerfrei durchläuft, können diese Editor‑Warnungen ignoriert oder in den Editor‑Hints deaktiviert werden.  

### 5. `nbactions.xml` und Exec‑Plugin

NetBeans steuert „Run“/„Debug“ bei Maven‑Projekten über `nbactions.xml`.  
Für dieses Projekt sieht die `run`‑Action z. B. so aus:

```xml
<action>
    <actionName>run</actionName>
    <packagings>
        <packaging>jar</packaging>
    </packagings>
    <goals>
        <goal>process-classes</goal>
        <goal>org.codehaus.mojo:exec-maven-plugin:3.5.1:exec</goal>
    </goals>
    <properties>
        <exec.vmArgs></exec.vmArgs>
        <exec.args>${exec.vmArgs} -classpath %classpath ${exec.mainClass} ${exec.appArgs}</exec.args>
        <exec.appArgs></exec.appArgs>
        <exec.mainClass>com.example.helloworldapi.HelloWorldApiApplication</exec.mainClass>
        <exec.executable>java</exec.executable>
    </properties>
</action>
```

Der eigentliche Java‑Aufruf (`java -classpath ... MainClass`) muss in `exec.args` stehen.  
Liegt alles versehentlich in `exec.vmArgs`, wird `java` ohne Main‑Class gestartet, zeigt nur seine Hilfe und das Exec‑Plugin bricht mit einem Fehler ab.

---

## Zusammenfassung

- Vollständiges Spring‑MVC‑/Thymeleaf‑Beispiel mit CRUD‑Formularen und Validierung.  
- Läuft standardmäßig auf `http://localhost:8094/persons`.  
- Für NetBeans 28 sind vor allem die Maven‑Konfiguration für Lombok und ein korrektes `nbactions.xml` wichtig; GUI‑Schalter für Annotation Processing gibt es im Maven‑Projekttyp nicht mehr.

