package de.ibb.uebung.interfaces;

/**
 * Repräsentiert ein Auto als Mietobjekt.
 */
public class Auto implements Mietbar, Versicherbar {

    private String kennzeichen;
    private double preisProTag;
    private MietobjektArt art;

    public Auto(String kennzeichen, double preisProTag) {
        this.kennzeichen = kennzeichen;
        this.preisProTag = preisProTag;
        this.art = MietobjektArt.AUTO;
    }

    public String getKennzeichen() {
        return kennzeichen;
    }

    public double getPreisProTag() {
        return preisProTag;
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
        return "Auto " + kennzeichen + " [" + art + "]";
    }

    @Override
    public double berechneVersicherungsPraemie() {
        // Beispiel: 10 % des Tagespreises als Versicherungsprämie
        return preisProTag * 0.10;
    }
}