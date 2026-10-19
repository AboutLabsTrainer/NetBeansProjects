---
name: Nummerierte Kursmodule
overview: Alle sieben Kurse werden auf fortlaufende Modulnummern 00–0x umgestellt. Jedes Modul enthält das bisherige Beispiel, zwei Maven-Übungen und die Lösungen, so dass Teilnehmende jedes Blatt einzeln in NetBeans, IntelliJ oder VS Code öffnen können.
todos:
  - id: ide-guide
    content: docs/ide-maven.md und die Modul-README-Vorlage anlegen; Wurzel-README auf die neue Karte vorbereiten
    status: completed
  - id: se-grundlagen
    content: Java SE Grundlagen auf 00–07 umstellen, Ant nach Maven, je Modul zwei Starter und Lösungen
    status: completed
  - id: se-erweitert
    content: Java SE Erweiterte Techniken auf 00–09 umstellen, Ant nach Maven, je Modul zwei Starter und Lösungen
    status: completed
  - id: java-oo
    content: Java OO auf 00–04 umstellen, Ant nach Maven, je Modul zwei Starter und Lösungen
    status: completed
  - id: anwendungsentwicklung
    content: Java Anwendungsentwicklung auf 00–10 umstellen, Ant-Module nach Maven, je Modul zwei Starter und Lösungen
    status: completed
  - id: web-basic
    content: Java Web Basic auf 00–09 umstellen und je Modul zwei Starter und Lösungen ergänzen
    status: completed
  - id: web-aufbau
    content: Java Web Aufbau lückenlos auf 00–08 umstellen und je Modul zwei Starter und Lösungen ergänzen
    status: completed
  - id: spring-basic
    content: Spring Boot Basic auf 00–09 umnummerieren und je Modul zwei Starter und Lösungen ergänzen
    status: completed
  - id: spring-ak
    content: Spring Boot AK auf 00–08 umstellen, Template-Engines und Microservices trennen, je Modul zwei Starter und Lösungen
    status: completed
  - id: verify
    content: Pro Kurs kompilieren und die Lösungen testen; Wurzel-README auf die neuen Nummern nachziehen
    status: completed
isProject: false
---

# Nummerierte Module mit zwei Übungsstufen

Jedes Thema wird ein Elternordner ohne das Wort „Tag“. Die Nummer beginnt bei `00` und läuft lückenlos. Der Ordner selbst ist kein Maven-Projekt, damit die Lösung nicht zusammen mit der Aufgabe importiert wird. Geöffnet wird immer der Blatt-Ordner, der die `pom.xml` enthält.

```text
00-typen/
  README.md
  codebeispiel/        bisheriges Beispiel, lauffähig
  uebung-stufe-1/      Maven-Starter, geführt
  uebung-stufe-2/      Maven-Starter, Transfer
  loesung/
    stufe-1/
    stufe-2/
```

`Java ist auch eine Insel`, `Rheinwerk`, `Tmp`, `Teilnehmerprojekte`, `Mini Projekte` und `Debug` bleiben außen vor. Die Insel bekommt nur einen kurzen Hinweis, wie das vorhandene Maven-Projekt geöffnet wird.

## Gemeinsame Regeln

- JDK 21, Encoding UTF-8. Web weiter Payara 6 und Jakarta EE 10, API `provided`. SE-JPA bleibt EclipseLink 5.0.0, Persistence 3.2, `RESOURCE_LOCAL`. Spring Boot behält die jeweilige Boot-Version und Hibernate aus dem BOM.
- Ant-Projekte werden nach Maven überführt: `pom.xml` mit `release` 21 und, bei Konsolenprogrammen, `exec-maven-plugin`. `build.xml` und `nbproject/` entfallen im verschobenen Beispiel, damit nicht zwei Build-Systeme nebeneinander stehen. Betrifft ganz `Java SE` und `Java OO` sowie in der Anwendungsentwicklung `Tag01-awt`, `Tag01-swing`, `Tag02-layouts` und `ContactEditor`.
- Jedes Blatt hat eine eigene `pom.xml` und dieselben Abhängigkeiten wie das Codebeispiel. Paket- und Klassennamen des Beispiels bleiben.
- Eine Anleitung [docs/ide-maven.md](docs/ide-maven.md) gilt für alle Kurse: Projektordner mit `pom.xml` öffnen, nicht den Elternordner. NetBeans: Datei, Projekt öffnen. IntelliJ: Ordner öffnen und Maven importieren. VS Code: Ordner öffnen, Extension Pack for Java. Dazu JDK 21, bei Web Payara 6 unter `C:\payara6`, bei Spring Boot der Hinweis, den vorherigen eingebetteten Tomcat zu stoppen. Ports der bestehenden Labs bleiben.
- Die Modul-`README.md` verlinkt diese Anleitung und nennt nur das Modulspezifische: Lernziel, Main-Klasse oder URL, Befehl (`mvn exec:java`, `mvn test`, `mvn spring-boot:run` oder Deploy). Abschnitte: Codebeispiel, Stufe 1, Stufe 2, Abnahme, Lösung erst nach eigenem Versuch.
- Stufe 1 ist geführt: Gerüst und `TODO`, Schritte und Abnahmekriterien in der README, fachlich derselbe Ausschnitt wie das Beispiel. Stufe 2 ist ein Transfer mit weniger Gerüst. Beide Starter kompilieren. Die prüfenden Tests liegen in `loesung/` und laufen dort mit `mvn test`. Die Wurzel-[README.md](README.md) wird auf die neuen Nummern umgeschrieben.

## Nummerierung

Java SE bleibt in zwei Folgen, weil es zwei Kurse sind.

- Grundlagen: `00-notebook`, `01-typen`, `02-text-zeit`, `03-ticketkasse`, `04-ablaufsteuerung`, `05-debug`, `06-debug-zahlen`, `07-buchverwaltung`
- Erweiterte Techniken: `00-listen`, `01-sets-maps`, `02-generics`, `03-lambda`, `04-funktionen`, `05-streams`, `06-dateien`, `07-threads`, `08-sync`, `09-sockets`

Java OO: `00-typen`, `01-static`, `02-vererbung`, `03-interfaces`, `04-ausnahmen`

Anwendungsentwicklung, Lücke Tag03 geschlossen, ContactEditor als eigenes Modul: `00-awt`, `01-swing`, `02-layouts`, `03-tabellen`, `04-javafx`, `05-contact-editor`, `06-jdbc`, `07-jdbc-pool`, `08-jpa`, `09-jpa-abfragen`, `10-integration`

Java Web Basic: `00-hello` bis `09-jdbc` in der bisherigen Reihenfolge.

Java Web Aufbau, die fehlende 06 wird nicht als Lücke mitgezogen: `00-filter`, `01-listener`, `02-auth`, `03-security`, `04-eigene-tags`, `05-jpa-jdbc`, `06-many-to-one`, `07-jpa-relationen`, `08-jsf`

Spring Boot Basic: bisher `01`–`10` wird `00-rest-api` bis `09-integration`.

Spring Boot AK: `00-custom-starter` bis `06-actuator`. Das bisherige Bündel `08-template-engines-microservices` wird getrennt in `07-template-engines` und `08-microservices`, weil das zwei Lernziele sind. Interne Unterprojekte bleiben im jeweiligen `codebeispiel`.

## Übungsinhalt pro Modul

Die Aufgabe wird aus dem vorhandenen Beispiel abgeleitet, nicht als leere Hülle erfunden.

- Stufe 1 lässt die zentrale Klasse oder den zentralen Endpunkt des Beispiels nachbauen. Die README nennt die erwartete Konsolenausgabe, die URL oder das UI-Verhalten.
- Stufe 2 verlangt eine kleine Erweiterung, die im Beispiel so nicht vorkommt: ein weiterer Typ, ein zweiter Endpunkt, eine zusätzliche Validierung oder eine zweite Relation. Dieselbe Domäne, weniger Vorgaben im Code.
- `loesung/stufe-1` und `loesung/stufe-2` sind vollständige Maven-Projekte und erfüllen die Abnahme.

## Prüfung

Je Kurs nach dem Umbau: `mvn -q -DskipTests compile` auf Codebeispiel und beiden Startern, `mvn -q test` auf beiden Lösungen. Web- und Spring-Boot-Module werden nicht alle deployt; die README-URLs bleiben an den bestehenden Ports und Kontextpfaden.