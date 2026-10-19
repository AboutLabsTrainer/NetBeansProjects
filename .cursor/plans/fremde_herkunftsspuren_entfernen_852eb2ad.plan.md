---
name: Fremde Herkunftsspuren entfernen
overview: Herkunftsspuren von java-developer.online (Java Fleet, Elyndra Valen, Franz-Martin, com.javadeveloper, fleetdata) werden aus den Kurslaboren entfernt. Kommentare bleiben fachlich, Pakete und Maven-Gruppen folgen dem bestehenden de.ibb-Schema.
todos:
  - id: strip-comments
    content: JavaDoc, @author, README-Quellen, Demo-Texte und JSP-Präfixe von Java Fleet / Elyndra / Franz-Martin befreien
    status: completed
  - id: rename-packages
    content: fleetdata-Pakete und com.javadeveloper-Gruppen auf de.ibb.boot.ak, de.ibb.web.basic und de.ibb.web.aufbau umstellen
    status: completed
  - id: verify
    content: Betroffene Codebeispiele kompilieren und die Herkunftsbegriffe in den Kursordnern erneut suchen
    status: completed
isProject: false
---

# Fremde Herkunftsspuren entfernen

Betrifft nur die sieben Kursordner. `Tmp`, `Rheinwerk`, `Teilnehmerprojekte` und `Mini Projekte` bleiben. Alte Tagesnummern in Kontextpfaden, Artefakt-Ids und Datenbanknamen (`/Tag7`, `jae_tag06_jdbc`) bleiben, das sind keine Herkunftsspuren.

Das bestehende Schema ist das der Übungen: Maven-Gruppe `de.ibb.web.basic`, `de.ibb.web.aufbau`, `de.ibb.ae`, `de.ibb.boot.ak`; Pakete `de.ibb…`; JavaDoc ohne Autor und ohne Website, wie in [PrimitiveDemo.java](Java%20SE/Java%20Grundlagen/01-typen/codebeispiel/src/main/java/de/ibb/typelab/PrimitiveDemo.java).

## Texte und sichtbare Namen

- JavaDoc-Zeilen und `@author` mit `Java Fleet`, `java-developer.online` oder `Franz-Martin` streichen. Der fachliche Kommentar bleibt. Betrifft die Codebeispiele in Anwendungsentwicklung `06-jdbc`, `07-jdbc-pool`, `08-jpa`, `09-jpa-abfragen`, `10-integration` und die Spring-Boot-Basic-Klassen in `01-container-di` und `02-thymeleaf`. Dieselbe Zeile in [persistence.xml](Java%20Anwendungsentwicklung/08-jpa/codebeispiel/src/main/resources/META-INF/persistence.xml).
- README-Abschnitte und Links auf `java-developer.online` sowie Fußzeilen „Von Java Fleet“ entfernen. Betrifft die Codebeispiel-READMEs in Spring Boot Basic (`00-rest-api`, `01-container-di`, `06-scopes`) und Spring Boot AK (`00-custom-starter`, `05-messaging`, `07-template-engines` samt Modul-READMEs, `08-microservices` samt `CONTRIBUTING.md`).
- Sichtbare Demo-Texte neutralisieren, ohne das Verhalten zu ändern: Fenstertitel in `PersonenFrame`, Konsolenbanner und JSON-Feld `author` in `01-container-di`, Thymeleaf-Fußzeilen, Mail-Absender `noreply@example.com` statt `noreply@java-developer.online`, Betreff und Fließtext ohne „Fleet Data“, Brokername `MessageBroker` statt `FleetDataBroker`.
- Beispielnamen `Elyndra` durch einen schon im Kurs verwendeten Namen ersetzen (`Anna`): Blog-Autor in [BlogPostDAO.java](Java%20Web%20Basic/03-mvc/codebeispiel/src/main/java/de/ibb/dao/BlogPostDAO.java), Greeting-Demo, Properties `app.author` / `app.company`. `app.company` wird `IBB`.
- JSP-Präfixe `fleet` und `fleetx` in [04-eigene-tags](Java%20Web%20Aufbau/04-eigene-tags/codebeispiel/src/main/webapp/demo-simple.jsp) werden `simple` und `classic`, passend zu den URIs `https://de.ibb/simple` und `https://de.ibb/classic`. Der Truncate-Text nennt kein „Java Fleet“ mehr.
- Annotation-Beispiel `@Author(name = "Java Fleet Student")` in [SimpleExample.java](Java%20SE/Java%20Erweiterte%20Techniken/07-threads/codebeispiel/src/main/java/de/ibb/tag08/level1/SimpleExample.java) wird `Kursteilnehmer`. Der Kommentar in [Main.java](Java%20SE/Java%20Erweiterte%20Techniken/01-sets-maps/codebeispiel/src/main/java/de/ibb/setsmapschallenge/Main.java) verliert den Quellenhinweis.
- In den `.cursor/plans` unter den Codebeispielen nur die Sätze und URLs streichen, die die Website oder `ElyndraValen` nennen.

## Pakete und Maven-Gruppen

Ordner verschieben, `package`/`import`, `start-class` und die Main-Klasse in der Modul-README nachziehen.

- `com.fleetdata.personmanagement` in [02-jpa-relationen/codebeispiel](Spring%20Boot%20AK/02-jpa-relationen/codebeispiel) wird `de.ibb.boot.ak.relations`.
- Dieselbe Wurzel in [03-security/codebeispiel](Spring%20Boot%20AK/03-security/codebeispiel) wird `de.ibb.boot.ak.security`.
- `de.fleetdata.messaging` in den vier Modulen unter [05-messaging/codebeispiel](Spring%20Boot%20AK/05-messaging/codebeispiel) wird `de.ibb.boot.ak.messaging`.
- `groupId` `com.fleetdata` und `de.fleetdata.messaging` wird `de.ibb.boot.ak`, wie in den Übungs-POMs desselben Kurses.
- `groupId` `com.javadeveloper` in den Codebeispiel-POMs von Java Web Basic (`00`–`07`, `09`) und Java Web Aufbau (`01`–`06`, `08`) wird `de.ibb.web.basic` bzw. `de.ibb.web.aufbau`. Java-Pakete sind dort schon `de.ibb`.

## Prüfung

Nach dem Umbau die betroffenen Codebeispiele mit `mvn -q -DskipTests compile` bauen: die drei Spring-Boot-AK-Labore, die genannten Anwendungsentwicklungs-Module und stichprobenartig ein Web-Basic- und ein Web-Aufbau-Codebeispiel. Eine Suche nach `fleetdata`, `javadeveloper`, `java-developer.online`, `Java Fleet`, `Elyndra` und `Franz-Martin` in den sieben Kursordnern muss leer sein.