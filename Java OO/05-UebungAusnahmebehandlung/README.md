# 05-Übung: Sichere Mietpreisberechnung mit Ausnahmebehandlung

## 📋 Projektbeschreibung

Dieses Projekt implementiert einen robusten Mietangebot-Rechner für die Verleihfirma IBB-Rent. Der Rechner demonstriert den professionellen Umgang mit Exceptions in Java und zeigt verschiedene Techniken der Fehlerbehandlung.

**Projektname:** `05-UebungAusnahmebehandlung`  
**Package:** `de.ibb.uebung.exceptions`

## 🎯 Lernziele

Die Übung vermittelt folgende Konzepte der Java-Ausnahmebehandlung:

- ✅ Absicherung von Benutzereingaben mit `try/catch`
- ✅ Eigene geprüfte Exceptions (`checked exceptions`) erstellen
- ✅ `finally`-Block für Aufräumarbeiten
- ✅ `try-with-resources` für automatisches Ressourcen-Management
- ✅ Unterscheidung zwischen `checked` und `unchecked` Exceptions
- ✅ Unterschied zwischen technischen und fachlichen Fehlern

## 📁 Projektstruktur

```
05-UebungAusnahmebehandlung/
├── src/
│   └── de/
│       └── ibb/
│           └── uebung/
│               └── exceptions/
│                   ├── MietRechner.java
│                   └── UngueltigeMietdauerException.java
├── angebote.txt
└── README.md
```

## 🔧 Implementierte Komponenten

### 1. Klasse `UngueltigeMietdauerException`

Eine eigene geprüfte Exception-Klasse für fachlich ungültige Mietdauern:

```java
public class UngueltigeMietdauerException extends Exception {
    public UngueltigeMietdauerException(String message) {
        super(message);
    }
}
```

**Eigenschaften:**
- Erweitert `Exception` → `checked exception`
- Muss bei Verwendung deklariert werden (`throws`)
- Für fachliche Validierungsfehler (z.B. Mietdauer > 30 Tage)

### 2. Klasse `MietRechner`

Die Hauptklasse mit folgenden Methoden:

#### `main(String[] args)`
- Liest Tagespreis und Anzahl Tage von der Konsole
- Absicherung mit `try/catch` für Eingabefehler
- Verwendet `finally`-Block für Ressourcenfreigabe
- Ruft weitere Demo-Methoden auf

#### `berechneMietpreis(double preisProTag, int tage)`
- Berechnet den Gesamtpreis: `preisProTag * tage`
- **Validierungen:**
  - `tage <= 0` → Exception: "Mietdauer muss positiv sein."
  - `tage > 30` → Exception: "Mietdauer darf 30 Tage nicht überschreiten."
  - `preisProTag < 0` → Exception: "Tagespreis darf nicht negativ sein."
- Wirft `UngueltigeMietdauerException` bei Verletzung der Geschäftsregeln

#### `leseAngeboteAusDatei(String dateiname)`
- Liest Mietangebote aus einer Textdatei
- Verwendet `try-with-resources` mit `BufferedReader`
- **Dateiformat:** `Bezeichnung;PreisProTag;Tage` (Semikolon-getrennt)
- **Fehlerbehandlung:**
  - `IOException` bei Dateizugriffsfehlern
  - `NumberFormatException` bei ungültigen Zahlenformaten
  - Warnung bei Formatfehlern, Weiterverarbeitung anderer Zeilen

#### `provoziereRuntimeException()`
- Demonstration von `unchecked exceptions`
- Provoziert `ArrayIndexOutOfBoundsException`
- Zeigt, dass RuntimeExceptions nicht deklariert werden müssen

## 📊 Übungsstufen

### Stufe 1: Benutzereingaben absichern
**Ziel:** Eingaben mit `try/catch` absichern

- Scanner für `double` (Tagespreis) und `int` (Tage)
- `InputMismatchException` abfangen
- Verständliche Fehlermeldungen ausgeben
- Programm beendet sich bei Eingabefehler

**Reflexion:** Warum ist `try/catch` hier wichtig?  
→ Ohne Exception-Handling würde das Programm bei falscher Eingabe abstürzen.

### Stufe 2: Eigene geprüfte Exception
**Ziel:** Fachliche Fehler mit eigener Exception behandeln

- `UngueltigeMietdauerException` erstellt
- `berechneMietpreis()` wirft Exception bei ungültigen Werten
- Unterscheidung: **technischer Fehler** (falscher Datentyp) vs. **fachlicher Fehler** (ungültige Geschäftsregel)

### Stufe 3: finally und Aufräumen
**Ziel:** Ressourcen immer freigeben

- `finally`-Block wird immer ausgeführt
- Scanner wird im `finally` geschlossen
- Garantiert Ressourcenfreigabe auch bei Exceptions

### Stufe 4: Datei einlesen mit try-with-resources
**Ziel:** Automatisches Ressourcen-Management

- `try-with-resources` schließt Datei automatisch
- Kein manuelles `close()` nötig
- Robustes Einlesen von `angebote.txt`

### Stufe 5: Bonus - Unchecked Exception & Error
**Ziel:** Verständnis für Exception-Typen

- `RuntimeException` demonstriert (unchecked)
- Kommentare zu `Error`-Typen (z.B. `OutOfMemoryError`)
- Unterschiede zwischen Exception-Typen erklärt

## 📝 Datei `angebote.txt`

Die Beispieldatei enthält verschiedene Szenarien:

```
Auto;49.99;3
Fahrrad;19.50;2
Bohrmaschine;25.00;5
KaputterEintrag;xx;3          # Zahlenformatfehler
ZuLang;30.00;40                # Mietdauer > 30 Tage
```

**Format:** `Bezeichnung;PreisProTag;Tage`

## 🚀 Verwendung

### Kompilieren und Ausführen

1. **In NetBeans:**
   - Projekt öffnen
   - `F6` drücken oder Rechtsklick → Run

2. **Über Kommandozeile:**
   ```bash
   javac -d build/classes src/de/ibb/uebung/exceptions/*.java
   java -cp build/classes de.ibb.uebung.exceptions.MietRechner
   ```

### Testfälle

**1. Gültige Eingabe:**
```
Tagespreis: 49.99
Tage: 3
```
→ Erfolgreiche Berechnung

**2. Eingabefehler (falscher Datentyp):**
```
Tagespreis: abc
```
→ `InputMismatchException` wird abgefangen, Programm beendet

**3. Fachlicher Fehler (ungültige Mietdauer):**
```
Tagespreis: 50.00
Tage: 35
```
→ `UngueltigeMietdauerException`: "Mietdauer darf 30 Tage nicht überschreiten."

**4. Negativer Wert:**
```
Tagespreis: 50.00
Tage: -5
```
→ `UngueltigeMietdauerException`: "Mietdauer muss positiv sein."

**5. Datei nicht gefunden:**
- Datei `angebote.txt` löschen/umbenennen
- → `IOException` wird abgefangen

## 📚 Wichtige Konzepte

### Exception-Typen in Java

| Typ | Erweitert | Muss deklariert werden? | Beispiel |
|-----|-----------|-------------------------|----------|
| **Checked Exception** | `Exception` | ✅ Ja (`throws`) | `IOException`, `UngueltigeMietdauerException` |
| **Unchecked Exception** | `RuntimeException` | ❌ Nein | `ArrayIndexOutOfBoundsException`, `NullPointerException` |
| **Error** | `Error` | ❌ Nein | `OutOfMemoryError`, `StackOverflowError` |

### try-catch-finally

```java
try {
    // Code, der Exceptions werfen kann
} catch (ExceptionType ex) {
    // Fehlerbehandlung
} finally {
    // Wird IMMER ausgeführt (auch bei Exceptions)
    // Für Aufräumarbeiten (z.B. Ressourcen schließen)
}
```

### try-with-resources

```java
try (BufferedReader reader = new BufferedReader(new FileReader(datei))) {
    // Datei verwenden
} catch (IOException ex) {
    // Fehlerbehandlung
}
// Datei wird automatisch geschlossen!
```

**Vorteil:** Automatisches Ressourcen-Management, kein manuelles `close()` nötig.

## 🔍 Unterschiede: Technischer vs. Fachlicher Fehler

- **Technischer Fehler:** 
  - Falscher Datentyp (`InputMismatchException`)
  - Datei nicht gefunden (`IOException`)
  - → System/Programmierfehler

- **Fachlicher Fehler:**
  - Geschäftsregel verletzt (z.B. Mietdauer > 30 Tage)
  - → `UngueltigeMietdauerException` (eigene Exception)

## ✅ Checkliste für Tests

- [x] Gültige Werte eingeben → erfolgreiche Berechnung
- [x] `tage = 0` oder negativ → Exception mit Fehlermeldung
- [x] `tage > 30` → Exception mit Fehlermeldung
- [x] Ungültige Texteingabe (z.B. "abc") → `InputMismatchException`
- [x] Datei `angebote.txt` löschen → `IOException` wird behandelt
- [x] Formatfehler in Datei → Warnung, andere Zeilen werden weiterverarbeitet
- [x] `finally`-Block wird immer ausgeführt
- [x] Scanner wird korrekt geschlossen

## 📖 Zusammenfassung

Dieses Projekt demonstriert eine professionelle Java-Implementierung mit:

- ✅ Robuster Fehlerbehandlung
- ✅ Eigener Exception-Klassen für fachliche Validierung
- ✅ Automatischem Ressourcen-Management
- ✅ Unterschiedlichen Exception-Typen und deren Verwendung
- ✅ Klarer Trennung zwischen technischen und fachlichen Fehlern

Die Implementierung folgt Best Practices und ist produktionsreif für einfache Anwendungsfälle.

---
**Erstellt für:** Übung Ausnahmebehandlung, IBB-Rent  
**Version:** 1.0  
**Datum:** 2024

