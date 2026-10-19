package de.ibb.uebung.fahrzeuge;

/**
 * Basisklasse für alle Fahrzeuge.
 *
 * Themen:
 *  - Eigenschaften (Attribute)
 *  - Konstruktor
 *  - Methoden
 */
public class Fahrzeug {

    private String kennzeichen;
    private int baujahr;
    private double mietpreisProTag;

    public Fahrzeug(String kennzeichen, int baujahr, double mietpreisProTag) {
        this.kennzeichen = kennzeichen;
        this.baujahr = baujahr;
        this.mietpreisProTag = mietpreisProTag;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public double getMietpreisProTag() {
        return mietpreisProTag;
    }

    public void setMietpreisProTag(double mietpreisProTag) {
        this.mietpreisProTag = mietpreisProTag;
    }

    /**
     * Gibt die Basisdaten des Fahrzeugs aus.
     */
    public void druckeDaten() {
        System.out.println("Kennzeichen:     " + kennzeichen);
        System.out.println("Baujahr:         " + baujahr);
        System.out.println("Mietpreis/Tag:   " + mietpreisProTag + " EUR");
    }

    @Override
    public String toString() {
        return "Fahrzeug{" +
                "kennzeichen='" + kennzeichen + '\'' +
                ", baujahr=" + baujahr +
                ", mietpreisProTag=" + mietpreisProTag +
                '}';
    }
}
