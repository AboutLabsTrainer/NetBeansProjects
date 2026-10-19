package de.ibb.typelab;

import java.awt.Point;

/**
 * Demo-Klasse für Referenztypen.
 *
 * Zeigt den Unterschied zwischen primitiven Typen (Wertkopie)
 * und Referenztypen (Referenzkopie auf dasselbe Objekt).
 */
public class ReferenceDemo {

    public static void main(String[] args) {

        // ------------------------------------------------------------
        // 1. String-Beispiel (Referenztyp, aber immutable)
        // ------------------------------------------------------------

        String greeting = "Hallo Java!";
        String alias = greeting; // alias erhält dieselbe Referenz

        System.out.println("=== Strings (Referenztyp, immutable) ===");
        System.out.println("greeting vor Änderung: " + greeting);
        System.out.println("alias    vor Änderung: " + alias);

        // Wir WEISEN greeting einen NEUEN String zu.
        // Das alte String-Objekt bleibt unverändert, alias zeigt weiterhin darauf.
        greeting = "Hallo Java, schön dich zu sehen!";

        System.out.println("greeting nach Änderung: " + greeting);
        System.out.println("alias    nach Änderung: " + alias);

        // Kommentar:
        // String ist ein Referenztyp, aber unveränderlich (immutable).
        // Durch die Zuweisung greeting = "..." zeigen greeting und alias
        // nicht mehr auf dasselbe Objekt. alias behält den alten String.

        // ------------------------------------------------------------
        // 2. Point-Beispiel (typischer Referenztyp, veränderlich)
        // ------------------------------------------------------------

        Point p1 = new Point(10, 20); // neues Point-Objekt
        Point p2 = p1;                // p2 zeigt auf dasselbe Objekt wie p1

        System.out.println();
        System.out.println("=== Points (Referenztyp, veränderlich) ===");
        System.out.println("p1 vor Änderung: " + p1);
        System.out.println("p2 vor Änderung: " + p2);

        // Wir verändern das Objekt über p1
        p1.x = 99;

        System.out.println("p1 nach Änderung: " + p1);
        System.out.println("p2 nach Änderung: " + p2);

        // Kommentar:
        // Beide Referenzen p1 und p2 zeigen auf dasselbe Point-Objekt.
        // Wenn wir eine Koordinate über p1 ändern, sehen wir die Änderung
        // auch über p2. Das ist der typische Unterschied zu Werttypen:
        // Es wird NICHT der Wert kopiert, sondern die Referenz auf das Objekt.
    }
}
