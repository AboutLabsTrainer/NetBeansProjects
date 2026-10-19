package de.ibb;

/**
 * Klasse zur Repräsentation einer Bestellung.
 * Enthält Produktname und Preis.
 */
public class Bestellung {
    private String produkt;
    private double preis;
    
    public Bestellung(String produkt, double preis) {
        this.produkt = produkt;
        this.preis = preis;
    }
    
    public double getPreis() {
        return preis;
    }
    
    public String getProdukt() {
        return produkt;
    }
}
