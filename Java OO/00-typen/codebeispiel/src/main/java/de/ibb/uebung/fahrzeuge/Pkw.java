package de.ibb.uebung.fahrzeuge;

/**
 * Unterklasse für PKW.
 *
 * Thema:
 *  - Vererbung
 *  - super(...) im Konstruktor
 *  - Überschreiben von Methoden
 */
public class Pkw extends Fahrzeug {

    private int anzahlTueren;

    public Pkw(String kennzeichen, int baujahr, double mietpreisProTag, int anzahlTueren) {
        super(kennzeichen, baujahr, mietpreisProTag);
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
        super.druckeDaten();
        System.out.println("Anzahl Türen:    " + anzahlTueren);
    }

    @Override
    public String toString() {
        return "Pkw{" +
                "kennzeichen='" + getKennzeichen() + '\'' +
                ", baujahr=" + getBaujahr() +
                ", mietpreisProTag=" + getMietpreisProTag() +
                ", anzahlTueren=" + anzahlTueren +
                '}';
    }
}
