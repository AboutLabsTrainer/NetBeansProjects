# 04-Übung - Mietobjekte mit Schnittstellen

## Projektbeschreibung

Dieses Projekt demonstriert die Verwendung von **Interfaces (Schnittstellen)** und **Enumerationen (Enums)** in Java am Beispiel einer Mietobjekt-Verwaltung für die Verleihfirma IBB-Rent.

Die Firma vermietet verschiedene Objekte:
- 🚗 **Autos**
- 🚲 **Fahrräder** (normal und E-Bikes)
- 🔧 **Bohrmaschinen**

Alle Mietobjekte können:
- auf dieselbe Art verwaltet werden (polymorphe Nutzung)
- Mietpreise berechnen (optional inkl. Mehrwertsteuer)
- bei Bedarf eine Versicherungsprämie liefern
- saisonabhängige Zuschläge und Rabatte berücksichtigen

## Lernziele

Nach der Bearbeitung können Sie:
- ✅ Schnittstellen in Java definieren und implementieren
- ✅ mehrere Klassen über ein gemeinsames Interface polymorph nutzen
- ✅ ein zweites Interface zusätzlich implementieren (Mehrfach-Implementierung)
- ✅ in Interfaces Konstanten und `default`-Methoden verwenden
- ✅ Enumerationen (`enum`) mit Konstanten, Feldern, Konstruktoren und Methoden definieren
- ✅ Enums in Klassen als Typ verwenden und sie Interfaces implementieren lassen

## Projektstruktur

```
src/de/ibb/uebung/interfaces/
├── Mietbar.java              # Haupt-Interface für alle Mietobjekte
├── Versicherbar.java         # Interface für versicherbare Objekte
├── RabattRegel.java          # Interface für Rabattberechnung
├── MietobjektArt.java        # Enum: Art des Mietobjekts
├── Saison.java               # Enum: Saison mit Faktor und Rabattlogik
├── Auto.java                 # Klasse: Auto als Mietobjekt
├── Fahrrad.java              # Klasse: Fahrrad als Mietobjekt
├── Bohrmaschine.java         # Klasse: Bohrmaschine als Mietobjekt
└── MietDemo.java             # Demo- und Testklasse
```

## Komponenten im Detail

### Interfaces

#### `Mietbar`
Das Haupt-Interface für alle Mietobjekte.

**Konstante:**
- `BASIS_MWST = 0.19` - Basis-Mehrwertsteuersatz (19%)

**Methoden:**
- `double berechneMietpreis(int tage)` - Berechnet den Nettomietpreis
- `String getBeschreibung()` - Liefert eine Beschreibung des Objekts
- `default double berechneMietpreisMitMwst(int tage)` - Berechnet den Bruttopreis mit MWSt (Default-Methode)

#### `Versicherbar`
Interface für Objekte, die eine Versicherungsprämie berechnen können.

**Methoden:**
- `double berechneVersicherungsPraemie()` - Berechnet die Versicherungsprämie in EUR

#### `RabattRegel`
Beschreibt eine Regel zur Berechnung eines prozentualen Rabatts in Abhängigkeit von der Mietdauer.

**Methoden:**
- `double berechneRabattProzent(int tage)` - Berechnet den Rabatt in Prozent

### Enumerationen

#### `MietobjektArt`
Art des Mietobjekts.

**Werte:**
- `AUTO`
- `FAHRRAD`
- `BOHRMASCHINE`

#### `Saison`
Saison mit Faktor und Rabattlogik. Implementiert das Interface `RabattRegel`.

**Werte:**
- `NEBENSAISON(0.8)` - Faktor 0.8 (20% günstiger)
- `STANDARD(1.0)` - Faktor 1.0 (Normalpreis)
- `HAUPTSAISON(1.2)` - Faktor 1.2 (20% teurer)

**Methoden:**
- `double getFaktor()` - Liefert den Saisonfaktor
- `double berechneRabattProzent(int tage)` - Berechnet Rabatt basierend auf Saison und Tagen
  - **NEBENSAISON**: ab 5 Tagen 10% Rabatt
  - **STANDARD**: ab 7 Tagen 5% Rabatt
  - **HAUPTSAISON**: kein Rabatt

### Klassen

#### `Auto`
Repräsentiert ein Auto als Mietobjekt.

**Attribute:**
- `String kennzeichen` - Kennzeichen des Autos
- `double preisProTag` - Mietpreis pro Tag
- `MietobjektArt art` - Art des Objekts (immer `AUTO`)

**Implementierte Interfaces:**
- `Mietbar`
- `Versicherbar` (Prämie: 10% des Tagespreises)

**Mietpreis:** `preisProTag * tage`

**Beispiel:**
```java
Auto auto = new Auto("B-AB 1234", 59.99);
// Mietpreis für 3 Tage: 179.97 EUR
```

#### `Fahrrad`
Repräsentiert ein Fahrrad (normal oder E-Bike).

**Attribute:**
- `String rahmennummer` - Rahmennummer des Fahrrads
- `boolean eBike` - `true` wenn E-Bike, `false` für normales Fahrrad
- `MietobjektArt art` - Art des Objekts (immer `FAHRRAD`)

**Implementierte Interfaces:**
- `Mietbar`

**Mietpreis:**
- Normales Fahrrad: 15€/Tag
- E-Bike: 25€/Tag

**Beispiel:**
```java
Fahrrad fahrrad = new Fahrrad("R-001", false);  // normales Fahrrad
Fahrrad eBike = new Fahrrad("E-007", true);     // E-Bike
```

#### `Bohrmaschine`
Repräsentiert eine Bohrmaschine als Mietobjekt.

**Attribute:**
- `String modell` - Modellbezeichnung
- `int leistungWatt` - Leistung in Watt
- `MietobjektArt art` - Art des Objekts (immer `BOHRMASCHINE`)

**Implementierte Interfaces:**
- `Mietbar`
- `Versicherbar` (Prämie: pauschal 5€)

**Mietpreis:**
- Basis: 20€/Tag
- Bei Leistung > 1000W: +5€/Tag Aufschlag

**Beispiel:**
```java
Bohrmaschine bohrer = new Bohrmaschine("ProDrill 500", 1200);
// Mietpreis für 3 Tage: 75.00 EUR (25€/Tag wegen hoher Leistung)
```

## Verwendung

### Polymorphe Nutzung

Alle Mietobjekte können über das `Mietbar`-Interface polymorph verwendet werden:

```java
Mietbar[] mietobjekte = {
    new Auto("B-AB 1234", 59.99),
    new Fahrrad("R-001", false),
    new Fahrrad("E-007", true),
    new Bohrmaschine("ProDrill 500", 1200)
};

for (Mietbar m : mietobjekte) {
    System.out.println(m.getBeschreibung());
    System.out.println("Preis (3 Tage): " + m.berechneMietpreis(3) + " EUR");
}
```

### Versicherung

Nur `Auto` und `Bohrmaschine` implementieren `Versicherbar`:

```java
Auto auto = new Auto("B-AB 1234", 59.99);
Bohrmaschine bohrer = new Bohrmaschine("ProDrill 500", 1200);

System.out.println("Versicherung Auto: " + 
    auto.berechneVersicherungsPraemie() + " EUR");
System.out.println("Versicherung Bohrer: " + 
    bohrer.berechneVersicherungsPraemie() + " EUR");
```

### Saison und Rabatt

Die `Saison`-Enumeration kann für saisonabhängige Preise und Rabatte verwendet werden:

```java
Saison saison = Saison.HAUPTSAISON;
int tage = 7;

double netto = auto.berechneMietpreis(tage);
double faktor = saison.getFaktor();
double rabattProzent = saison.berechneRabattProzent(tage);

double nettoMitSaison = netto * faktor;
double rabattBetrag = nettoMitSaison * (rabattProzent / 100.0);
double endpreis = nettoMitSaison - rabattBetrag;
```

## Ausführung

Die Hauptklasse `MietDemo` enthält eine vollständige Demonstration aller Funktionen:

1. **Mietobjekte (Netto & Brutto)**: Zeigt alle Mietobjekte mit Netto- und Bruttopreisen
2. **Versicherungskosten**: Demonstriert die Versicherungsprämien
3. **Saison & Rabatt-Demo**: Zeigt die Berechnung mit Saisonfaktor und Rabatt

### Programm starten

1. Öffnen Sie das Projekt in NetBeans
2. Stellen Sie sicher, dass `MietDemo` als Main Class gesetzt ist:
   - Rechtsklick auf Projekt → Properties → Run → Main Class: `de.ibb.uebung.interfaces.MietDemo`
3. Führen Sie das Projekt aus (F6)

### Erwartete Ausgabe

```
=== Mietobjekte (Netto & Brutto) ===
Auto B-AB 1234 [AUTO]
  Mietdauer: 3 Tage
  Preis netto:  179.97 EUR
  Preis brutto: 214.16 EUR

Fahrrad R-001 [FAHRRAD]
  Mietdauer: 3 Tage
  Preis netto:  45.00 EUR
  Preis brutto: 53.55 EUR

E-Bike E-007 [FAHRRAD]
  Mietdauer: 3 Tage
  Preis netto:  75.00 EUR
  Preis brutto: 89.25 EUR

Bohrmaschine ProDrill 500 (1200 W) [BOHRMASCHINE]
  Mietdauer: 3 Tage
  Preis netto:  75.00 EUR
  Preis brutto: 89.25 EUR

=== Versicherungskosten ===
Versicherungsprämie für Auto B-AB 1234 [AUTO]: 6.00 EUR
Versicherungsprämie für Bohrmaschine ProDrill 500 (1200 W) [BOHRMASCHINE]: 5.00 EUR

=== Saison & Rabatt-Demo ===
Saison: HAUPTSAISON
Auto: Auto B-AB 1234 [AUTO]
Tage: 7
Netto ohne Saison:      419.93 EUR
Saisonfaktor:           1.20
Netto mit Saison:       503.92 EUR
Rabatt:                 0.00 % (0.00 EUR)
Endpreis (netto):       503.92 EUR
```

## Wichtige Konzepte

### Polymorphie über Interfaces

Alle Mietobjekte können über das gemeinsame Interface `Mietbar` behandelt werden, obwohl sie unterschiedliche Klassen sind. Dies ermöglicht eine einheitliche Verarbeitung in Arrays oder Collections.

### Mehrfach-Implementierung

Klassen können mehrere Interfaces implementieren:
- `Auto` implementiert `Mietbar` und `Versicherbar`
- `Bohrmaschine` implementiert `Mietbar` und `Versicherbar`
- `Fahrrad` implementiert nur `Mietbar`

### Default-Methoden in Interfaces

Seit Java 8 können Interfaces `default`-Methoden enthalten, die bereits implementiert sind. Die Methode `berechneMietpreisMitMwst()` in `Mietbar` ist eine solche Default-Methode und muss nicht in jeder implementierenden Klasse neu geschrieben werden.

### Enumerationen mit Funktionalität

Enums können nicht nur Konstanten sein, sondern auch:
- Felder (z.B. `faktor` in `Saison`)
- Konstruktoren (z.B. `Saison(double faktor)`)
- Methoden (z.B. `getFaktor()`, `berechneRabattProzent()`)
- Interfaces implementieren (z.B. `Saison implements RabattRegel`)

## Technische Details

- **Java Version**: Kompatibel mit Java 8+
- **Package**: `de.ibb.uebung.interfaces`
- **Projekttyp**: Java Application (NetBeans)

## Autor

IBB-Rent Mietobjekte Übung - Lernprojekt für Interfaces und Enumerationen

