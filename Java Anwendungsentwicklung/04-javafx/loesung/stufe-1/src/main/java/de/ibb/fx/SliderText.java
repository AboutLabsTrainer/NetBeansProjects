package de.ibb.fx;

public final class SliderText {

    private SliderText() {
    }

    public static String format(double wert) {
        return String.format("Wert: %.0f", wert);
    }
}
