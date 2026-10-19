package de.ibb.debugchallenge;
public class Spanne {
    public static int differenz(int[] werte) {
        int min = werte[0];
        int max = werte[0];
        for (int wert : werte) {
            if (wert < min) {
                min = wert;
            }
            if (wert > max) {
                max = wert;
            }
        }
        return max - min;
    }
}
