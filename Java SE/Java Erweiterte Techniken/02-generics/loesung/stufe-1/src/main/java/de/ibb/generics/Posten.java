package de.ibb.generics;

public class Posten {

    private final String produkt;
    private final double preis;

    public Posten(String produkt, double preis) {
        this.produkt = produkt;
        this.preis = preis;
    }

    public String getProdukt() {
        return produkt;
    }

    public double getPreis() {
        return preis;
    }
}
