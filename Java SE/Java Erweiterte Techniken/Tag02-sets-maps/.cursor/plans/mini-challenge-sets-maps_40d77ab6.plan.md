---
name: mini-challenge-sets-maps
overview: "Umsetzung der kompletten Mini-Challenge (Quiz + Level 1–3 Aufgaben) aus \"Tag 2: Sets & Maps – Keine Duplikate\" als Java-Konsolenprogramm im vorhandenen NetBeans-Projekt."
todos:
  - id: setup-main-structure
    content: Neue Packages/Klassen (Main, Student, ggf. Person, LruCache, Level1/2/3-Hilfsklassen) im bestehenden NetBeans-Projekt anlegen.
    status: completed
  - id: implement-quiz
    content: Quiz-Fragen 1–5 mit Interaktion und Lösungen als Konsolendialog nachbauen.
    status: completed
    dependencies:
      - setup-main-structure
  - id: implement-level1
    content: Level 1 – Einsteiger-Aufgaben (HashSet mit Städten, HashMap Länder→Hauptstadt) als Methoden implementieren und ins Menü einbinden.
    status: completed
    dependencies:
      - setup-main-structure
  - id: implement-level2
    content: Level 2 – Fortgeschritten-Aufgaben (Student-Klasse mit equals/hashCode, Worthäufigkeit mit HashMap) implementieren.
    status: completed
    dependencies:
      - setup-main-structure
  - id: implement-level3
    content: Level 3 – Profi-Aufgaben (LRU-Cache mit LinkedHashMap, TreeSet mit Comparator für Personen) implementieren.
    status: completed
    dependencies:
      - setup-main-structure
  - id: wire-menu-and-test
    content: Hauptmenü und Navigation zwischen Quiz/Levels verdrahten, Programm kompilieren und manuell mit Beispiel-Durchläufen testen.
    status: completed
    dependencies:
      - implement-quiz
      - implement-level1
      - implement-level2
      - implement-level3
---

## Plan: Mini-Challenge zu Sets & Maps nachbauen

### Ziel

**Ein Konsolenprogramm `Main` im vorhandenen NetBeans-Projekt erstellen, das alle Teile der Mini-Challenge (Quiz-Fragen + Level 1–3 Aufgaben) aus dem Artikel „Tag 2: Sets & Maps – Keine Duplikate“ funktional nachbildet.**

### Struktur & Dateien

- **Neue Klasse `Main`** in einem passenden Package (z.B. `de.ibb.setsmapschallenge`):
- Enthält `public static void main(String[] args)`.
- Steuert das Menü und ruft die einzelnen Challenge-Teile auf.
- **Neue Klasse `Student`**:
- Eigenschaften: z.B. `String matrikelnummer`, `String name` (und optional `int alter`).
- Implementiert **korrekt** `equals()` und `hashCode()` (mit `Objects.equals`/`Objects.hash`).
- Optional: `toString()` zur schönen Konsolenausgabe.
- **Hilfsklassen nach Level (optional, aber übersichtlich)**:
- `Level1Einsteiger` mit statischen Methoden für die Einsteigeraufgaben.
- `Level2Fortgeschritten` für die fortgeschrittenen Aufgaben.
- `Level3Profi` für die Profi-Aufgaben.
- Alternativ: alles als statische Methoden in `Main` (falls du es kompakter bevorzugst).

### Funktionales Verhalten (exakte Mini-Challenge-Abdeckung)

- **Hauptmenü in `main`** (Konsolenausgabe über `System.out.println`):
- Menüeinträge:
- `1) Quiz-Fragen bearbeiten`
- `2) Level 1 – Einsteiger`
- `3) Level 2 – Fortgeschritten`
- `4) Level 3 – Profi`
- `0) Beenden`
- Einlesen der Auswahl mit `Scanner`.

- **Quiz-Teil (Fragen 1–5)**:
- Jede Quiz-Frage aus dem Artikel wird **als Text ausgegeben**, inkl. des zugehörigen Codesnippets (in die Konsole übernommen).
- Für jede Frage wird der/die Nutzer:in nach der vermuteten Antwort gefragt (z.B. per freie Eingabe oder Multiple-Choice-Hinweis).
- Nach der Eingabe wird die **korrekte Lösung** aus dem Artikel ausgegeben (inkl. kurzer Erklärung, analog zu den „Quiz-Lösungen“).
- Reihenfolge und Inhalte der 5 Quiz-Fragen orientieren sich an:
- Größe des `Set<Person>` ohne `equals/hashCode`.
- Welches Set die Sortierung behält.
- `Map<List<String>, String>` mit verändertem Key.
- Zeitkomplexität von `get()` bei `HashMap` vs. `TreeMap`.
- Beziehung zwischen `equals` und `hashCode`.

- **Level 1 – Einsteiger (2 Aufgaben)**
- **Aufgabe 1:** HashSet mit Städten
- Methode `runLevel1Aufgabe1()`:
- Erzeugt `HashSet<String> staedte` mit mindestens 5 Städtenamen (inkl. "Berlin").
- Gibt das Set und `staedte.contains("Berlin")` aus.
- **Aufgabe 2:** HashMap Länder → Hauptstädte
- Methode `runLevel1Aufgabe2()`:
- Erzeugt `HashMap<String, String> laenderZuHauptstadt` mit diversen Einträgen (z.B. "Deutschland" → "Berlin", ...).
- Gibt alle Einträge lesbar aus (Iteration über `entrySet`).
- Ein Level-1-Menü, das beide Aufgaben nacheinander ausführt oder einzeln anwählbar macht.

- **Level 2 – Fortgeschritten (2 Aufgaben)**
- **Aufgabe 1:** `Student` mit korrekt implementiertem `equals/hashCode`
- Klasse `Student` mit Feldern, Konstruktor, Gettern und overrides von `equals`/`hashCode`.
- Testmethode `runLevel2Aufgabe1()`:
- Erzeugt ein `Set<Student>` (z.B. `HashSet<Student>`), fügt mehrere `Student`-Objekte hinzu, darunter Duplikate bzgl. Matrikelnummer/Name.
- Demonstriert, dass Duplikate im Set **nicht mehrfach** vorkommen (Set-Größe & Ausgabe).
- **Aufgabe 2:** Worthäufigkeit in einem Text zählen
- Methode `runLevel2Aufgabe2()`:
- Nimmt einen festen Beispieltext (oder Konsoleneingabe), zerlegt ihn in Wörter.
- Verwendet `HashMap<String, Integer>` mit `merge` oder `computeIfAbsent`, um Häufigkeiten zu zählen.
- Gibt die Map-Ergebnisse formatiert aus (z.B. `Wort -> Anzahl`).

- **Level 3 – Profi (2 Aufgaben)**
- **Aufgabe 1:** LRU-Cache mit `LinkedHashMap`
- Klasse `LruCache<K,V>`:
- Erweitert `LinkedHashMap<K,V>` und überschreibt `removeEldestEntry`, um eine maximale Kapazität umzusetzen.
- Konstruktor mit `maxCapacity`.
- Testmethode `runLevel3Aufgabe1()`:
- Erzeugt einen `LruCache<String, String>` mit kleiner Kapazität (z.B. 3), fügt mehrere Einträge hinzu und zeigt per Konsolenausgabe, wann alte Einträge entfernt werden.
- **Aufgabe 2:** `TreeSet` mit `Comparator` für Personen
- Wiederverwendung der Klasse `Person` oder `Student` (oder eigene `Person`-Klasse):
- Entweder `implements Comparable<Person>` oder externer `Comparator<Person>`.
- Sortierung: erst nach Alter, dann nach Name ("erst Alter, dann Name" wie im Artikel).
- Testmethode `runLevel3Aufgabe2()`:
- Erstellt ein `TreeSet<Person>` mit passendem `Comparator`.
- Fügt mehrere Personen mit unterschiedlichem Alter und Namen hinzu.
- Gibt die sortierte Reihenfolge in der Konsole aus.

### Konsolen-UX & Dokumentation

- **Konsolen-Ausgaben** klar strukturieren:
- Überschriften je Bereich: z.B. `==== QUIZ ====`, `==== LEVEL 1 – EINSTEIGER ====`, etc.
- Zwischen Überschriften und Ergebnissen Leerzeilen für Lesbarkeit.
- **Kommentare im Code**:
- Kurz erklären, wo die Beispiele direkt aus dem Artikel übernommen sind (z.B. `// basiert auf Quiz-Frage 3 aus Tag 2: Sets & Maps`).
- Bei `equals/hashCode` und `LruCache` kurz den Zweck erklären.

### Anpassung an dein Projekt

- Integration in dein bestehendes NetBeans-Projekt unter dem vorhandenen Kurs-Ordner:
- Neues Package und Klassen wie oben beschrieben anlegen.
- Sicherstellen, dass sich das Projekt mit `Main.main()` starten lässt (Run Configuration in NetBeans auf `Main` setzen).

Sobald du diesen Plan bestätigst, implementiere ich die beschriebenen Klassen (`Main`, `Student`, optional `Person`, `LruCache` sowie Level-Hilfsklassen), baue das Menü und alle Aufgaben, und halte mich so nah wie möglich an Inhalt und Intention der Mini-Challenge.