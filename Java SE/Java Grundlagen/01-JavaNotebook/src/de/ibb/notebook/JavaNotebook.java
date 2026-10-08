package de.ibb.notebook;

/**
 * Mein Java-Notizbuch.
 *
 * - Java ist plattformunabhängig: Programme laufen mit einer passenden JVM
 *   auf Windows, Linux, macOS usw.
 * - Für die Entwicklung benötigt man ein JDK (enthält Compiler, Bibliotheken
 *   und eine JVM).
 * - Java ist objektorientiert: Klassen und Objekte stehen im Mittelpunkt.
 * - Ein minimales Java-Programm besteht aus einer öffentlichen Klasse, deren
 *   Name der .java-Datei entspricht, und einer main-Methode.
 */
public class JavaNotebook {

    public static void main(String[] args) {

        // --- Variablen und Datentypen ---------------------------------------

        // primitive Datentypen
        byte age = 30;                  // kleines Alter
        int currentYear = 2025;         // Jahrzahl
        double accountBalance = 1234.56; // Kontostand
        char initial = 'P';             // Anfangsbuchstabe
        boolean isJavaFun = true;       // Ja/Nein-Information

        // Referenztyp (String)
        String name = "Petra";

        // Konsolenausgaben
        System.out.println("=== Persönliche Daten ===");
        System.out.println("Name: " + name);
        System.out.println("Alter: " + age);
        System.out.println("Anfangsbuchstabe: " + initial);
        System.out.println("Aktuelles Jahr: " + currentYear);
        System.out.println("Kontostand: " + accountBalance);
        System.out.println("Java macht Spaß: " + isJavaFun);

        // --- Type Casting ---------------------------------------------------

        double price = 19.99;
        int roundedPrice = (int) price; // explizites Casting von double nach int
        // Beim Cast gehen die Nachkommastellen verloren, es wird einfach abgeschnitten.

        System.out.println();
        System.out.println("=== Type Casting ===");
        System.out.println("Preis als double: " + price);
        System.out.println("Preis als int   : " + roundedPrice);

        // --- var-Beispiel (lokale Typinferenz, ab Java 10) -----------------

        var message = "Hallo Java mit var!"; // Compiler leitet hier den Typ String ab

        System.out.println();
        System.out.println("=== var-Beispiel ===");
        System.out.println(message);

        // --- Zusatzaufgabe: Kreisumfang mit Konstante berechnen ------------

        final double PI = 3.14159; // Konstante (Großbuchstaben, final)
        double radius = 5.0;
        double circumference = 2 * PI * radius;

        System.out.println();
        System.out.println("=== Kreisberechnung ===");
        System.out.println("Radius: " + radius);
        System.out.println("Umfang: " + circumference);
    }
}
