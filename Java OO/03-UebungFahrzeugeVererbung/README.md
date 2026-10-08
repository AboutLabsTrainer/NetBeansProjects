## 03-UebungFahrzeugeVererbung

Kleine NetBeans/Ant-Java-Anwendung zur Demonstration von Vererbung und Polymorphie anhand eines Fuhrparks.

### Inhalt
- `Fahrzeug`: Basisklasse mit `kennzeichen` und `baujahr`, `druckeDaten()`, `toString()`.
- `Pkw`: erweitert `Fahrzeug`, fügt `anzahlTueren` hinzu, überschreibt `druckeDaten()` und `toString()`.
- `Lkw`: erweitert `Fahrzeug`, fügt `maxLadungTonnen` hinzu, überschreibt `druckeDaten()` und `toString()`.
- `FahrzeugDemo`: `main`-Klasse, erzeugt Objekte, zeigt Polymorphie mit `Fahrzeug[]`, nutzt `instanceof` + Downcasting sowie `toString()`.

### Projekt öffnen
1. NetBeans: Datei → Öffnen → Projektordner `03-UebungFahrzeugeVererbung` wählen.
2. Stellen Sie sicher, dass als Main Class `de.ibb.uebung.fahrzeuge.FahrzeugDemo` gesetzt ist (Projekt → Properties → Run).

### Build & Run
- In NetBeans: `F6` zum Starten.
- Alternativ Ant (Konsole im Projekt-Root):
  - `ant clean`
  - `ant run`

### Erwartete Konsolenausgabe (gekürzt)
- Einzelne Objekte: Ausgabe von PKW und LKW mit spezifischen Attributen.
- Polymorphie: Iteration über `Fahrzeug[]`, dynamische `druckeDaten()`-Aufrufe, `instanceof`-Verarbeitung.
- `toString()`-Block aller Fahrzeuginstanzen.

### Lernziele
- `extends` und `super(...)` im Konstruktor.
- Methodenüberschreiben mit `@Override`.
- Polymorphie über Basistyp `Fahrzeug`.
- Typprüfung mit `instanceof` und Downcasting.

