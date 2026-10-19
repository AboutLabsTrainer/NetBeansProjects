package de.ibb.setsmapschallenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Level 2 – Fortgeschritten-Aufgaben
 * Basierend auf der Mini-Challenge aus "Tag 2: Sets & Maps – Keine Duplikate"
 */
public class Level2Fortgeschritten {

    /**
     * Aufgabe 1: Student-Klasse mit korrektem equals/hashCode
     * Demonstriert, dass ein Set Duplikate verhindert, wenn equals/hashCode
     * korrekt implementiert sind.
     */
    public static void runAufgabe1() {
        System.out.println("\n==== LEVEL 2 – AUFGABE 1: Student mit equals/hashCode ====");
        System.out.println();

        // Set mit Student-Objekten erstellen
        Set<Student> studenten = new HashSet<>();

        // Erste Student-Objekte hinzufügen
        Student student1 = new Student("12345", "Max Müller", 22);
        Student student2 = new Student("67890", "Anna Schmidt", 21);
        Student student3 = new Student("11111", "Tom Weber", 23);

        studenten.add(student1);
        studenten.add(student2);
        studenten.add(student3);

        System.out.println("Nach dem Hinzufügen von 3 verschiedenen Studenten:");
        System.out.println("Anzahl im Set: " + studenten.size());
        System.out.println("Studenten:");
        for (Student s : studenten) {
            System.out.println("  - " + s);
        }
        System.out.println();

        // Duplikat hinzufügen (gleiche Matrikelnummer und Name)
        Student duplikat = new Student("12345", "Max Müller", 25); // Anderes Alter, aber gleiche Matrikelnummer und Name
        System.out.println("Versuche Duplikat hinzuzufügen: " + duplikat);
        boolean hinzugefuegt = studenten.add(duplikat);

        System.out.println("Wurde das Duplikat hinzugefügt? " + hinzugefuegt);
        System.out.println("Anzahl im Set nach Duplikat-Versuch: " + studenten.size());
        System.out.println();

        System.out.println("✓ Das Set hat das Duplikat automatisch verhindert,");
        System.out.println("  weil equals() und hashCode() korrekt implementiert sind!");
    }

    /**
     * Aufgabe 2: Worthäufigkeit in einem Text zählen
     * Verwendet HashMap mit merge(), um die Häufigkeit jedes Wortes zu zählen.
     */
    public static void runAufgabe2() {
        System.out.println("\n==== LEVEL 2 – AUFGABE 2: Worthäufigkeit zählen ====");
        System.out.println();

        // Beispieltext (könnte auch von der Konsole eingelesen werden)
        String text = "Java ist eine Programmiersprache. Java ist objektorientiert. " +
                      "Java ist plattformunabhängig. Programmieren mit Java macht Spaß.";

        System.out.println("Text:");
        System.out.println(text);
        System.out.println();

        // Text in Wörter zerlegen (einfach: nach Leerzeichen und Satzzeichen)
        String[] woerter = text.toLowerCase()
                .replaceAll("[.,!?;:]", "")
                .split("\\s+");

        // HashMap für Worthäufigkeiten
        Map<String, Integer> worthaeufigkeit = new HashMap<>();

        // Jedes Wort zählen mit merge() (elegant!)
        for (String wort : woerter) {
            worthaeufigkeit.merge(wort, 1, Integer::sum);
        }

        System.out.println("Worthäufigkeiten:");
        System.out.println("----------------");

        // Ergebnisse sortiert ausgeben (nach Häufigkeit, dann alphabetisch)
        worthaeufigkeit.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry -> System.out.println(entry.getKey() + " → " + entry.getValue()));

        System.out.println();
        System.out.println("Gesamtanzahl verschiedener Wörter: " + worthaeufigkeit.size());
        System.out.println("Gesamtanzahl aller Wörter: " + woerter.length);
    }
}
