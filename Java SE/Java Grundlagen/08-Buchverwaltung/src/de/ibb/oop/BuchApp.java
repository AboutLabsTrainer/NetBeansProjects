package de.ibb.oop;

/**
 * Testklasse für die Buch-Klasse.
 * Demonstriert das Erzeugen von Objekten und Aufruf von Methoden.
 */
public class BuchApp {

    public static void main(String[] args) {

        // Buch-Objekte mit vollständigem Konstruktor
        Buch javaInsel = new Buch(
                "Java ist auch eine Insel",
                "Christian Ullenboom",
                2019,
                49.90
        );

        Buch oopBuch = new Buch(
                "Objektorientierte Programmierung mit Java",
                "Max Muster",
                2021,
                39.90
        );

        System.out.println("=== Ausgangsdaten ===");
        javaInsel.druckeInfo();
        oopBuch.druckeInfo();

        // Rabatt auf ein Buch anwenden
        System.out.println("\n=== Rabattaktion: 15 % auf 'Java ist auch eine Insel' ===");
        javaInsel.rabatt(15); // 15 % Rabatt
        javaInsel.druckeInfo(); // neuer Preis

        // Buch mit Standard-Konstruktor + Setter
        System.out.println("\n=== Buch mit Standard-Konstruktor ===");
        Buch standardBuch = new Buch(); // nutzt Default-Werte
        standardBuch.druckeInfo();

        System.out.println("=== Werte über Setter setzen ===");
        standardBuch.setTitel("Einführung in Java");
        standardBuch.setAutor("Erika Beispiel");
        standardBuch.setErscheinungsjahr(2024);
        standardBuch.setPreis(29.90);
        standardBuch.druckeInfo();
    }
}
