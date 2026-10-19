package de.ibb.setsmapschallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Level 1 – Einsteiger-Aufgaben
 * Basierend auf der Mini-Challenge aus "Tag 2: Sets & Maps – Keine Duplikate"
 */
public class Level1Einsteiger {

    /**
     * Aufgabe 1: HashSet mit Städtenamen
     * Erstellt ein HashSet mit 5 Städtenamen (inkl. "Berlin") und prüft,
     * ob "Berlin" enthalten ist.
     */
    public static void runAufgabe1() {
        System.out.println("\n==== LEVEL 1 – AUFGABE 1: HashSet mit Städten ====");
        System.out.println();

        // HashSet mit Städtenamen erstellen
        Set<String> staedte = new HashSet<>();
        staedte.add("Berlin");
        staedte.add("München");
        staedte.add("Hamburg");
        staedte.add("Köln");
        staedte.add("Frankfurt");

        System.out.println("HashSet mit Städten:");
        System.out.println(staedte);
        System.out.println();

        // Prüfen, ob "Berlin" enthalten ist
        boolean enthaeltBerlin = staedte.contains("Berlin");
        System.out.println("Enthält das Set 'Berlin'? " + enthaeltBerlin);
        System.out.println("Anzahl der Städte: " + staedte.size());
    }

    /**
     * Aufgabe 2: HashMap Länder → Hauptstädte
     * Erstellt eine HashMap, die Ländernamen auf ihre Hauptstädte mappt
     * und gibt alle Einträge aus.
     */
    public static void runAufgabe2() {
        System.out.println("\n==== LEVEL 1 – AUFGABE 2: HashMap Länder → Hauptstädte ====");
        System.out.println();

        // HashMap mit Ländern und Hauptstädten
        Map<String, String> laenderZuHauptstadt = new HashMap<>();
        laenderZuHauptstadt.put("Deutschland", "Berlin");
        laenderZuHauptstadt.put("Frankreich", "Paris");
        laenderZuHauptstadt.put("Italien", "Rom");
        laenderZuHauptstadt.put("Spanien", "Madrid");
        laenderZuHauptstadt.put("Österreich", "Wien");
        laenderZuHauptstadt.put("Schweiz", "Bern");

        System.out.println("Länder und ihre Hauptstädte:");
        System.out.println("----------------------------");

        // Iteration über entrySet (effizienter als keySet + get)
        for (Map.Entry<String, String> entry : laenderZuHauptstadt.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        System.out.println();
        System.out.println("Anzahl der Einträge: " + laenderZuHauptstadt.size());
    }
}
