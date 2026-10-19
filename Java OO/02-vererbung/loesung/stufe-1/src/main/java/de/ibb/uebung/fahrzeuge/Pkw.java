package de.ibb.uebung.fahrzeuge;

public class Pkw extends Fahrzeug {

    private final int anzahlTueren;

    public Pkw(String kennzeichen, int baujahr, int anzahlTueren) {
        super(kennzeichen, baujahr);
        this.anzahlTueren = anzahlTueren;
    }

    public String beschreibung() {
        return "=== PKW ===\n" + basis() + "\nAnzahl Türen: " + anzahlTueren;
    }
}
