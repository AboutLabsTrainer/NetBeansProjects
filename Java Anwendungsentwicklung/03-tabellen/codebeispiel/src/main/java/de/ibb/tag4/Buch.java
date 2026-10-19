package de.ibb.tag4;

/**
 * Domänenklasse für Level 3 – Custom TableModel.
 */
public class Buch {

    private String titel;
    private String autor;
    private int jahr;
    private double preis;
    private boolean gelesen;

    public Buch(String titel, String autor, int jahr, double preis, boolean gelesen) {
        this.titel = titel;
        this.autor = autor;
        this.jahr = jahr;
        this.preis = preis;
        this.gelesen = gelesen;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public boolean isGelesen() {
        return gelesen;
    }

    public void setGelesen(boolean gelesen) {
        this.gelesen = gelesen;
    }
}
