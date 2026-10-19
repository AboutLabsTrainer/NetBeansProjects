package de.ibb.uebung.fahrzeuge;

/**
 * Demo- und Testklasse
 * für Klassendefinition, Konstruktoren,
 * Typumwandlung und Typuntersuchung.
 */
public class FahrzeugDemo {

    public static void main(String[] args) {

        // ---------------------------------------------------------
        // STUFE 1: Klassen, Eigenschaften, Konstruktoren
        // ---------------------------------------------------------
        Pkw pkw1 = new Pkw("B-AB 1234", 2015, 49.99, 5);
        Lkw lkw1 = new Lkw("B-XY 9999", 2018, 89.99, 7.5);

        System.out.println("=== Stufe 1: Einzelne Objekte ===");
        pkw1.druckeDaten();
        System.out.println();
        lkw1.druckeDaten();
        System.out.println();

        // ---------------------------------------------------------
        // STUFE 2: Vererbung, Polymorphie, instanceof, Downcast
        // ---------------------------------------------------------
        Fahrzeug[] flotte = new Fahrzeug[3];
        flotte[0] = pkw1;                                // impliziter Upcast
        flotte[1] = lkw1;
        flotte[2] = new Pkw("B-CD 4321", 2020, 39.99, 3);

        System.out.println("=== Stufe 2: Polymorphie im Array Fahrzeug[] ===");
        for (Fahrzeug f : flotte) {
            // Polymorpher Methodenaufruf (richtige Unterklasse)
            f.druckeDaten();

            // Typuntersuchung + Downcast
            if (f instanceof Pkw) {
                Pkw p = (Pkw) f;      // Downcast auf Pkw
                nimmPkw(p);
            } else if (f instanceof Lkw) {
                Lkw l = (Lkw) f;      // Downcast auf Lkw
                nimmLkw(l);
            }

            System.out.println();
        }

        System.out.println("=== toString()-Ausgabe ===");
        for (Fahrzeug f : flotte) {
            System.out.println(f);
        }
        System.out.println();

        // ---------------------------------------------------------
        // STUFE 2: Typumwandlung bei primitiven Datentypen
        // ---------------------------------------------------------
        int tageAlsInt = 5;
        short tageAlsShort = (short) tageAlsInt; // expliziter Cast

        double preisNetto = berechneMietpreis(
                pkw1.getMietpreisProTag(), tageAlsShort);

        System.out.println("=== Stufe 2: Typumwandlung (primitiv) ===");
        System.out.println("tageAlsInt   = " + tageAlsInt);
        System.out.println("tageAlsShort = " + tageAlsShort);
        System.out.printf("Mietpreis netto für %d Tage: %.2f EUR%n",
                tageAlsShort, preisNetto);
        System.out.println();

        // ---------------------------------------------------------
        // STUFE 3: Boxing / Unboxing, weitere Typumwandlungen
        // ---------------------------------------------------------
        System.out.println("=== Stufe 3: Wrapper, Boxing / Unboxing ===");
        short tage = 7;
        Short verpackteTage = tage;          // Auto-Boxing
        short wiederAusgepackt = verpackteTage; // Auto-Unboxing

        System.out.println("tage (short):             " + tage);
        System.out.println("verpackteTage (Short):    " + verpackteTage);
        System.out.println("wiederAusgepackt (short): " + wiederAusgepackt);
        System.out.println();

        System.out.println("=== Stufe 3: Integer / Long Demo ===");
        Integer i1 = 42;      // Auto-Boxing int -> Integer
        int i2 = i1;          // Auto-Unboxing Integer -> int
        System.out.println("Integer i1 = " + i1 + ", int i2 = " + i2);

        // Long l1 = 42;  // würde nicht kompilieren: 42 ist int-Literal
        Long l1 = (long) 42;  // expliziter Cast auf long, dann Auto-Boxing zu Long
        System.out.println("Long l1 = " + l1);

        System.out.println();
        System.out.println("=== Ende der Demo ===");
    }

    // -------------------------------------------------------------
    // Hilfsmethoden für typabhängige Logik
    // -------------------------------------------------------------

    public static void nimmPkw(Pkw pkw) {
        System.out.println("-> nimmPkw() aufgerufen für: " + pkw.getKennzeichen());
    }

    public static void nimmLkw(Lkw lkw) {
        System.out.println("-> nimmLkw() aufgerufen für: " + lkw.getKennzeichen());
    }

    /**
     * Berechnet den Mietpreis.
     *
     * @param preisProTag Tagessatz in EUR
     * @param tage        Anzahl der Tage (als short, bewusst kleinerer Typ)
     */
    public static double berechneMietpreis(double preisProTag, short tage) {
        return preisProTag * tage;
    }
}
