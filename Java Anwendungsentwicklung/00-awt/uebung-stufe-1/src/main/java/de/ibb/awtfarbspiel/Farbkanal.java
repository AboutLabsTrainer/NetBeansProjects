package de.ibb.awtfarbspiel;

/** Kanalgrenzen der Zufallsfarbe in FarbFenster: 0 bis 255. */
public final class Farbkanal {

    private Farbkanal() {
    }

    public static int begrenze(int wert) {
        // TODO
        return 0;
    }

    public static int[] rgb(int rot, int gruen, int blau) {
        return new int[] {begrenze(rot), begrenze(gruen), begrenze(blau)};
    }
}
