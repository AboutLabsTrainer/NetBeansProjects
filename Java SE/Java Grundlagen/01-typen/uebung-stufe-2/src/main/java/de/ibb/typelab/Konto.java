package de.ibb.typelab;
public class Konto {
    private final String inhaber;
    private double stand;
    public Konto(String inhaber, double stand) {
        this.inhaber = inhaber;
        this.stand = stand;
    }
    public String getInhaber() { return inhaber; }
    public double getStand() { return stand; }
    public void einzahlen(double betrag) { /* TODO */ }
    public boolean abheben(double betrag) { return false; /* TODO */ }
}
