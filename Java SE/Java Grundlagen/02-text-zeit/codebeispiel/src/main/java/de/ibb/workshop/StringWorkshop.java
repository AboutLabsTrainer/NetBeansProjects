package de.ibb.workshop;

/**
 * Übungen zum Umgang mit Strings.
 *
 * Themen:
 * - Definition und Grund-Methoden von String
 * - String-Manipulation (substring, replace, contains, ...)
 * - String-Pool und Vergleich mit == vs. equals()
 */
public class StringWorkshop {

    public static void main(String[] args) {

        // ------------------------------------------------------------
        // Aufgabe 1: String-Grundlagen
        // ------------------------------------------------------------

        String fullName = "Max Mustermann";
        String motto    = "Java macht Spaß!";

        System.out.println("=== Aufgabe 1: String-Grundlagen ===");
        System.out.println("Voller Name:     " + fullName);
        System.out.println("Länge:           " + fullName.length());
        System.out.println("Großschreibung:  " + fullName.toUpperCase());
        System.out.println("Kleinschreibung: " + fullName.toLowerCase());

        // Vor- und Nachnamen per substring ermitteln
        int spaceIndex = fullName.indexOf(' ');
        String firstName = fullName.substring(0, spaceIndex);
        String lastName  = fullName.substring(spaceIndex + 1);

        System.out.println("Vorname:         " + firstName);
        System.out.println("Nachname:        " + lastName);

        // Kommentar:
        // Strings werden in Java mit doppelten Anführungszeichen definiert,
        // z.B. String text = "Hallo";  Sie sind Objekte der Klasse java.lang.String.

        // ------------------------------------------------------------
        // Aufgabe 2: String-Manipulation
        // ------------------------------------------------------------

        System.out.println();
        System.out.println("=== Aufgabe 2: String-Manipulation ===");
        System.out.println("Motto (Original): " + motto);

        String mottoWithUnderscores = motto.replace(" ", "_");
        System.out.println("Motto (mit _):    " + mottoWithUnderscores);

        boolean containsJava = motto.contains("Java");
        System.out.println("Enthält 'Java'?   " + containsJava);

        String mottoWithCSharp = motto.replace("Java", "C#");
        System.out.println("Motto (C#):       " + mottoWithCSharp);

        // Hinweis:
        // Strings sind immutable (unveränderlich). Jede "Änderung" liefert
        // ein NEUES String-Objekt, die Variable zeigt dann auf dieses neue Objekt.

        // ------------------------------------------------------------
        // Aufgabe 3: String-Pool & Vergleich
        // ------------------------------------------------------------

        System.out.println();
        System.out.println("=== Aufgabe 3: String-Pool & Vergleich ===");

        String a = "Java";
        String b = "Java";
        String c = new String("Java");

        System.out.println("a == b       : " + (a == b));
        System.out.println("a == c       : " + (a == c));
        System.out.println("a.equals(c)  : " + a.equals(c));

        // Kommentar:
        // a und b werden aus String-Literalen gebildet und landen im String-Pool.
        // Deshalb zeigen a und b auf dasselbe Objekt -> (a == b) ist true.
        // c wird mit new String(...) erzeugt, ist also ein anderes Objekt im Heap.
        // Deshalb ist (a == c) false, obwohl der Inhalt gleich ist.
        // equals() vergleicht den INHALT, daher ist a.equals(c) true.

        // ------------------------------------------------------------
        // Aufgabe 4 (optional): Scanner-Eingabe
        // ------------------------------------------------------------
        // Dieser Teil kann bei Bedarf im Unterricht aktiviert werden.
        /*
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("\nBitte einen Satz eingeben: ");
        String sentence = scanner.nextLine();

        System.out.println("Länge:            " + sentence.length());
        System.out.println("Beginnt mit \"Hallo\"? " + sentence.startsWith("Hallo"));
        System.out.println("Endet mit \"!\"?       " + sentence.endsWith("!"));
        System.out.println("Getrimmt:         \"" + sentence.trim() + "\"");

        scanner.close();
        */
    }
}
