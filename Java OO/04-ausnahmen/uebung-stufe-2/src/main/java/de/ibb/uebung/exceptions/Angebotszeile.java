package de.ibb.uebung.exceptions;

public record Angebotszeile(String bezeichnung, double preisProTag, int tage) {

    public static Angebotszeile parse(String zeile) {
        throw new IllegalArgumentException("TODO");
    }
}
