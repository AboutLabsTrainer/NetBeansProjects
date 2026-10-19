package de.ibb.uebung.fahrzeuge;

/**
 * Demo- und Testklasse für das Projekt
 * "03-UebungFahrzeugeVererbung".
 *
 * Zeigt:
 *  - Erzeugen von Unterklassen-Objekten (Pkw, Lkw)
 *  - Verwendung des Basistyps Fahrzeug
 *  - Polymorphie im Array
 *  - instanceof + Downcasting
 */
public class FahrzeugDemo {

    public static void main(String[] args) {

        // ---------------------------------------------------------
        // 1. Schritt: Einzelne Objekte erzeugen
        // ---------------------------------------------------------
        Pkw pkw1 = new Pkw("B-AB 1234", 2015, 5);
        Lkw lkw1 = new Lkw("B-XY 9999", 2018, 7.5);

        System.out.println("=== Einzelne Objekte ===");
        pkw1.druckeDaten();
        System.out.println();
        lkw1.druckeDaten();
        System.out.println();

        // ---------------------------------------------------------
        // 2. Schritt: Polymorphie mit Basisklassen-Array
        // ---------------------------------------------------------
        Fahrzeug[] flotte = new Fahrzeug[3];
        flotte[0] = pkw1;                                // Upcast auf Fahrzeug
        flotte[1] = lkw1;
        flotte[2] = new Pkw("B-CD 4321", 2020, 3);

        System.out.println("=== Polymorphie im Array Fahrzeug[] ===");
        for (Fahrzeug f : flotte) {
            // Dynamischer Methodenaufruf:
            // druckeDaten() der jeweiligen Unterklasse wird verwendet
            f.druckeDaten();

            // instanceof + Downcast für typabhängige Logik
            if (f instanceof Pkw) {
                Pkw p = (Pkw) f;
                nimmPkw(p);
            } else if (f instanceof Lkw) {
                Lkw l = (Lkw) f;
                nimmLkw(l);
            }

            System.out.println();
        }

        System.out.println("=== toString()-Ausgabe ===");
        for (Fahrzeug f : flotte) {
            System.out.println(f);
        }
    }

    // -------------------------------------------------------------
    // Hilfsmethoden für die Typ-spezifische Verarbeitung
    // -------------------------------------------------------------

    public static void nimmPkw(Pkw pkw) {
        System.out.println("-> nimmPkw() aufgerufen für: " + pkw.getKennzeichen());
    }

    public static void nimmLkw(Lkw lkw) {
        System.out.println("-> nimmLkw() aufgerufen für: " + lkw.getKennzeichen());
    }
}