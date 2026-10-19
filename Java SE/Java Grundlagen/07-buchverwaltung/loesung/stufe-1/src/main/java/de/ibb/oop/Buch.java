package de.ibb.oop;
public class Buch {
    private final String titel;
    private final String autor;
    private double preis;
    public Buch(String titel, String autor, double preis) {
        this.titel = titel;
        this.autor = autor;
        this.preis = preis;
    }
    public String getTitel() { return titel; }
    public String getAutor() { return autor; }
    public double getPreis() { return preis; }
    public void rabatt(double prozent) {
        preis = preis * (1 - prozent / 100.0);
    }
}
