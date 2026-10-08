package de.ibb.debugchallenge;

/**
 * Musterlösung zur Mini-Debugging-Challenge "Fehler im Zahlenauswerter".
 *
 * Korrigiert:
 * - Schleifenbereiche (startet bei Index 0, Bedingung < length)
 * - Initialisierung von max
 * - Durchschnittsberechnung mit Gleitkommadivision
 * - Schleifenbedingung in findIndex(...)
 */
public class ZahlenauswerterLoesung {

    public static void main(String[] args) {

        int[] values = {5, 8, -3, 10, 42};

        // Summe und Maximum berechnen
        int sum = 0;

        // max mit erstem Element initialisieren, damit auch bei nur negativen
        // Zahlen ein korrektes Maximum gefunden wird
        int max = values[0];

        // Schleife: von Index 0 bis length-1
        for (int i = 0; i < values.length; i++) {
            sum += values[i];

            if (values[i] > max) {
                max = values[i];
            }
        }

        // Durchschnitt als double berechnen (Gleitkommadivision)
        double average = (double) sum / values.length;

        System.out.println("Summe:        " + sum);
        System.out.println("Maximum:      " + max);
        System.out.println("Durchschnitt: " + average);

        // Extra: Suche nach einer Zahl
        int search = 10;
        int index = findIndex(values, search);
        System.out.println("Index von " + search + ": " + index);
    }

    /**
     * Sucht den Index einer Zahl im Array.
     * Wird die Zahl nicht gefunden, wird -1 zurückgegeben.
     */
    private static int findIndex(int[] values, int search) {
        // auch hier: i < values.length, sonst ArrayIndexOutOfBoundsException
        for (int i = 0; i < values.length; i++) {
            if (values[i] == search) {
                return i;
            }
        }
        return -1;
    }
}
