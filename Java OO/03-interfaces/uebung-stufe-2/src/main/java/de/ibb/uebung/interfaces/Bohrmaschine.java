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
        return 0.0;
    }

    @Override
    public String getBeschreibung() {
        return "Bohrmaschine " + modell + " (" + leistungWatt + " W)";
    }
}
