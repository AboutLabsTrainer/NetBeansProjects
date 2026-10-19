package de.ibb.uebung.fahrzeuge;

/**
 * Basisklasse für alle Fahrzeuge im Projekt
 * "03-UebungFahrzeugeVererbung".
 *
 * Zeigt:
 *  - gemeinsame Attribute (kennzeichen, baujahr)
 *  - Konstruktor
 *  - einfache Ausgabemethode
 */
public class Fahrzeug {

    private String kennzeichen;
    private int baujahr;

    public Fahrzeug(String kennzeichen, int baujahr) {
        this.kennzeichen = kennzeichen;
        this.baujahr = baujahr;
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

    /**
     * Gibt die Basisdaten des Fahrzeugs aus.
     * Diese Methode wird in den Unterklassen wiederverwendet.
     */
    public void druckeDaten() {
        System.out.println("Kennzeichen: " + kennzeichen);
        System.out.println("Baujahr: " + baujahr);
    }

    @Override
    public String toString() {
        return "Fahrzeug{" +
                "kennzeichen='" + kennzeichen + '\'' +
                ", baujahr=" + baujahr +
                '}';
    }
}