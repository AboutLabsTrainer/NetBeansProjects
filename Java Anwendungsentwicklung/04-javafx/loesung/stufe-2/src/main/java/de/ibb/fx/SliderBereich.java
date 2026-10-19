package de.ibb.fx;

public final class SliderBereich {

    private SliderBereich() {
    }

    public static double begrenze(double wert) {
        if (wert < 0) {
            return 0;
        }
        if (wert > 100) {
            return 100;
        }
        return wert;
    }
}
