# Übung: Fuhrpark-Statistik mit statischen Klassenkomponenten

## Projektbeschreibung

Dieses Projekt demonstriert die Verwendung von **statischen Elementen** in Java-Klassen am Beispiel einer Fahrzeugverwaltung für die Autovermietung IBB-Rent.

## Lernziele

- Verständnis des Unterschieds zwischen Instanz- und Klassenvariablen
- Verwendung statischer Attribute (Zähler)
- Verwendung statischer Konstanten (`static final`)
- Verwendung statischer Methoden
- Praktische Anwendung: Tracking von Fahrzeuganzahl und Vermietungsstatus

## Projektstruktur

```
02-UebungFahrzeugeStatic/
├── src/
│   └── de/
│       └── ibb/
│           └── uebung/
│               └── fahrzeuge/
│                   ├── Fahrzeug.java      # Hauptklasse mit statischen Elementen
│                   └── FahrzeugDemo.java  # Demo-Klasse mit main-Methode
└── README.md
```

## Klasse: Fahrzeug

### Instanzattribute (jedes Objekt hat eigene Werte)

- `String kennzeichen` - Eindeutige Kennzeichnung des Fahrzeugs
- `int baujahr` - Baujahr des Fahrzeugs
- `double mietpreisProTag` - Tagesmietpreis in EUR (netto)
- `boolean vermietet` - Status, ob das Fahrzeug aktuell vermietet ist

### Statische Attribute (werden von allen Objekten geteilt)

- `private static int anzahlFahrzeuge = 0` - Zählt alle erzeugten Fahrzeuge
- `private static int anzahlVermietet = 0` - Zählt aktuell vermietete Fahrzeuge

### Statische Konstanten

- `public static final int MAX_FAHRZEUGE = 1000` - Maximale Anzahl erlaubter Fahrzeuge
- `public static final double MWST = 0.19` - Mehrwertsteuersatz (19%)

### Wichtige Methoden

#### Instanzmethoden

- `druckeDaten()` - Gibt Fahrzeugdaten aus (Kennzeichen, Baujahr, Preis, Vermietungsstatus)
- `berechneMietpreisNetto(int tage)` - Berechnet Nettomietpreis für gegebene Tage
- `berechneMietpreisBrutto(int tage)` - Berechnet Bruttomietpreis (mit MWSt) für dieses Fahrzeug
- `miete()` - Vermietet das Fahrzeug (setzt `vermietet = true`, erhöht Zähler)
- `rueckgabe()` - Gibt das Fahrzeug zurück (setzt `vermietet = false`, verringert Zähler)

#### Statische Methoden

- `getAnzahlFahrzeuge()` - Liefert die Gesamtanzahl aller erzeugten Fahrzeuge
- `getAnzahlVermietet()` - Liefert die Anzahl aktuell vermieteter Fahrzeuge
- `berechneMietpreisBrutto(double preisProTag, int tage)` - Berechnet Bruttopreis ohne konkretes Objekt
- `druckeStatistik()` - Gibt eine Statistik über alle Fahrzeuge aus

## Unterschied: Statisch vs. Nicht-Statisch

### Statische Elemente (Klassenvariablen)

- **Zugehörigkeit**: Gehören zur **Klasse**, nicht zu einzelnen Objekten
- **Zugriff**: Über **Klassennamen** (z.B. `Fahrzeug.getAnzahlFahrzeuge()`)
- **Speicher**: Existieren **einmal** für die gesamte Klasse
- **Verwendung**: Für Werte, die für alle Objekte gleich sind oder gemeinsam verwaltet werden müssen

**Beispiel:**
```java
// Statischer Zähler - wird von allen Objekten geteilt
Fahrzeug f1 = new Fahrzeug("B-AB 1234", 2015, 49.99);
Fahrzeug f2 = new Fahrzeug("B-CD 4321", 2020, 59.99);

System.out.println(Fahrzeug.getAnzahlFahrzeuge()); // Ausgabe: 2
// Beide Objekte teilen sich den gleichen Zähler!
```

### Instanzvariablen

- **Zugehörigkeit**: Gehören zu **jedem einzelnen Objekt**
- **Zugriff**: Über **Objektreferenz** (z.B. `f1.getKennzeichen()`)
- **Speicher**: Jedes Objekt hat **eigene Kopie**
- **Verwendung**: Für Werte, die für jedes Objekt unterschiedlich sind

**Beispiel:**
```java
Fahrzeug f1 = new Fahrzeug("B-AB 1234", 2015, 49.99);
Fahrzeug f2 = new Fahrzeug("B-CD 4321", 2020, 59.99);

System.out.println(f1.getKennzeichen()); // Ausgabe: "B-AB 1234"
System.out.println(f2.getKennzeichen()); // Ausgabe: "B-CD 4321"
// Jedes Objekt hat sein eigenes Kennzeichen!
```

## Verwendung

### Kompilieren und Ausführen

Das Projekt kann in NetBeans oder über die Kommandozeile kompiliert und ausgeführt werden:

```bash
# Kompilieren
javac -d build/classes src/de/ibb/uebung/fahrzeuge/*.java

# Ausführen
java -cp build/classes de.ibb.uebung.fahrzeuge.FahrzeugDemo
```

### Beispiel-Output

```
=== Start: Noch keine Fahrzeuge erzeugt ===
Anzahl Fahrzeuge (statisch): 0

=== Nach Erzeugung von f1 und f2 ===
Anzahl Fahrzeuge (statisch): 2
Maximal erlaubte Fahrzeuge:  1000

=== Einzelfahrzeuge (Instanzdaten) ===
Fahrzeug B-AB 1234
  Baujahr:        2015
  Mietpreis/Tag:  49.99 EUR netto
  Vermietet:      nein

Fahrzeug B-CD 4321
  Baujahr:        2020
  Mietpreis/Tag:  59.99 EUR netto
  Vermietet:      nein

=== Mietpreis über Instanzmethoden ===
Bruttomietpreis für B-AB 1234 (3 Tage): 178,57 EUR
Bruttomietpreis für B-CD 4321 (3 Tage): 214,17 EUR

=== Mietpreis über statische Methode ===
Bruttomietpreis (ohne konkretes Objekt, 39.99 EUR/Tag, 5 Tage): 237,95 EUR

=== Statische Statistik ===
=== Fahrzeug-Statistik ===
Anzahl Fahrzeuge gesamt: 2
Anzahl vermietet:        0
Maximal erlaubt:         1000
MWSt-Satz (Konstante):   19.0 %

=== Teil 3: Vermietungsstatus-Tracking ===
Anzahl vermietet (vor Vermietungen): 0

Vermiete f1...
Anzahl vermietet: 1

Vermiete f2...
Anzahl vermietet: 2

Gebe f1 zurück...
Anzahl vermietet: 1

=== Finale Statistik ===
=== Fahrzeug-Statistik ===
Anzahl Fahrzeuge gesamt: 2
Anzahl vermietet:        1
Maximal erlaubt:         1000
MWSt-Satz (Konstante):   19.0 %
```

## Übungsaufgaben (Teil 1-3)

### Teil 1: Statische Klassenvariable (Zähler)
- ✅ Statisches Attribut `anzahlFahrzeuge` implementiert
- ✅ Zähler wird im Konstruktor erhöht
- ✅ Statische Getter-Methode `getAnzahlFahrzeuge()`

### Teil 2: Statische Konstante & statische Methode
- ✅ Statische Konstante `MWST = 0.19` für Mehrwertsteuersatz
- ✅ Statische Methode `berechneMietpreisBrutto()` für Preisberechnung ohne Objekt

### Teil 3: Bonus - Statischer Zähler "vermietet"
- ✅ Statisches Attribut `anzahlVermietet` implementiert
- ✅ Instanzmethoden `miete()` und `rueckgabe()` implementiert
- ✅ Statische Getter-Methode `getAnzahlVermietet()`

## Warum statische Variablen für Zähler?

**Frage:** Warum ist `anzahlVermietet` sinnvoll als statische Variable und nicht als Instanzvariable?

**Antwort:**
- Eine **Instanzvariable** würde in jedem Objekt gespeichert werden, aber jedes Fahrzeug "weiß" nur, ob es selbst vermietet ist
- Um die **Gesamtzahl** aller vermieteten Fahrzeuge zu kennen, müsste man alle Objekte durchlaufen und zählen
- Eine **statische Variable** speichert die Gesamtzahl zentral und wird automatisch aktualisiert, wenn ein Fahrzeug vermietet oder zurückgegeben wird
- Der Zugriff erfolgt direkt über die Klasse: `Fahrzeug.getAnzahlVermietet()` - ohne alle Objekte durchsuchen zu müssen

## Technische Details

- **Java Version**: Kompatibel mit Java 8+
- **Package**: `de.ibb.uebung.fahrzeuge`
- **Main-Klasse**: `FahrzeugDemo`

## Autor

IBB-Rent - Übungsprojekt für statische Klassenkomponenten in Java

