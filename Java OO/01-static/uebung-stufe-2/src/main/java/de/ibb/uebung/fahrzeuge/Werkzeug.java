package de.ibb.uebung.fahrzeuge;

public class Werkzeug {

    private static int anzahlVermietet;
    private boolean vermietet;

    public void miete() {
        // TODO: nur zählen, wenn es noch nicht vermietet ist
    }

    public void rueckgabe() {
        // TODO: Zähler nur senken, wenn es vermietet war
    }

    public static int getAnzahlVermietet() {
        return anzahlVermietet;
    }
}
