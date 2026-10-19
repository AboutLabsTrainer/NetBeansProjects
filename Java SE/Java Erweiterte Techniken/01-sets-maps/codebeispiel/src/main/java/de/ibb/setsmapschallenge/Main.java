package de.ibb.setsmapschallenge;

import java.util.Scanner;

/**
 * Hauptklasse für die Mini-Challenge zu Sets & Maps
 * 
 * Enthält das Hauptmenü und steuert die Navigation zwischen Quiz und Level-Aufgaben.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║     Mini-Challenge: Sets & Maps – Keine Duplikate            ║");
        System.out.println("║     Basierend auf Tag 2: Java Erweiterte Techniken           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        boolean laeuft = true;

        while (laeuft) {
            zeigeMenue();

            int auswahl = leseAuswahl();

            switch (auswahl) {
                case 1:
                    Quiz.runQuiz();
                    break;
                case 2:
                    runLevel1();
                    break;
                case 3:
                    runLevel2();
                    break;
                case 4:
                    runLevel3();
                    break;
                case 0:
                    System.out.println("\nAuf Wiedersehen! 👋");
                    laeuft = false;
                    break;
                default:
                    System.out.println("\n⚠️ Ungültige Auswahl! Bitte wähle eine Zahl von 0-4.");
            }

            if (laeuft && auswahl != 0) {
                System.out.println();
                System.out.print("Drücke Enter, um zum Hauptmenü zurückzukehren...");
                scanner.nextLine();
                System.out.println();
            }
        }

        scanner.close();
    }

    private static void zeigeMenue() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("HAUPTMENÜ");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.println("  1) Quiz-Fragen bearbeiten");
        System.out.println("  2) Level 1 – Einsteiger");
        System.out.println("  3) Level 2 – Fortgeschritten");
        System.out.println("  4) Level 3 – Profi");
        System.out.println("  0) Beenden");
        System.out.println();
    }

    private static int leseAuswahl() {
        System.out.print("Deine Auswahl: ");
        try {
            String eingabe = scanner.nextLine().trim();
            return Integer.parseInt(eingabe);
        } catch (NumberFormatException e) {
            return -1; // Ungültige Eingabe
        }
    }

    private static void runLevel1() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              LEVEL 1 – EINSTEIGER                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        boolean zurueck = false;

        while (!zurueck) {
            System.out.println();
            System.out.println("Wähle eine Aufgabe:");
            System.out.println("  1) Aufgabe 1: HashSet mit Städten");
            System.out.println("  2) Aufgabe 2: HashMap Länder → Hauptstädte");
            System.out.println("  0) Zurück zum Hauptmenü");
            System.out.println();
            System.out.print("Deine Auswahl: ");

            int auswahl = leseAuswahl();

            switch (auswahl) {
                case 1:
                    Level1Einsteiger.runAufgabe1();
                    break;
                case 2:
                    Level1Einsteiger.runAufgabe2();
                    break;
                case 0:
                    zurueck = true;
                    break;
                default:
                    System.out.println("\n⚠️ Ungültige Auswahl!");
            }

            if (!zurueck && auswahl != 0) {
                System.out.println();
                System.out.print("Drücke Enter, um fortzufahren...");
                scanner.nextLine();
            }
        }
    }

    private static void runLevel2() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║            LEVEL 2 – FORTGESCHRITTEN                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        boolean zurueck = false;

        while (!zurueck) {
            System.out.println();
            System.out.println("Wähle eine Aufgabe:");
            System.out.println("  1) Aufgabe 1: Student mit equals/hashCode");
            System.out.println("  2) Aufgabe 2: Worthäufigkeit zählen");
            System.out.println("  0) Zurück zum Hauptmenü");
            System.out.println();
            System.out.print("Deine Auswahl: ");

            int auswahl = leseAuswahl();

            switch (auswahl) {
                case 1:
                    Level2Fortgeschritten.runAufgabe1();
                    break;
                case 2:
                    Level2Fortgeschritten.runAufgabe2();
                    break;
                case 0:
                    zurueck = true;
                    break;
                default:
                    System.out.println("\n⚠️ Ungültige Auswahl!");
            }

            if (!zurueck && auswahl != 0) {
                System.out.println();
                System.out.print("Drücke Enter, um fortzufahren...");
                scanner.nextLine();
            }
        }
    }

    private static void runLevel3() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                  LEVEL 3 – PROFI                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        boolean zurueck = false;

        while (!zurueck) {
            System.out.println();
            System.out.println("Wähle eine Aufgabe:");
            System.out.println("  1) Aufgabe 1: LRU-Cache mit LinkedHashMap");
            System.out.println("  2) Aufgabe 2: TreeSet mit Comparator");
            System.out.println("  0) Zurück zum Hauptmenü");
            System.out.println();
            System.out.print("Deine Auswahl: ");

            int auswahl = leseAuswahl();

            switch (auswahl) {
                case 1:
                    Level3Profi.runAufgabe1();
                    break;
                case 2:
                    Level3Profi.runAufgabe2();
                    break;
                case 0:
                    zurueck = true;
                    break;
                default:
                    System.out.println("\n⚠️ Ungültige Auswahl!");
            }

            if (!zurueck && auswahl != 0) {
                System.out.println();
                System.out.print("Drücke Enter, um fortzufahren...");
                scanner.nextLine();
            }
        }
    }
}
