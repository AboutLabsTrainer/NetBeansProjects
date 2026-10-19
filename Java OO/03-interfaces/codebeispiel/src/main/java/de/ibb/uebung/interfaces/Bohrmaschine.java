package de.ibb.uebung.interfaces;

/**
 * Repräsentiert eine Bohrmaschine als Mietobjekt.
 */
public class Bohrmaschine implements Mietbar, Versicherbar {

    private String modell;
    private int leistungWatt;
    private MietobjektArt art;

    public Bohrmaschine(String modell, int leistungWatt) {
        this.modell = modell;
        this.leistungWatt = leistungWatt;
        this.art = MietobjektArt.BOHRMASCHINE;
    }

    public String getModell() {
        return modell;
    }

    public int getLeistungWatt() {
        return leistungWatt;
    }

    @Override
    public double berechneMietpreis(int tage) {
        if (tage <= 0) {
            return 0.0;
        }
        double preisProTag = 20.0;
        if (leistungWatt > 1000) {
            preisProTag += 5.0; // Aufschlag für leistungsstarke Geräte
        }
        return preisProTag * tage;
    }

    @Override
    public String getBeschreibung() {
        return "Bohrmaschine " + modell + " (" + leistungWatt + " W) [" + art + "]";
    }

    @Override
    public double berechneVersicherungsPraemie() {
        // Beispiel: pauschale Prämie
        return 5.0;
    }
}