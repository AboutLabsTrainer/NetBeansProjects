package de.ibb.debugchallenge;
public class Auswerter {
    public static int summe(int[] werte) {
        int summe = 0;
        for (int wert : werte) {
            summe += wert;
        }
        return summe;
    }
    public static int maximum(int[] werte) {
        int max = werte[0];
        for (int wert : werte) {
            if (wert > max) {
                max = wert;
            }
        }
        return max;
    }
    public static double durchschnitt(int[] werte) {
        return summe(werte) / (double) werte.length;
    }
    public static int indexVon(int[] werte, int suche) {
        for (int i = 0; i < werte.length; i++) {
            if (werte[i] == suche) {
                return i;
            }
        }
        return -1;
    }
}
