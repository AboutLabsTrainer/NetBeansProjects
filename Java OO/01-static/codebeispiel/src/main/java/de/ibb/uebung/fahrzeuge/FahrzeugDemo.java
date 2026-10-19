package de.ibb.uebung.fahrzeuge;

/**
 * Demo-Klasse für die Übung "UebungFahrzeugeStatic".
 *
 * Zeigt:
 *  - Zugriff auf statische Attribute und Methoden
 *  - Unterschied instanzbezogene / statische Methoden
 */
public class FahrzeugDemo {

    public static void main(String[] args) {

        System.out.println("=== Start: Noch keine Fahrzeuge erzeugt ===");
        System.out.println("Anzahl Fahrzeuge (statisch): " + Fahrzeug.getAnzahlFahrzeuge());
        System.out.println();

        // Zwei Fahrzeug-Objekte erzeugen
        Fahrzeug f1 = new Fahrzeug("B-AB 1234", 2015, 49.99);
        Fahrzeug f2 = new Fahrzeug("B-CD 4321", 2020, 59.99);

        System.out.println("=== Nach Erzeugung von f1 und f2 ===");
        System.out.println("Anzahl Fahrzeuge (statisch): " + Fahrzeug.getAnzahlFahrzeuge());
        System.out.println("Maximal erlaubte Fahrzeuge:  " + Fahrzeug.MAX_FAHRZEUGE);
        System.out.println();

        System.out.println("=== Einzelfahrzeuge (Instanzdaten) ===");
        f1.druckeDaten();
        System.out.println();
        f2.druckeDaten();
        System.out.println();

        int tage = 3;
        System.out.println("=== Mietpreis über Instanzmethoden ===");
        double bruttoF1 = f1.berechneMietpreisBrutto(tage);
        double bruttoF2 = f2.berechneMietpreisBrutto(tage);
        System.out.printf("Bruttomietpreis für %s (%d Tage): %.2f EUR%n",
                f1.getKennzeichen(), tage, bruttoF1);
        System.out.printf("Bruttomietpreis für %s (%d Tage): %.2f EUR%n",
                f2.getKennzeichen(), tage, bruttoF2);
        System.out.println();

        System.out.println("=== Mietpreis über statische Methode ===");
        double bruttoOhneObjekt = Fahrzeug.berechneMietpreisBrutto(39.99, 5);
        System.out.printf("Bruttomietpreis (ohne konkretes Objekt, 39.99 EUR/Tag, 5 Tage): %.2f EUR%n",
                bruttoOhneObjekt);
        System.out.println();

        System.out.println("=== Statische Statistik ===");
        Fahrzeug.druckeStatistik();
        System.out.println();

        System.out.println("=== Teil 3: Vermietungsstatus-Tracking ===");
        System.out.println("Anzahl vermietet (vor Vermietungen): " + Fahrzeug.getAnzahlVermietet());
        System.out.println();

        // Einige Fahrzeuge vermieten
        System.out.println("Vermiete f1...");
        f1.miete();
        System.out.println("Anzahl vermietet: " + Fahrzeug.getAnzahlVermietet());
        System.out.println();

        System.out.println("Vermiete f2...");
        f2.miete();
        System.out.println("Anzahl vermietet: " + Fahrzeug.getAnzahlVermietet());
        System.out.println();

        // Ein Fahrzeug zurückgeben
        System.out.println("Gebe f1 zurück...");
        f1.rueckgabe();
        System.out.println("Anzahl vermietet: " + Fahrzeug.getAnzahlVermietet());
        System.out.println();

        System.out.println("=== Finale Statistik ===");
        Fahrzeug.druckeStatistik();
    }
}