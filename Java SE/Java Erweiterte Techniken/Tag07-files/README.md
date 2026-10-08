# Tag 7 – Dateien lesen, schreiben und verwalten (Mini‑Challenge)

Dieses Projekt ist ein **NetBeans-kompatibles Ant‑Projekt** zur vollständigen Umsetzung der Mini‑Challenge aus  
**„TAG 7: Dateien lesen, schreiben und verwalten“** auf [java-developer.online](https://www.java-developer.online/tag-7-dateien-lesen-schreiben/).

## 📋 Projektübersicht

Das Projekt demonstriert die moderne **Java NIO.2 API** (`java.nio.file`) für Dateioperationen und setzt alle drei Schwierigkeitsgrade der Challenge um:

- **🟢 Level 1 (Einsteiger)**: Grundlegende Dateioperationen (Lesen, Schreiben, Prüfen)
- **🟡 Level 2 (Fortgeschritten)**: Properties, rekursive Verzeichnissuche, Dateikopien
- **🔵 Level 3 (Profi)**: Config-Manager mit Defaults, CSV-Parsing, Backup-Service

### Technologie-Stack

- **Java 21** (LTS)
- **NIO.2 API** (`Path`, `Files`, `Stream`)
- **Try-with-Resources** für sichere Ressourcenverwaltung
- **NetBeans Ant Build** (kompatibel mit NetBeans IDE)
- **UTF-8 Encoding** für plattformunabhängige Textverarbeitung

---

## 🏗️ Projektstruktur

```mermaid
graph TD
    A[07-file-io/] --> B[build.xml]
    A --> C[nbproject/]
    A --> D[src/]
    A --> E[data/]
    A --> F[README.md]
    
    C --> C1[project.xml]
    C --> C2[project.properties]
    C --> C3[build-impl.xml]
    
    D --> D1[tag07/fileio/]
    D1 --> D2[Main.java]
    D1 --> D3[tasks/]
    D1 --> D4[config/]
    D1 --> D5[csv/]
    D1 --> D6[backup/]
    
    D3 --> D3A[Level1Tasks.java]
    D3 --> D3B[Level2Tasks.java]
    D3 --> D3C[Level3Tasks.java]
    
    D4 --> D4A[ConfigManager.java]
    D5 --> D5A[Person.java]
    D5 --> D5B[CsvParser.java]
    D6 --> D6A[BackupService.java]
    
    E --> E1[text/]
    E --> E2[config/]
    E --> E3[csv/]
    E --> E4[java-scan/]
    E --> E5[copy-src/]
    E --> E6[copy-dst/]
    E --> E7[backup/]
    
    E1 --> E1A[input.txt]
    E1 --> E1B[output.txt]
    E2 --> E2A[app.properties]
    E3 --> E3A[daten.csv]
```

---

## 🎯 Architektur

### Klassendiagramm

```mermaid
classDiagram
    class Main {
        -Path DATA_DIR
        +main(String[] args)
        -runMenu()
        -runByArg(String arg)
    }
    
    class Level1Tasks {
        +runAll(Path dataDir)
        -countLines(Path file)
        -writeListOfStrings(Path file)
        -checkExistsAndReadable(Path file)
    }
    
    class Level2Tasks {
        +runAll(Path dataDir)
        -printDbEntries(Path propertiesFile)
        -findJavaFilesRecursively(Path dir)
        -copyTxtFiles(Path srcDir, Path dstDir)
    }
    
    class Level3Tasks {
        +runAll(Path dataDir)
        -configManagerWithDefaults(Path configFile)
        -parseCsvToObjects(Path csvFile)
        -backupWithTimestamp(Path sourceFile, Path backupDir)
    }
    
    class ConfigManager {
        -Path file
        -Properties props
        +loadOrCreate(Path file) ConfigManager
        +getString(String key, String defaultValue) String
        +getInt(String key, int defaultValue) int
        +getBoolean(String key, boolean defaultValue) boolean
        +getDbEntries() Map~String,String~
        +set(String key, String value)
        +save(String comment)
    }
    
    class CsvParser {
        +readPersons(Path csvFile) List~Person~
    }
    
    class Person {
        -String name
        -int age
        -String city
        +Person(String name, int age, String city)
        +toString() String
    }
    
    class BackupService {
        +backupWithTimestamp(Path sourceFile, Path backupDir) Path
    }
    
    Main --> Level1Tasks : verwendet
    Main --> Level2Tasks : verwendet
    Main --> Level3Tasks : verwendet
    Level3Tasks --> ConfigManager : verwendet
    Level3Tasks --> CsvParser : verwendet
    Level3Tasks --> BackupService : verwendet
    CsvParser --> Person : erzeugt
```

### Ablaufdiagramm

```mermaid
flowchart TD
    Start([Programm startet]) --> CheckArgs{Argumente vorhanden?}
    CheckArgs -->|Ja| ParseArg[Argument parsen]
    CheckArgs -->|Nein| ShowMenu[Menü anzeigen]
    
    ParseArg --> ArgLevel1[Level 1 ausführen]
    ParseArg --> ArgLevel2[Level 2 ausführen]
    ParseArg --> ArgLevel3[Level 3 ausführen]
    ParseArg --> ArgAll[Alle Level ausführen]
    
    ShowMenu --> UserInput[Benutzer-Eingabe]
    UserInput --> Menu1[Level 1]
    UserInput --> Menu2[Level 2]
    UserInput --> Menu3[Level 3]
    UserInput --> Menu4[Alle Level]
    UserInput --> Menu0[Beenden]
    
    Menu1 --> L1[Level1Tasks.runAll]
    Menu2 --> L2[Level2Tasks.runAll]
    Menu3 --> L3[Level3Tasks.runAll]
    Menu4 --> L1
    Menu4 --> L2
    Menu4 --> L3
    Menu0 --> End([Programm beendet])
    
    ArgLevel1 --> L1
    ArgLevel2 --> L2
    ArgLevel3 --> L3
    ArgAll --> L1
    ArgAll --> L2
    ArgAll --> L3
    
    L1 --> L1A[Zeilen zählen]
    L1 --> L1B[Liste schreiben]
    L1 --> L1C[Exists/Readable prüfen]
    
    L2 --> L2A[db.* Properties ausgeben]
    L2 --> L2B[.java rekursiv finden]
    L2 --> L2C[.txt kopieren]
    
    L3 --> L3A[ConfigManager mit Defaults]
    L3 --> L3B[CSV parsen]
    L3 --> L3C[Backup mit Timestamp]
    
    L1A --> End
    L1B --> End
    L1C --> End
    L2A --> End
    L2B --> End
    L2C --> End
    L3A --> End
    L3B --> End
    L3C --> End
```

---

## 📚 Detaillierte Aufgaben-Übersicht

### 🟢 Level 1 – Einsteiger

**Aufgabe 1: Textdatei lesen und Zeilen zählen**

```java
try (var stream = Files.lines(file, StandardCharsets.UTF_8)) {
    long lines = stream.count();
}
```

- Verwendet `Files.lines()` für Stream-basiertes Lesen
- Try-with-Resources stellt automatisches Schließen sicher
- UTF-8 Encoding für korrekte Umlaute

**Aufgabe 2: Liste von Strings in Datei schreiben**

```java
List<String> lines = List.of("Zeile 1", "Zeile 2", "Zeile 3");
Files.write(file, lines, StandardCharsets.UTF_8, 
    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
```

- `Files.write()` für atomares Schreiben
- `TRUNCATE_EXISTING` überschreibt vorhandene Datei

**Aufgabe 3: Datei existiert und lesbar prüfen**

```java
boolean exists = Files.exists(file);
boolean readable = Files.isReadable(file);
```

- Plattformunabhängige Prüfungen mit NIO.2

---

### 🟡 Level 2 – Fortgeschritten

**Aufgabe 1: Properties laden und `db.*` Einträge ausgeben**

```java
Properties props = new Properties();
try (InputStream in = Files.newInputStream(propertiesFile)) {
    props.load(in);
}
props.stringPropertyNames().stream()
    .filter(k -> k.startsWith("db."))
    .forEach(k -> System.out.println(k + "=" + props.getProperty(k)));
```

- Stream-API für elegante Filterung
- Try-with-Resources für InputStream

**Aufgabe 2: `.java` Dateien rekursiv finden**

```java
try (Stream<Path> walk = Files.walk(dir)) {
    walk
        .filter(Files::isRegularFile)
        .filter(p -> p.getFileName().toString().endsWith(".java"))
        .forEach(System.out::println);
}
```

- `Files.walk()` für rekursive Verzeichnisdurchquerung
- Methodenreferenzen (`Files::isRegularFile`) für sauberen Code

**Aufgabe 3: `.txt` Dateien von A nach B kopieren**

```java
try (Stream<Path> list = Files.list(srcDir)) {
    list
        .filter(Files::isRegularFile)
        .filter(p -> p.getFileName().toString().endsWith(".txt"))
        .forEach(p -> Files.copy(p, dstDir.resolve(p.getFileName()), 
            StandardCopyOption.REPLACE_EXISTING));
}
```

- `Files.list()` für flache Verzeichnisauflistung
- `Files.copy()` mit `REPLACE_EXISTING` Option

---

### 🔵 Level 3 – Profi

**Aufgabe 1: Config-Manager mit Default-Werten**

Die `ConfigManager`-Klasse implementiert:

- **Lazy Loading**: Erstellt Default-Properties, falls Datei nicht existiert
- **Type-Safe Access**: `getString()`, `getInt()`, `getBoolean()` mit Defaults
- **Non-Destructive**: Fehlende Werte werden mit Defaults ergänzt, vorhandene bleiben unverändert
- **Filterung**: `getDbEntries()` liefert alle `db.*` Einträge als `Map`

```java
ConfigManager cfg = ConfigManager.loadOrCreate(configFile);
String host = cfg.getString("db.host", "localhost");
int port = cfg.getInt("db.port", 5432);
Map<String, String> dbEntries = cfg.getDbEntries();
```

**Aufgabe 2: CSV in Objekte parsen**

Die `CsvParser`-Klasse:

- Liest CSV mit Semikolon-Trenner (`;`)
- Überspringt Header-Zeile
- Mappt jede Zeile zu einem `Person`-Objekt
- Verwendet Stream-API für funktionale Transformation

```java
List<Person> persons = CsvParser.readPersons(csvFile);
// Person: name, age, city
```

**Aufgabe 3: Backup mit Timestamp**

Die `BackupService`-Klasse:

- Erstellt Kopie mit Format: `<originalname>_<yyyyMMdd_HHmmss>.<ext>`
- Verwendet `LocalDateTime` für plattformunabhängige Zeitstempel
- Erstellt Backup-Verzeichnis automatisch, falls nicht vorhanden

```java
Path backup = BackupService.backupWithTimestamp(sourceFile, backupDir);
// Ergebnis: input_20250120_143052.txt
```

---

## 🗂️ Datenbasis (`data/`-Ordner)

Das Projekt verwendet ausschließlich **relative Pfade** mit `java.nio.file.Path`/`Files`.  
Alle Ein- und Ausgaben liegen unter dem Ordner `data/`:

```
data/
├── text/
│   ├── input.txt          # Beispiel-Textdatei (Level 1)
│   └── output.txt         # Ausgabe der String-Liste (Level 1)
├── config/
│   └── app.properties     # Beispiel-Konfiguration (db.*, app.*)
├── csv/
│   └── daten.csv          # Beispiel-CSV (Name;Alter;Stadt)
├── java-scan/             # Dummy-.java-Dateien zum rekursiven Finden
│   ├── Root.java
│   ├── a/
│   │   ├── A1.java
│   │   └── note.txt
│   └── b/
│       ├── B1.java
│       └── inner/
│           └── B2.java
├── copy-src/              # Quelle für .txt-Kopien
│   ├── eins.txt
│   ├── zwei.txt
│   └── ignore.md
├── copy-dst/              # Ziel für .txt-Kopien (wird befüllt)
└── backup/                # Hier landen Timestamp-Backups
```

**Wichtig**: Fehlende Dateien/Ordner werden beim ersten Lauf – soweit notwendig – automatisch angelegt (idempotente Initialisierung).

---

## 🚀 Projekt öffnen und ausführen

### NetBeans IDE

1. **NetBeans starten**
2. **File → Open Project…**
3. Ordner `07-file-io` auswählen (dort, wo diese `README.md` liegt)
4. Projekt sollte als **Java Application (Ant)** erkannt werden  
   (`build.xml` und `nbproject/` vorhanden)
5. Projekt markieren → Rechtsklick → **Run**  
   → Es startet ein Konsolen‑Menü mit den Optionen für Level 1–3 bzw. „alle“.

### Kommandozeile

Im Projektverzeichnis `07-file-io`:

```bash
# Build und Run
ant run

# Oder direkt mit Java (nach Build)
java -cp dist/tag07-file-io.jar de.ibb.fileio.Main level1
java -cp dist/tag07-file-io.jar de.ibb.fileio.Main level2
java -cp dist/tag07-file-io.jar de.ibb.fileio.Main level3
java -cp dist/tag07-file-io.jar de.ibb.fileio.Main all
```

### Menü-Optionen

Bei interaktivem Start (ohne Argumente) erscheint ein Menü:

```
=== Tag 7: File I/O Mini-Challenge (Level 1–3) ===
Arbeitsverzeichnis: C:\Users\...\07-file-io
Datenbasis:         C:\Users\...\07-file-io\data

1) Level 1 (Einsteiger) ausführen
2) Level 2 (Fortgeschritten) ausführen
3) Level 3 (Profi) ausführen
4) Alle Level ausführen
0) Beenden
>
```

---

## 🔧 Technische Details

### NIO.2 API – Warum modern?

**Vorteile gegenüber `java.io.File`:**

- ✅ **Plattformunabhängig**: `Path.of("ordner", "datei.txt")` funktioniert auf Windows, macOS, Linux
- ✅ **Stream-Support**: `Files.lines()`, `Files.walk()` für funktionale Programmierung
- ✅ **Bessere API**: Weniger Boilerplate, mehr Ausdruckskraft
- ✅ **Try-with-Resources**: Automatisches Ressourcen-Management

**Wichtige Klassen:**

- `Path`: Repräsentiert Dateipfade (ersetzt `File`)
- `Files`: Statische Utility-Methoden für alle Dateioperationen
- `StandardOpenOption`: Optionen für Datei-Öffnungen (`CREATE`, `APPEND`, `TRUNCATE_EXISTING`)
- `StandardCopyOption`: Optionen für Kopieroperationen (`REPLACE_EXISTING`, `ATOMIC_MOVE`)

### Ressourcen-Management

**Kritisch**: Alle Streams, Reader und Writer müssen geschlossen werden!

```java
// ✅ RICHTIG: Try-with-Resources
try (Stream<String> lines = Files.lines(path)) {
    lines.forEach(System.out::println);
} // Stream wird automatisch geschlossen

// ❌ FALSCH: Resource Leak!
Stream<String> lines = Files.lines(path);
lines.forEach(System.out::println);
// Stream bleibt offen → Datei blockiert!
```

### Encoding

Das Projekt verwendet durchgängig **UTF-8**:

```java
Files.readAllLines(path, StandardCharsets.UTF_8);
Files.writeString(path, text, StandardCharsets.UTF_8);
```

Dies stellt sicher, dass Umlaute (äöü) und Sonderzeichen korrekt verarbeitet werden.

---

## 📖 Weiterführende Ressourcen

- **Original-Artikel**: [TAG 7: Dateien lesen, schreiben und verwalten](https://www.java-developer.online/tag-7-dateien-lesen-schreiben/)
- **Oracle Tutorial**: [File I/O (NIO.2)](https://docs.oracle.com/javase/tutorial/essential/io/file.html)
- **Baeldung**: [Java NIO2 File API](https://www.baeldung.com/java-nio-2-file-api)

---

## ✅ Akzeptanzkriterien

- ✅ Projekt lässt sich in NetBeans **als Ant-Projekt öffnen** und mit **Run** starten
- ✅ Alle Challenge-Aufgaben (Level 1–3) sind vollständig implementiert
- ✅ Jede Aufgabe ist ausführbar und schreibt/liest erwartbar unter `data/`
- ✅ Keine hardcodierten absoluten Pfade; nur `Path`/`Files` (NIO.2)
- ✅ Try-with-Resources für alle Streams/Reader/Writer
- ✅ UTF-8 Encoding für plattformunabhängige Textverarbeitung

---

**Viel Erfolg beim Lernen der Java NIO.2 API! 🚀**
