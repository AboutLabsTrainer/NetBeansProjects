package de.ibb.uebung.exceptions;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Musterlösung zur Übung "Ausnahmebehandlung".
 *
 * Zeigt:
 *  - Absicherung von Benutzereingaben mit try/catch
 *  - Verwendung einer eigenen checked Exception (UngueltigeMietdauerException)
 *  - finally-Block für Aufräumarbeiten
 *  - Einlesen einer Datei mit try-with-resources
 *  - Provozieren und Behandeln einer RuntimeException
 */
public class MietRechner {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double preisProTag;
        int tage;

        try {
            System.out.print("Bitte Tagespreis eingeben (z.B. 49.99): ");
            preisProTag = scanner.nextDouble();

            System.out.print("Bitte Anzahl der Tage eingeben (1-30): ");
            tage = scanner.nextInt();

            double gesamt = berechneMietpreis(preisProTag, tage);
            System.out.println("Gesamtpreis: " + gesamt + " EUR");

        } catch (InputMismatchException ex) {
            System.out.println("Eingabefehler: Bitte verwenden Sie die richtige Zahlenschreibweise.");
            return;
        } catch (UngueltigeMietdauerException ex) {
            System.out.println("Fachlicher Fehler: " + ex.getMessage());
            return;
        } finally {
            System.out.println("MietRechner wird beendet – Ressourcen werden freigegeben.");
            scanner.close();
        }

        // Datei einlesen (Stufe 4)
        leseAngeboteAusDatei("angebote.txt");

        // RuntimeException provozieren (Stufe 5)
        provoziereRuntimeException();
    }

    /**
     * Berechnet den Mietpreis und wirft bei ungültiger Mietdauer
     * eine UngueltigeMietdauerException.
     *
     * Bedingungen:
     *  - tage <= 0  -> Exception
     *  - tage > 30  -> Exception (max. Mietdauer 30 Tage)
     *  - preisProTag < 0 -> Exception
     */
    public static double berechneMietpreis(double preisProTag, int tage)
            throws UngueltigeMietdauerException {

        if (tage <= 0) {
            throw new UngueltigeMietdauerException("Mietdauer muss positiv sein.");
        }
        if (tage > 30) {
            throw new UngueltigeMietdauerException("Mietdauer darf 30 Tage nicht überschreiten.");
        }
        if (preisProTag < 0) {
            throw new UngueltigeMietdauerException("Tagespreis darf nicht negativ sein.");
        }

        return preisProTag * tage;
    }

    /**
     * Liest Angebote aus einer Textdatei ein und berechnet für jede Zeile den Mietpreis.
     *
     * Dateiformat je Zeile: Bezeichnung;PreisProTag;Tage
     *   z.B.: Auto;49.99;3
     *
     * - nutzt try-with-resources mit BufferedReader
     * - behandelt IOException und Formatfehler (NumberFormatException)
     */
    public static void leseAngeboteAusDatei(String dateiname) {
        System.out.println();
        System.out.println("Angebote aus Datei \"" + dateiname + "\":");

        // angebote.txt liegt auf dem Klassenpfad (src/main/resources), nicht im Arbeitsverzeichnis.
        InputStream resource = MietRechner.class.getClassLoader().getResourceAsStream(dateiname);
        if (resource == null) {
            System.out.println("Fehler beim Lesen der Datei: " + dateiname
                    + " (Klassenpfad, Quelle src/main/resources)");
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource, StandardCharsets.UTF_8))) {
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                if (zeile.isBlank()) {
                    continue;
                }

                String[] teile = zeile.split(";");
                if (teile.length != 3) {
                    System.out.println("Warnung: Ungültiges Format: " + zeile);
                    continue;
                }

                String bezeichnung = teile[0];
                try {
                    double preis = Double.parseDouble(teile[1]);
                    int tage = Integer.parseInt(teile[2]);

                    try {
                        double gesamt = berechneMietpreis(preis, tage);
                        System.out.printf("%s: %d Tage, Gesamtpreis: %.2f EUR%n",
                                bezeichnung, tage, gesamt);
                    } catch (UngueltigeMietdauerException ex) {
                        System.out.println("Fachlicher Fehler bei \"" + bezeichnung + "\": " + ex.getMessage());
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Warnung: Zahlenformatfehler in Zeile: " + zeile);
                }
            }
        } catch (IOException ex) {
            System.out.println("Fehler beim Lesen der Datei: " + ex.getMessage());
        }
    }

    /**
     * Provoziert eine RuntimeException (ArrayIndexOutOfBoundsException)
     * und fängt sie ab.
     *
     * Dient zur Demonstration:
     *  - RuntimeExceptions (unchecked) müssen nicht deklariert werden
     *  - Sie weisen oft auf Programmierfehler hin (z.B. falscher Index).
     */
    public static void provoziereRuntimeException() {
        System.out.println();
        System.out.println("RuntimeException-Demo:");

        try {
            int[] werte = {1, 2, 3};
            // bewusster Fehler: Index 5 existiert nicht
            System.out.println("Wert an Index 5: " + werte[5]);
        } catch (RuntimeException ex) {
            System.out.println("Abgefangene RuntimeException: " + ex);
            // Hinweis:
            // RuntimeExceptions (unchecked) müssen nicht zwingend behandelt werden.
            // Sie weisen oft auf Programmierfehler hin (z.B. falscher Index).
        }

        // Hinweis zu Error-Typen im Kommentar:
        // Ein OutOfMemoryError oder StackOverflowError sind schwerwiegende Fehler,
        // die meist nicht sinnvoll abgefangen werden können und auf Probleme in der
        // Umgebung oder Programmarchitektur hinweisen.
    }
}