---
name: Legacy-Code Generics Fix
overview: "Implementiere die Mini-Challenge \"Level 1: Legacy-Code fixen\" exakt wie auf der Webseite beschrieben. Die Aufgabe besteht darin, einen BestellService mit Raw Types zu reparieren, indem Generics hinzugefügt, alle Casts entfernt und Typsicherheit gewährleistet wird."
todos:
  - id: "0"
    content: Erstelle NetBeans Ant-Projektstruktur (build.xml, nbproject/, src/)
    status: completed
  - id: "1"
    content: Erstelle Bestellung.java mit produkt (String), preis (double), Konstruktor und Getter-Methoden
    status: completed
  - id: "2"
    content: Erstelle BestellService.java mit Raw Types (wie im Legacy-Code beschrieben)
    status: completed
  - id: "3"
    content: "Repariere BestellService: Füge Generics hinzu (List<Bestellung>), ändere Parameter von Object zu Bestellung, entferne alle Casts"
    status: completed
  - id: "4"
    content: Erstelle BestellServiceTest.java mit Tests für Typsicherheit und Bonus-Test für Compile-Error bei falschen Typen
    status: completed
---

# Level 1: Legacy-Code fixen - Generics Challenge

## Ziel

Repariere den Legacy-Code `BestellService`, der Raw Types verwendet, indem Generics hinzugefügt, alle Casts entfernt und Typsicherheit zur Compile-Zeit gewährleistet wird.

## Zu implementierende Dateien

### 1. `Bestellung.java`

- Klasse mit `produkt` (String) und `preis` (double)
- Konstruktor und Getter-Methoden
- Diese Klasse ist bereits korrekt definiert

### 2. `BestellService.java` (zu reparieren)

**Aktuelle Probleme:**

- Raw Type: `private List bestellungen = new ArrayList();`
- Raw Type: `public List getBestellungenUeber(...)`
- Unsicherer Parameter: `public void addBestellung(Object bestellung)`
- Explizite Casts: `(Bestellung) obj` in mehreren Methoden

**Zu implementieren:**

- Generics hinzufügen: `List<Bestellung>`
- Typsichere Methoden ohne Casts
- Compile-Zeit-Prüfung für falsche Typen

### 3. `BestellServiceTest.java`

- Test, der beweist, dass der Code typsicher ist
- Test für den Bonus-Fall: `addBestellung("Keine echte Bestellung")` sollte einen Compile-Error verursachen

## Implementierungsdetails

### BestellService - Vorher (mit Fehlern):

```java
private List bestellungen = new ArrayList();
public void addBestellung(Object bestellung)
public List getBestellungenUeber(double minWert)
// Mit expliziten Casts: (Bestellung) obj
```

### BestellService - Nachher (typsicher):

```java
private List<Bestellung> bestellungen = new ArrayList<>();
public void addBestellung(Bestellung bestellung)
public List<Bestellung> getBestellungenUeber(double minWert)
// Keine Casts mehr nötig
```

## Aufgaben

0. ✅ Erstelle NetBeans Ant-Projektstruktur
1. ✅ Identifiziere alle Raw Types
2. ✅ Füge Generics hinzu (`List<Bestellung>`)
3. ✅ Entferne alle Casts
4. ✅ Schreibe einen Test, der beweist, dass der Code typsicher ist
5. ✅ Bonus: Dokumentiere, was bei `addBestellung("String")` passiert (vorher vs. nachher)

## NetBeans Ant-Projekt Setup

Das Projekt wird als NetBeans Ant-Projekt erstellt mit:

- `build.xml`: Standard Ant-Build-Datei für Java-Projekte
- `nbproject/project.xml`: NetBeans Projekt-Konfiguration
- `nbproject/build-impl.xml`: NetBeans Build-Implementierung (kann von NetBeans generiert werden)
- Standard-Verzeichnisstruktur: `src/`, `build/`, `dist/`

## Projektstruktur (NetBeans Ant-Projekt)

```
03-collections-generics/
├── build.xml                    # Ant Build-Datei
├── nbproject/                   # NetBeans Projekt-Konfiguration
│   ├── project.xml              # Projekt-Metadaten
│   ├── build-impl.xml           # NetBeans Build-Implementierung
│   └── ...                      # Weitere NetBeans-Konfigurationsdateien
├── src/                         # Quellcode-Verzeichnis
│   └── (package)/
│       ├── Bestellung.java
│       ├── BestellService.java
│       └── BestellServiceTest.java
├── build/                       # Kompilierte Klassen (wird generiert)
└── dist/                        # JAR-Dateien (wird generiert)
```

## Erwartetes Ergebnis

- Der Code kompiliert ohne Warnings
- Alle Methoden sind typsicher
- `addBestellung("String")` verursacht einen Compile-Error (nicht mehr zur Laufzeit)
- Tests beweisen die Typsicherheit