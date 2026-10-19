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
        if (tage <= 0) {
            return 0.0;
        }
        return preisProTag * tage;
    }

    @Override
    public String getBeschreibung() {
        return "Auto " + kennzeichen + " [" + MietobjektArt.AUTO + "]";
    }
}
