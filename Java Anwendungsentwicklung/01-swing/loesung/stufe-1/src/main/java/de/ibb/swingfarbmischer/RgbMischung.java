package de.ibb.swingfarbmischer;

public final class RgbMischung {

    private RgbMischung() {
    }

    public static String hex(int rot, int gruen, int blau) {
        return String.format("#%02X%02X%02X", kanal(rot), kanal(gruen), kanal(blau));
    }

    private static int kanal(int wert) {
        if (wert < 0) {
            return 0;
        }
        if (wert > 255) {
            return 255;
        }
        return wert;
    }
}
