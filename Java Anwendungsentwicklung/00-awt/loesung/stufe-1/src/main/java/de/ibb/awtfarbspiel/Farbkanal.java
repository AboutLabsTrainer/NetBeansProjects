package de.ibb.awtfarbspiel;

public final class Farbkanal {

    private Farbkanal() {
    }

    public static int begrenze(int wert) {
        if (wert < 0) {
            return 0;
        }
        if (wert > 255) {
            return 255;
        }
        return wert;
    }

    public static int[] rgb(int rot, int gruen, int blau) {
        return new int[] {begrenze(rot), begrenze(gruen), begrenze(blau)};
    }
}
