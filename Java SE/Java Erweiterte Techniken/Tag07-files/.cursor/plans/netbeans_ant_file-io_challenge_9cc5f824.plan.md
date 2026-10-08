---
name: NetBeans Ant File-IO Challenge
overview: "Ein lauffähiges NetBeans-Ant-Java-Projekt (Java 21), das die Mini‑Challenge aus „Tag 7: Dateien lesen, schreiben und verwalten“ vollständig (Level 1–3) umsetzt und per Console-Menü demonstrierbar ist."
todos:
  - id: scaffold-ant
    content: NetBeans Ant Projektgerüst anlegen (build.xml, nbproject/*, src/ Struktur) und Java 21 konfigurieren.
    status: completed
  - id: seed-data
    content: data/ Struktur und Beispiel-Dateien (text/properties/csv/copy/java-scan) so anlegen, dass alle Aufgaben sofort lauffähig sind.
    status: completed
  - id: implement-level1
    content: Level 1 Aufgaben implementieren (Zeilen zählen, Liste schreiben, exists+readable prüfen) mit NIO.2.
    status: completed
  - id: implement-level2
    content: Level 2 Aufgaben implementieren (db.* aus Properties, rekursiv .java finden, .txt kopieren A->B).
    status: completed
  - id: implement-level3
    content: Level 3 implementieren (ConfigManager Defaults, CSV->Objekte, Backup mit Timestamp).
    status: completed
  - id: wire-cli
    content: Main-Menü/Args bauen, Aufgaben aufrufbar machen und verständliche Konsolen-Ausgaben/Fehlermeldungen ergänzen.
    status: completed
  - id: docs
    content: README.md für NetBeans-Import/Run + kurze Beschreibung der Mini-Challenge und Datenpfade erstellen.
    status: completed
---

# NetBeans Ant Projekt: Tag 7 Mini‑Challenge (Level 1–3)

### Ziel

Ein **NetBeans-kompatibles Ant-Projekt (Java 21)** im Workspace `07-file-io`, das die Mini‑Challenge aus dem Artikel `[TAG 7: Dateien lesen, schreiben und verwalten]` (`https://www.java-developer.online/tag-7-dateien-lesen-schreiben/`) **vollständig** umsetzt:

- **Level 1**: Textdatei lesen & Zeilen zählen; Liste von Strings schreiben; Datei existiert & lesbar prüfen
- **Level 2**: Properties laden & `db.*` ausgeben; `.java` Dateien rekursiv finden; `.txt` Dateien von A nach B kopieren
- **Level 3**: Config-Manager mit Default-Werten; CSV in Objekte parsen; Datei-Backup mit Timestamp

### Projektstruktur (NetBeans Ant)

- Erzeuge ein klassisches NetBeans-Ant-Java-Projektlayout mit:
- `build.xml` (Ant Build)
- `nbproject/` (NetBeans Metadaten, inkl. `project.properties`, `project.xml`)
- `src/` (Java Quellcode)
- `data/` (Beispieldateien zur Challenge: Text, Properties, CSV, Input/Output Ordner)
- `README.md` (Start/Run-Anleitung in NetBeans + Konsole)

### Implementierungs-Design

- **CLI Entry Point**: `src/.../Main.java`
- Menü/Argumente zum Ausführen einzelner Aufgaben (`level1`, `level2`, `level3`, oder `all`).
- Arbeitet mit relativen Pfaden unter `data/` (Windows/macOS/Linux kompatibel via `Path.of(...)`).
- Erstellt fehlende Beispiel-Dateien/Ordner beim ersten Lauf (idempotent), damit das Projekt sofort lauffähig ist.

- **Aufgaben-Module** (saubere Trennung, jeweils mit klaren Methoden):
- `tasks/Level1Tasks.java`
- `tasks/Level2Tasks.java`
- `tasks/Level3Tasks.java`

- **Level 3 Config-Manager**
- `config/ConfigManager.java`
- Lädt `data/config/app.properties` (oder erstellt sie mit Defaults, falls nicht vorhanden).
- API: `getString(key, default)`, `getInt(...)`, `getBoolean(...)`, plus `getDbEntries()` für `db.*`.

- **CSV Parsing**
- `csv/Person.java` (oder ähnlich; wird aus CSV-Spalten gemappt)
- `csv/CsvParser.java` (liest `data/csv/daten.csv`, überspringt Header, splittet per `;` wie im Artikelbeispiel)

- **Backup mit Timestamp**
- `backup/BackupService.java`
- Erstellt Kopie einer Datei nach `data/backup/<originalname>_<yyyyMMdd_HHmmss>.<ext>`.

### Dateien/Assets unter `data/`

- `data/text/input.txt` (für Zeilenzählen)
- `data/text/output.txt` (für „Liste schreiben“)
- `data/config/app.properties` (mit `db.host`, `db.port`, ...)
- `data/java-scan/` (enthält ein paar Dummy-`.java` Dateien/Unterordner zum rekursiven Finden)
- `data/copy-src/` und `data/copy-dst/` (für `.txt`-Kopie)
- `data/csv/daten.csv` (Header + Beispielzeilen)

### NetBeans-Lauffähigkeit

- Setze `Main-Class` in `nbproject/project.properties` so, dass **Run in NetBeans** direkt das Menü startet.
- Stelle `source/target=21` ein.

### Akzeptanzkriterien (Definition of Done)

- Projekt lässt sich in NetBeans **als Ant-Projekt öffnen** und mit **Run** starten.
- Jede Challenge-Aufgabe ist ausführbar und schreibt/liest erwartbar unter `data/`.
- Keine hardcodierten absoluten Pfade; nur `Path`/`Files` (NIO.2) und Try-with-Resources.