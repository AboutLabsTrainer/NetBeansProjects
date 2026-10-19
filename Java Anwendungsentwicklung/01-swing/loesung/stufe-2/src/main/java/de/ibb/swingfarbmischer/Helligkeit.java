package de.ibb.swingfarbmischer;

public final class Helligkeit {

    private Helligkeit() {
    }

    public static boolean dunkel(int rot, int gruen, int blau) {
        double hell = 0.299 * rot + 0.587 * gruen + 0.114 * blau;
        return hell < 128;
    }
}
