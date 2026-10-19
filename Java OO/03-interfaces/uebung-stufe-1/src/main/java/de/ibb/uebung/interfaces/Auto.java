package de.ibb.uebung.interfaces;

public class Auto implements Mietbar {

    private final String kennzeichen;
    private final double preisProTag;

    public Auto(String kennzeichen, double preisProTag) {
        this.kennzeichen = kennzeichen;
        this.preisProTag = preisProTag;
    }

    @Override
    public double berechneMietpreis(int tage) {
        // TODO: bei tage <= 0 den Wert 0, sonst preisProTag * tage
        return 0.0;
    }

    @Override
    public String getBeschreibung() {
        return "Auto " + kennzeichen + " [" + MietobjektArt.AUTO + "]";
    }
}
