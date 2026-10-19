# HelloPayara – Tag 2 Übungen (HTTP & Sessions)

Kleines NetBeans/Maven-WAR-Projekt für Payara 6 (Jakarta Servlet 6, Java 21). Es demonstriert grundlegende HTTP- und Session-Konzepte ohne Datenbank- oder JSP-Einsatz.

## Voraussetzungen
- Java 21 (JDK)
- Maven 3.9+
- Payara Server 7 (HTTP-Port 8080) (als lokal installierter oder in NetBeans konfigurierter Server)
- NetBeans 21+ mit Payara-Plugin (optional, aber empfohlen)

## Projekt öffnen & ausführen (NetBeans)
1. `Datei → Öffnen` und den Ordner `HelloPayara-Tag2-Exercises` (mit `pom.xml`) auswählen.
2. Sicherstellen, dass der Payara-Server in NetBeans registriert ist (`Dienste → Server`).
3. Rechtsklick auf das Projekt → `Bereitstellen` (oder `Run`). NetBeans baut das WAR und deployed es automatisch.
4. Die Standardbasis lautet `http://localhost:8080/HelloPayara/`.

> Hinweis: Maven-Builds starten mit der JVM-Option `--sun-misc-unsafe-memory-access=allow` (siehe `.mvn/jvm.config`), um Guice/Unsafe-Warnungen während des Builds zu vermeiden.

## Endpunkte & Verhalten
- Basis: [`http://localhost:8080/HelloPayara/`](http://localhost:8080/HelloPayara/)
- [`/products`](http://localhost:8080/HelloPayara/products) – Liste von 20 Demo-Produkten
- [`/product/{id}`](http://localhost:8080/HelloPayara/product/1) – Produktdetail, legt das zuletzt betrachtete Produkt in der Session ab
- [`/lastViewed`](http://localhost:8080/HelloPayara/lastViewed) – liest das Session-Attribut `lastViewed` aus
- [`/login`](http://localhost:8080/HelloPayara/login) (GET/POST) – minimales Login (`anna@example.com` / `password123`)
- [`/dashboard`](http://localhost:8080/HelloPayara/dashboard) (GET) – geschützter Bereich, setzt aktive Session voraus
- [`/logout`](http://localhost:8080/HelloPayara/logout) (POST) – invalidiert die Session
- [`/method-echo`](http://localhost:8080/HelloPayara/method-echo) – gibt HTTP-Methode und Header zurück (Demo)

## CLI-Build (optional)
```bash
mvn clean verify
```
Das erzeugte WAR befindet sich anschließend in `target/HelloPayara-Tag2-Exercises.war` und kann manuell auf einen Payara-Server deployed werden.

## Architektur-Hinweise
- Reine Servlet-Beispiele ohne Persistenzlayer
- Sessions dienen zur Speicherung der zuletzt betrachteten Produkte sowie zur Authentifizierung
- Fokus: Verständnis der HTTP-Mechanik (Methoden, Header, Statuscodes) und des Servlet-Lebenszyklus