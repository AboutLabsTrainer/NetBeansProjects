Die Aufgabe dieses Moduls steht in der [README des Elternordners](../README.md).

# Tag 10 Spring Aufbau - Template Engines Demos

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-green.svg)](https://www.thymeleaf.org/)
[![FreeMarker](https://img.shields.io/badge/FreeMarker-2.3-blue.svg)](https://freemarker.apache.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**Multi-Module Maven Projekt** mit 7 Server-Side Template Engines!

**7 Spring Boot Projekte** - Jede wichtige Template Engine mit der gleichen Personen-Liste!

> 📚 Vergleiche alle Template Engines direkt  
> ⭐ Wenn hilfreich, gib dem Projekt einen Stern!


---

## 🏗️ Multi-Module Maven Parent Structure

```
tag10-springAufbau-template-engines-demos/
├── pom.xml                    ← PARENT POM (Multi-Module!)
├── thymeleaf-demo/            ← Thymeleaf Module
├── freemarker-demo/           ← FreeMarker Module
├── jte-demo/                  ← JTE Module
├── groovy-demo/               ← Groovy Templates Module
├── pebble-demo/               ← Pebble Module
├── mustache-demo/             ← Mustache Module
└── jsp-demo/                  ← JSP Module
```

**Ein Befehl baut alles:** `mvn clean install` im Root-Verzeichnis!

---

## 📦 Projekte

| Template Engine | Port | Ordner | Beschreibung |
|-----------------|------|--------|--------------|
| **Thymeleaf** | 8080 | `thymeleaf-demo` | Standard für Spring Boot, Natural Templates |
| **FreeMarker** | 8081 | `freemarker-demo` | Enterprise-Klassiker, mächtig & flexibel |
| **JTE** | 8082 | `jte-demo` | Type-Safe, kompiliert zu Bytecode, schnellste! |
| **Groovy** | 8083 | `groovy-demo` | DSL-basiert, elegante Syntax |
| **Pebble** | 8084 | `pebble-demo` | Modern, Twig-ähnlich, gute Error-Messages |
| **Mustache** | 8085 | `mustache-demo` | Logikfrei, simpel, isomorph |
| **JSP** | 8086 | `jsp-demo` | Legacy-Technologie, JSTL |

---

## 🚀 Schnellstart

### Einzelnes Projekt starten

```bash
cd thymeleaf-demo
mvn spring-boot:run
```

Dann öffne: `http://localhost:8080`

### Alle Projekte testen

Du kannst alle 7 Projekte parallel starten (verschiedene Ports) und vergleichen:

```bash
# Terminal 1
cd thymeleaf-demo && mvn spring-boot:run

# Terminal 2
cd freemarker-demo && mvn spring-boot:run

# Terminal 3
cd jte-demo && mvn spring-boot:run

# ... und so weiter
```

---

## 📋 Was bietet jedes Projekt?

Jedes der 7 Projekte enthält:

- ✅ **50 zufällige Personen** generiert mit DataFaker
- ✅ **Vollständiges Spring Boot Setup** (Java 21, Maven)
- ✅ **Model, Service, Controller** - saubere Architektur
- ✅ **Schönes, responsives CSS** mit Gradient-Design
- ✅ **README mit Infos** zur jeweiligen Template Engine
- ✅ **Sofort lauffähig** - kein zusätzlicher Setup nötig

---

## 🎯 Für wen ist das?

Diese Demo-Collection ist perfekt für:

- 📚 **Spring Boot Lernende** (Tag 10 des Aufbau-Kurses)
- 🔍 **Template Engine Vergleiche** - Welche ist die Beste für dein Projekt?
- 💼 **Portfolio-Projekte** - Zeig dein Spring Boot Know-how
- 🎓 **Lern-Demos** - Verstehe Template Engines durch Praxis

---

## 🔧 Voraussetzungen

- **Java 21**
- **Maven 3.8+**
- (Optional) IDE wie IntelliJ IDEA oder Eclipse

---

## 📖 Template Engine Übersicht

### Thymeleaf ⭐ Empfohlen für die meisten Projekte
- Standard für Spring Boot
- Natural Templates (können im Browser geöffnet werden)
- Starke Spring-Integration
- Große Community

### FreeMarker 💼 Enterprise-Wahl
- Mächtige Template-Sprache
- Sehr flexibel
- Weit verbreitet in großen Unternehmen
- Ausgereifte, stabile Technologie

### JTE ⚡ Schnellste Performance
- Type-Safety zur Compile-Zeit
- Kompiliert zu Bytecode
- Extrem schnell!
- Hot-Reload während Entwicklung

### Groovy Templates 🎨 Für Groovy-Fans
- DSL-basierte Template-Sprache
- Elegante, ausdrucksstarke Syntax
- Volle Groovy-Power in Templates

### Pebble 🆕 Modern & Developer-Friendly
- Twig-ähnliche Syntax (aus PHP bekannt)
- Schnelle Performance
- Hervorragende Error-Messages

### Mustache 🎯 Simpel & Logikfrei
- Einfachste Template-Syntax
- Keine Logik in Templates
- Isomorph (Java, JavaScript, etc.)

### JSP 📜 Legacy, aber wichtig
- Für Wartung alter Anwendungen
- JSTL (JSP Standard Tag Library)
- Nicht für neue Projekte empfohlen

---

## 🎓 Lernpfad-Empfehlung

1. **Start:** Thymeleaf (Standard, am einfachsten)
2. **Dann:** FreeMarker (Enterprise-Standard)
3. **Performance:** JTE (wenn Performance kritisch ist)
4. **Spezial:** Andere je nach Projektanforderungen

---

## 📝 Lizenz

MIT License - Frei verwendbar für Lern- und Portfolio-Zwecke!

---

## 🤝 Beitragen

Pull Requests willkommen! Fehler gefunden? Issue erstellen!

---


🚀 Viel Erfolg beim Lernen und Entwickeln!
