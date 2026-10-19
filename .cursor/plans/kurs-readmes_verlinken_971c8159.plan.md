---
name: Kurs-READMEs verlinken
overview: Die fehlende Kursübersicht für Java SE anlegen, die Grundlagen-Module verlinken und die Wurzel-README so erweitern, dass jeder Kurs und jedes Modul auf die vorhandene README zeigt.
todos:
  - id: java-se-readme
    content: Java SE/README.md anlegen und Grundlagen-Module sowie Insel-Rücklink setzen
    status: completed
  - id: course-backlinks
    content: Bestehende Kurs-READMEs um den Link zur Wurzel (und bei SE-Unterkursen zur Java-SE-README) ergänzen
    status: completed
  - id: root-readme
    content: Wurzel-README um Blattstruktur, Lesefolge und Links auf Kurs- und Modul-READMEs erweitern
    status: completed
isProject: false
---

# Kurs-READMEs und Wurzelkarte

Die Modul- und Blatt-READMEs sind schon da. Geändert werden nur die Kursübersichten und [README.md](README.md). Sprache bleibt: Kursdateien Deutsch, Wurzel-README Englisch.

## Lücke schließen

Neu: [Java SE/README.md](Java%20SE/README.md). Sie verweist auf die zwei nummerierten Folgen und stellt klar, dass `Java ist auch eine Insel` außerhalb der Reihe liegt.

- [Java SE/Java Grundlagen/README.md](Java%20SE/Java%20Grundlagen/README.md) — Module `00`–`07` sind dort nur als Code gesetzt. Dieselben Namen werden Links auf die Modul-READMEs, im Stil von [Java SE/Java Erweiterte Techniken/README.md](Java%20SE/Java%20Erweiterte%20Techniken/README.md).
- [Java SE/Java ist auch eine Insel/README.md](Java%20SE/Java%20ist%20auch%20eine%20Insel/README.md) bleibt inhaltlich, bekommt nur den Rücklink zur Java-SE-Übersicht.

Die übrigen Kurs-READMEs bleiben die Modulindizes. Jede bekommt eine Zeile zurück zur Wurzel und, bei Java SE, zur neuen Übersicht:

- [Java OO/README.md](Java%20OO/README.md)
- [Java Anwendungsentwicklung/README.md](Java%20Anwendungsentwicklung/README.md)
- [Java Web Basic/README.md](Java%20Web%20Basic/README.md)
- [Java Web Aufbau/README.md](Java%20Web%20Aufbau/README.md)
- [Spring Boot Basic/README.md](Spring%20Boot%20Basic/README.md)
- [Spring Boot AK/README.md](Spring%20Boot%20AK/README.md)

## Wurzel-README erweitern

[README.md](README.md) bleibt die Workspace-Karte (JDK 21, Payara 6, EclipseLink, Spring Boot 3.5.16, Port-Hinweis, Konventionen). Der Abschnitt Course tracks wird zur verlinkten Karte:

- Jede Kursüberschrift verlinkt die Kurs-README. `Java SE` verlinkt zusätzlich Grundlagen und Erweiterte Techniken.
- Jeder Modulname verlinkt die Modul-README, zum Beispiel `[00-notebook](Java%20SE/Java%20Grundlagen/00-notebook/README.md)`.
- Ein kurzer Abschnitt beschreibt die Blattstruktur einmal: Elternordner ohne `pom.xml`, öffnen von `codebeispiel`, `uebung-stufe-1`, `uebung-stufe-2`, `loesung/stufe-1`, `loesung/stufe-2`, mit Link auf [docs/ide-maven.md](docs/ide-maven.md).
- Eine Lesefolge für den Unterricht: Grundlagen, dann Erweiterte Techniken, OO, Anwendungsentwicklung, Web Basic, Web Aufbau. Die beiden Spring-Boot-Kurse stehen daneben, Basic vor AK.

Tooling, Port-Hinweis und Supporting folders bleiben. Keine neuen Fakten zu Versionen oder Ports.
