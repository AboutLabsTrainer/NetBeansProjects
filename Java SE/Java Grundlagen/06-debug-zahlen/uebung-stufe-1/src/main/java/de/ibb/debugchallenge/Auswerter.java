package de.ibb.debugchallenge;
public class Auswerter {
    public static int summe(int[] werte) {
        int summe = 0;
        for (int i = 1; i <= werte.length; i++) { // TODO
            summe += werte[i];
        }
        return summe;
    }
    public static int maximum(int[] werte) {
        int max = 0; // TODO
        for (int wert : werte) {
            if (wert > max) {
                max = wert;
            }
        }
        return max;
    }
    public static double durchschnitt(int[] werte) {
        return summe(werte) / werte.length; // TODO
    }
    public static int indexVon(int[] werte, int suche) {
        for (int i = 0; i <= werte.length; i++) { // TODO
            if (werte[i] == suche) {
                return i;
            }
        }
        return -1;
    }
}
