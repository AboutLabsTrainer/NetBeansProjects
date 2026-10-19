package de.ibb.swingfarbmischer;

/** Farbe aus den drei Slidern des Farbmischers, als Hex ohne Fenster. */
public final class RgbMischung {

    private RgbMischung() {
    }

    public static String hex(int rot, int gruen, int blau) {
        // TODO: Kanäle auf 0..255 begrenzen und als #RRGGBB ausgeben
        return "#000000";
    }
}
