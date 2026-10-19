package de.ibb.fenstertrainer;

public final class Statuszeile {

    private Statuszeile() {
    }

    public static String fuerKlick(int x, int y) {
        return "Mausklick bei (" + x + ", " + y + ")";
    }
}
