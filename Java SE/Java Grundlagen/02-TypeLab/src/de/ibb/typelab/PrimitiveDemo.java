package de.ibb.typelab;

/**
 * Demo-Klasse für primitive Datentypen (Werttypen).
 *
 * Hier werden alle acht primitiven Datentypen verwendet und gezeigt,
 * dass bei primitiven Typen der WERT kopiert wird (Wertsemantik).
 */
public class PrimitiveDemo {

    public static void main(String[] args) {

        // ------------------------------------------------------------
        // 1. Primitive Datentypen deklarieren und initialisieren
        // ------------------------------------------------------------

        // byte: sehr kleine Ganzzahl (-128 bis 127)
        byte childAge = 8;

        // short: kleine Ganzzahl
        short busSeats = 52;

        // int: Standard-Ganzzahl
        int cityPopulation = 350_000;

        // long: große Ganzzahl (L am Ende!)
        long distanceToMoonKm = 384_400L;

        // float: Kommazahl mit einfacher Genauigkeit (f am Ende!)
        float outsideTemperature = 18.5f;

        // double: Kommazahl mit doppelter Genauigkeit
        double accountBalance = 1_234.56;

        // char: einzelnes Unicode-Zeichen
        char grade = 'A';

        // boolean: Wahrheitswert (true / false)
        boolean isJavaFun = true;

        System.out.println("=== Primitive Datentypen ===");
        System.out.println("Alter des Kindes:           " + childAge);
        System.out.println("Sitzplätze im Bus:          " + busSeats);
        System.out.println("Einwohnerzahl der Stadt:    " + cityPopulation);
        System.out.println("Entfernung zum Mond (km):   " + distanceToMoonKm);
        System.out.println("Außentemperatur (°C):       " + outsideTemperature);
        System.out.println("Kontostand (EUR):           " + accountBalance);
        System.out.println("Schulnote:                  " + grade);
        System.out.println("Java macht Spaß:            " + isJavaFun);

        // ------------------------------------------------------------
        // 2. Wertsemantik: Kopieren von primitiven Typen
        // ------------------------------------------------------------

        int cityPopulationCopy = cityPopulation; // Kopie des WERTES
        System.out.println();
        System.out.println("=== Werttypen: Kopie testen ===");
        System.out.println("Original vor Änderung:  " + cityPopulation);
        System.out.println("Kopie vor Änderung:      " + cityPopulationCopy);

        // wir ändern nur das Original
        cityPopulation = cityPopulation + 1_000;

        System.out.println("Original nach Änderung: " + cityPopulation);
        System.out.println("Kopie nach Änderung:     " + cityPopulationCopy);

        // Kommentar:
        // cityPopulationCopy bleibt unverändert, weil bei primitiven Typen
        // der Wert kopiert wird. Die Variablen sind völlig unabhängig.

        // ------------------------------------------------------------
        // 3. Konstante mit final
        // ------------------------------------------------------------

        final double PI = 3.14159;   // Konstante (Großschreibung + final)
        double radius = 5.0;
        double circumference = 2 * PI * radius;

        System.out.println();
        System.out.println("=== Kreisberechnung mit Konstante ===");
        System.out.println("Radius:       " + radius);
        System.out.println("Umfang (2πr): " + circumference);

        // Wenn man hier z.B. PI = 3.0; schreiben würde, meldet der Compiler
        // einen Fehler, weil final-Variablen nach der Initialisierung
        // nicht mehr verändert werden dürfen.
    }
}
