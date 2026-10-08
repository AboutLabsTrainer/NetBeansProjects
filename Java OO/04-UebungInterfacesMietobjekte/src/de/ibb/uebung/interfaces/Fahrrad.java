package de.ibb.uebung.interfaces;

/**
 * Repräsentiert ein Fahrrad (normal oder E-Bike).
 */
public class Fahrrad implements Mietbar {

    private String rahmennummer;
    private boolean eBike;
    private MietobjektArt art;

    public Fahrrad(String rahmennummer, boolean eBike) {
        this.rahmennummer = rahmennummer;
        this.eBike = eBike;
        this.art = MietobjektArt.FAHRRAD;
    }

    public String getRahmennummer() {
        return rahmennummer;
    }

    public boolean iseBike() {
        return eBike;
    }

    @Override
    public double berechneMietpreis(int tage) {
        if (tage <= 0) {
            return 0.0;
        }
        double preisProTag;
        if (eBike) {
            preisProTag = 25.0; // z.B. E-Bike teurer
        } else {
            preisProTag = 15.0; // normales Fahrrad
        }
        return preisProTag * tage;
    }

    @Override
    public String getBeschreibung() {
        String typ = eBike ? "E-Bike" : "Fahrrad";
        return typ + " " + rahmennummer + " [" + art + "]";
    }
}