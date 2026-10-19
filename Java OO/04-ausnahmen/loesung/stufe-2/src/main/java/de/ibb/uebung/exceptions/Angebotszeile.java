package de.ibb.uebung.exceptions;

public record Angebotszeile(String bezeichnung, double preisProTag, int tage) {

    public static Angebotszeile parse(String zeile) {
        if (zeile == null || zeile.isBlank()) {
            throw new IllegalArgumentException("Leere Zeile");
        }
        String[] teile = zeile.split(";");
        if (teile.length != 3) {
            throw new IllegalArgumentException("Ungültiges Format: " + zeile);
        }
        try {
            double preis = Double.parseDouble(teile[1]);
            int tage = Integer.parseInt(teile[2]);
            return new Angebotszeile(teile[0], preis, tage);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Zahlenformatfehler in Zeile: " + zeile, ex);
        }
    }
}
