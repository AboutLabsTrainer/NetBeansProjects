package de.ibb.uebung.exceptions;

public final class Mietpreis {

    private Mietpreis() {
    }

    public static double berechne(double preisProTag, int tage) throws UngueltigeMietdauerException {
        if (tage <= 0) {
            throw new UngueltigeMietdauerException("Mietdauer muss positiv sein.");
        }
        if (tage > 30) {
            throw new UngueltigeMietdauerException("Mietdauer darf 30 Tage nicht überschreiten.");
        }
        if (preisProTag < 0) {
            throw new UngueltigeMietdauerException("Tagespreis darf nicht negativ sein.");
        }
        return preisProTag * tage;
    }
}
