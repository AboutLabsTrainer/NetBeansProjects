package de.ibb.controlflow;

import java.util.Scanner;

/**
 * Konsolenprogramm zur Demonstration von Ablaufsteuerung:
 * - Menü mit do-while + switch
 * - if-else-if zur Preislogik
 * - for-Schleife für mehrere Tickets
 * - while-Schleife zur Eingabeprüfung
 */
public class TicketKasse {

    // Grundpreise als Konstanten
    private static final double PRICE_CHILD   = 5.00;
    private static final double PRICE_STANDARD = 10.00;
    private static final double PRICE_SENIOR   = 7.00;

    public static void main(String[] args) {
        // try-with-resources schließt den Scanner auch dann, wenn nextInt eine Exception wirft.
        try (Scanner scanner = new Scanner(System.in)) {

        // Optional: Rabatt-Tag abfragen (z.B. Mittwoch)
        System.out.print("Ist heute Rabatt-Tag (z.B. Mittwoch)? (j/n): ");
        String rabattAntwort = scanner.nextLine().trim().toLowerCase();
        boolean isDiscountDay = rabattAntwort.equals("j");
        double discountFactor = 0.8; // 20% Rabatt

        // Statistik über gesamten Tag
        int totalChildrenTickets = 0;
        int totalStandardTickets = 0;
        int totalSeniorTickets   = 0;
        double totalRevenue      = 0.0;

        int choice;
        do {
            // -----------------------------
            // Hauptmenü
            // -----------------------------
            System.out.println("\n===== Ticket-Kasse =====");
            System.out.println("1 - Neue Tickets verkaufen");
            System.out.println("0 - Programm beenden");
            System.out.print("Auswahl: ");

            // einfache Annahme: Eingabe ist eine Zahl
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    // -----------------------------
                    // Tickets verkaufen
                    // -----------------------------
                    System.out.print("Wie viele Tickets sollen verkauft werden? ");
                    int ticketCount = scanner.nextInt();

                    double saleTotal = 0.0; // Summe dieser Verkaufseinheit

                    for (int i = 1; i <= ticketCount; i++) {
                        System.out.println("\n--- Ticket " + i + " von " + ticketCount + " ---");

                        // Alter mit while-Schleife validieren
                        int age = -1;
                        while (age < 0 || age > 120) {
                            System.out.print("Bitte Alter eingeben (0-120): ");
                            age = scanner.nextInt();

                            if (age < 0 || age > 120) {
                                System.out.println("Ungültiges Alter! Bitte erneut versuchen.");
                            }
                        }

                        // Tickettyp bestimmen (if-else-if)
                        String ticketType;
                        double price;

                        if (age < 14) {
                            ticketType = "Kinder-Ticket";
                            price = PRICE_CHILD;
                            totalChildrenTickets++;
                        } else if (age < 65) {
                            ticketType = "Standard-Ticket";
                            price = PRICE_STANDARD;
                            totalStandardTickets++;
                        } else {
                            ticketType = "Senioren-Ticket";
                            price = PRICE_SENIOR;
                            totalSeniorTickets++;
                        }

                        // Rabatt anwenden, falls Rabatt-Tag
                        if (isDiscountDay) {
                            price = price * discountFactor;
                        }

                        // Quittung für dieses Ticket
                        System.out.printf("Verkauftes Ticket: %s%n", ticketType);
                        System.out.printf("Alter:            %d%n", age);
                        System.out.printf("Preis:            %.2f EUR%n", price);

                        saleTotal += price;
                    }

                    // Zusammenfassung dieser Verkaufseinheit
                    System.out.println("\n=== Zusammenfassung dieses Verkaufs ===");
                    System.out.printf("Anzahl Tickets: %d%n", ticketCount);
                    System.out.printf("Gesamtpreis:    %.2f EUR%n", saleTotal);

                    totalRevenue += saleTotal; // zur Tagesstatistik addieren
                }

                case 0 -> {
                    // nichts, Schleife endet gleich
                    System.out.println("\nProgramm wird beendet...");
                }

                default -> {
                    System.out.println("Ungültige Auswahl! Bitte 1 oder 0 eingeben.");
                }
            }

        } while (choice != 0);

        // -----------------------------
        // Tagesstatistik am Ende
        // -----------------------------
        int totalTickets = totalChildrenTickets + totalStandardTickets + totalSeniorTickets;

        System.out.println("\n===== Tagesstatistik =====");
        System.out.printf("Kinder-Tickets : %d%n", totalChildrenTickets);
        System.out.printf("Standard-Tickets: %d%n", totalStandardTickets);
        System.out.printf("Senioren-Tickets: %d%n", totalSeniorTickets);
        System.out.printf("Gesamtanzahl   : %d%n", totalTickets);
        System.out.printf("Gesamtumsatz   : %.2f EUR%n", totalRevenue);
        }
    }
}
