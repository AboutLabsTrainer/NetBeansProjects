package de.ibb.flow;

import java.util.Scanner;

/**
 * Demo-Programm zur Ablaufsteuerung in Java.
 *
 * Enthält Beispiele für:
 * - if / if-else / if-else-if
 * - switch
 * - while
 * - do-while
 * - for
 */
public class AblaufsteuerungDemo {

    public static void main(String[] args) {

        // try-with-resources schließt den Scanner auch dann, wenn nextInt eine Exception wirft.
        try (Scanner scanner = new Scanner(System.in)) {

        // ------------------------------------------------------------
        // Aufgabe 1: Verzweigungen – Noten-Feedback
        // ------------------------------------------------------------
        int grade = -1;

        // Eingabeprüfung mit while
        while (grade < 1 || grade > 6) {
            System.out.print("Bitte Schulnote (1-6) eingeben: ");
            grade = scanner.nextInt();

            if (grade < 1 || grade > 6) {
                System.out.println("Ungültige Note, bitte erneut versuchen.");
            }
        }

        System.out.println("\n=== Noten-Feedback ===");
        if (grade == 1 || grade == 2) {
            System.out.println("Sehr gut / Gut – weiter so!");
        } else if (grade == 3 || grade == 4) {
            System.out.println("Ausreichend – noch etwas üben.");
        } else if (grade == 5 || grade == 6) {
            System.out.println("Nicht bestanden – bitte intensiv nacharbeiten.");
        }

        // ------------------------------------------------------------
        // Aufgabe 2: switch – Wochentag anzeigen
        // ------------------------------------------------------------
        int day = 0;

        while (day < 1 || day > 7) {
            System.out.print("\nBitte Zahl für Wochentag (1=Mo ... 7=So) eingeben: ");
            day = scanner.nextInt();

            if (day < 1 || day > 7) {
                System.out.println("Ungültige Zahl, bitte 1-7 eingeben.");
            }
        }

        System.out.println("\n=== Wochentag ===");
        switch (day) {
            case 1 -> System.out.println("Montag");
            case 2 -> System.out.println("Dienstag");
            case 3 -> System.out.println("Mittwoch");
            case 4 -> System.out.println("Donnerstag");
            case 5 -> System.out.println("Freitag");
            case 6 -> System.out.println("Samstag");
            case 7 -> System.out.println("Sonntag");
            default -> System.out.println("Dieser Fall sollte wegen der Prüfung oben nie auftreten.");
        }

        // ------------------------------------------------------------
        // Aufgabe 3: do-while – Zahlenraten
        // ------------------------------------------------------------
        final int secret = 4; // feste Geheimzahl
        int guess;

        System.out.println("\n=== Zahlenraten (1-5) ===");

        do {
            System.out.print("Dein Tipp: ");
            guess = scanner.nextInt();

            if (guess < secret) {
                System.out.println("Zu klein!");
            } else if (guess > secret) {
                System.out.println("Zu groß!");
            } else {
                System.out.println("Treffer, Glückwunsch!");
            }

        } while (guess != secret); // Schleife läuft, bis korrekt geraten wurde

        // ------------------------------------------------------------
        // Aufgabe 4: for – Einmaleins
        // ------------------------------------------------------------
        int n = 0;

        while (n < 1 || n > 10) {
            System.out.print("\nBitte eine Zahl für das Einmaleins (1-10) eingeben: ");
            n = scanner.nextInt();

            if (n < 1 || n > 10) {
                System.out.println("Bitte eine Zahl zwischen 1 und 10 eingeben.");
            }
        }

        System.out.println("\n=== Einmaleins von " + n + " ===");
        // for-Schleife: Startwert; Bedingung; Schrittweite
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }

        System.out.println("\nProgrammende.");
        }
    }
}
