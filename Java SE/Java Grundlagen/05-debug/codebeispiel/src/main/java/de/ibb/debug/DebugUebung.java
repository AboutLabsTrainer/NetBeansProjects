package de.ibb.debug;

/**
 * Übung für den Debugger. Die Fehler in {@code square} und {@code sumUpTo}
 * sind absichtlich: Haltepunkte setzen und die Werte Schritt für Schritt prüfen,
 * statt die Methoden sofort zu korrigieren.
 */
public class DebugUebung {

    public static void main(String[] args) {

        int value = 5;
        int resultSquare = square(value);
        System.out.println("Quadrat von " + value + " = " + resultSquare);

        int n = 5;
        int sum = sumUpTo(n);
        System.out.println("Summe von 1 bis " + n + " = " + sum);
    }

    // Soll das Quadrat berechnen – enthält absichtlich einen Fehler
    private static int square(int value) {
        return value + value;      // BUG: sollte value * value sein
    }

    // Soll 1 + 2 + ... + n berechnen – enthält ebenfalls einen Fehler
    private static int sumUpTo(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {   // BUG: Bedingung sollte i <= n sein
            sum += i;
        }
        return sum;
    }
}
