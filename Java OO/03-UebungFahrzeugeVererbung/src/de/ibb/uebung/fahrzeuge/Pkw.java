package de.ibb.uebung.fahrzeuge;

/**
 * Unterklasse für PKW-Fahrzeuge.
 *
 * Zeigt:
 *  - Vererbung mit extends Fahrzeug
 *  - zusätzlichen Attributwert (anzahlTueren)
 *  - super(...) im Konstruktor
 *  - Überschreiben von druckeDaten()
 */
public class Pkw extends Fahrzeug {

    private int anzahlTueren;

    public Pkw(String kennzeichen, int baujahr, int anzahlTueren) {
        // Basisteil initialisieren
        super(kennzeichen, baujahr);
        // PKW-spezifisches Attribut
        this.anzahlTueren = anzahlTueren;
    }

    public int getAnzahlTueren() {
        return anzahlTueren;
    }

    public void setAnzahlTueren(int anzahlTueren) {
        this.anzahlTueren = anzahlTueren;
    }

    @Override
    public void druckeDaten() {
        System.out.println("=== PKW ===");
        // Basisdaten ausgeben
        super.druckeDaten();
        // zusätzliche PKW-Daten
        System.out.println("Anzahl Türen: " + anzahlTueren);
    }

    @Override
    public String toString() {
        return "Pkw{" +
                "kennzeichen='" + getKennzeichen() + '\'' +
                ", baujahr=" + getBaujahr() +
                ", anzahlTueren=" + anzahlTueren +
                '}';
    }
}