package de.ibb.uebung.interfaces;

public class Bohrmaschine implements Mietbar {

    private final String modell;
    private final int leistungWatt;

    public Bohrmaschine(String modell, int leistungWatt) {
        this.modell = modell;
        this.leistungWatt = leistungWatt;
    }

    @Override
    public double berechneMietpreis(int tage) {
        if (tage <= 0) {
            return 0.0;
        }
        double preisProTag = 20.0;
        if (leistungWatt > 1000) {
            preisProTag += 5.0;
        }
        return preisProTag * tage;
    }

    @Override
    public String getBeschreibung() {
        return "Bohrmaschine " + modell + " (" + leistungWatt + " W)";
    }
}
