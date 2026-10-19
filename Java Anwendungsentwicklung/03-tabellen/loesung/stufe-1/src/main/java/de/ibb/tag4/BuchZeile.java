package de.ibb.tag4;

public final class BuchZeile {

    private BuchZeile() {
    }

    public static Object wert(String titel, String autor, int jahr, double preis, boolean gelesen, int spalte) {
        return switch (spalte) {
            case 0 -> titel;
            case 1 -> autor;
            case 2 -> jahr;
            case 3 -> preis;
            case 4 -> gelesen;
            default -> null;
        };
    }
}
