package de.ibb.notebook;

public class Temperatur {
    public static double celsiusNachFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32;
    }

    public static String kategorie(double celsius) {
        if (celsius < 0) {
            return "kalt";
        }
        if (celsius < 20) {
            return "mild";
        }
        return "warm";
    }
}
