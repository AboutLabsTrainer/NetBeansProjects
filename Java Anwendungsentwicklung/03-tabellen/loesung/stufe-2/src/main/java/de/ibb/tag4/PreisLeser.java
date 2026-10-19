package de.ibb.tag4;

public final class PreisLeser {

    private PreisLeser() {
    }

    public static double lies(String text, double bisher) {
        if (text == null) {
            return bisher;
        }
        try {
            return Double.parseDouble(text.replace(',', '.'));
        } catch (NumberFormatException ex) {
            return bisher;
        }
    }
}
