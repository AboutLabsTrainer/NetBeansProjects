package de.ibb.debugchallenge;

/**
 * Fehlerhafte Vorlage für die Debugging-Challenge.
 * Die Schleifen laufen absichtlich über den Array-Rand, {@code max} startet bei 0
 * und die Division für den Durchschnitt ist ganzzahlig. Die korrigierte Fassung
 * steht in {@link ZahlenauswerterLoesung}.
 */
public class Zahlenauswerter {

    public static void main(String[] args) {

        int[] values = {5, 8, -3, 10, 42};

        int sum = 0;
        int max = 0; // soll das Maximum enthalten

        // Schleife über alle Werte
        for (int i = 1; i <= values.length; i++) {
            sum += values[i];

            if (values[i] > max) {
                max = values[i];
            }
        }

        double average = sum / values.length;

        System.out.println("Summe: " + sum);
        System.out.println("Maximum: " + max);
        System.out.println("Durchschnitt: " + average);

        // Extra: Suche nach einer Zahl
        int search = 10;
        int index = findIndex(values, search);
        System.out.println("Index von " + search + ": " + index);
    }

    // soll den Index einer gesuchten Zahl im Array zurückgeben
    private static int findIndex(int[] values, int search) {
        for (int i = 0; i <= values.length; i++) {
            if (values[i] == search) {
                return i;
            }
        }
        return -1;
    }
}
