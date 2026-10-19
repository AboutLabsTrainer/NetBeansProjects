package de.ibb.debug;
public class Mittelwert {
    public static double durchschnitt(int[] werte) {
        if (werte.length == 0) {
            return 0;
        }
        int summe = 0;
        for (int wert : werte) {
            summe += wert;
        }
        return summe / (double) werte.length;
    }
}
