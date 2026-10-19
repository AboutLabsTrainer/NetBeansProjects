package de.ibb.uebung.fahrzeuge;

public class Fahrzeug {

    private final String kennzeichen;
    private final int baujahr;

    public Fahrzeug(String kennzeichen, int baujahr) {
        this.kennzeichen = kennzeichen;
        this.baujahr = baujahr;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public String basis() {
        return "Kennzeichen: " + kennzeichen + "\nBaujahr: " + baujahr;
    }
}
