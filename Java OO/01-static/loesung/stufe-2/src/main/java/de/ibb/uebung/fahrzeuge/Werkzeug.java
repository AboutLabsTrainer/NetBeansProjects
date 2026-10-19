package de.ibb.uebung.fahrzeuge;

public class Werkzeug {

    private static int anzahlVermietet;
    private boolean vermietet;

    public void miete() {
        if (!vermietet) {
            vermietet = true;
            anzahlVermietet++;
        }
    }

    public void rueckgabe() {
        if (vermietet) {
            vermietet = false;
            anzahlVermietet--;
        }
    }

    public static int getAnzahlVermietet() {
        return anzahlVermietet;
    }
}
