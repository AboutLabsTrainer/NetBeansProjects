package de.ibb.setsmapschallenge;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Level 3 – Profi-Aufgaben
 * Basierend auf der Mini-Challenge aus "Tag 2: Sets & Maps – Keine Duplikate"
 */
public class Level3Profi {

    /**
     * Aufgabe 1: LRU-Cache mit LinkedHashMap
     * Demonstriert einen Least-Recently-Used Cache, der automatisch
     * den ältesten Eintrag entfernt, wenn die Kapazität überschritten wird.
     */
    public static void runAufgabe1() {
        System.out.println("\n==== LEVEL 3 – AUFGABE 1: LRU-Cache mit LinkedHashMap ====");
        System.out.println();

        // LRU-Cache mit maximal 3 Einträgen erstellen
        LruCache<String, String> cache = new LruCache<>(3);

        System.out.println("LRU-Cache mit max. Kapazität: 3");
        System.out.println();

        // Einträge hinzufügen
        System.out.println("Füge Einträge hinzu:");
        cache.put("1", "Eins");
        System.out.println("  Nach '1' → 'Eins': " + cache);
        cache.put("2", "Zwei");
        System.out.println("  Nach '2' → 'Zwei': " + cache);
        cache.put("3", "Drei");
        System.out.println("  Nach '3' → 'Drei': " + cache);
        System.out.println();

        // Jetzt sollte der älteste Eintrag entfernt werden
        System.out.println("Füge '4' → 'Vier' hinzu (Kapazität überschritten):");
        cache.put("4", "Vier");
        System.out.println("  Cache: " + cache);
        System.out.println("  ✓ Der älteste Eintrag ('1') wurde automatisch entfernt!");
        System.out.println();

        // Zugriff auf ein Element ändert die Reihenfolge (LRU-Prinzip)
        System.out.println("Zugriff auf '2' (macht es zum neuesten Element):");
        cache.get("2");
        System.out.println("  Cache: " + cache);
        System.out.println();

        System.out.println("Füge '5' → 'Fünf' hinzu:");
        cache.put("5", "Fünf");
        System.out.println("  Cache: " + cache);
        System.out.println("  ✓ Jetzt wurde '3' entfernt (nicht '2', weil '2' zuletzt verwendet wurde)!");
    }

    /**
     * Aufgabe 2: TreeSet mit Comparator für Personen
     * Sortierung: erst nach Alter, dann nach Name (Vorname + Nachname).
     */
    public static void runAufgabe2() {
        System.out.println("\n==== LEVEL 3 – AUFGABE 2: TreeSet mit Comparator ====");
        System.out.println();

        // Comparator erstellen: erst nach Alter, dann nach Nachname, dann nach Vorname
        Comparator<Person> personComparator = Comparator
                .comparing(Person::getAlter)           // Erst nach Alter
                .thenComparing(Person::getNachname)    // Dann nach Nachname
                .thenComparing(Person::getVorname);      // Dann nach Vorname

        // TreeSet mit Comparator erstellen
        TreeSet<Person> personen = new TreeSet<>(personComparator);

        // Personen hinzufügen
        personen.add(new Person("Max", "Müller", 25));
        personen.add(new Person("Anna", "Schmidt", 22));
        personen.add(new Person("Tom", "Müller", 25));  // Gleiches Alter wie Max Müller
        personen.add(new Person("Lisa", "Weber", 30));
        personen.add(new Person("Peter", "Schmidt", 22)); // Gleiches Alter wie Anna Schmidt

        System.out.println("Personen im TreeSet (sortiert: erst Alter, dann Nachname, dann Vorname):");
        System.out.println("------------------------------------------------------------------------");

        for (Person p : personen) {
            System.out.println("  - " + p);
        }

        System.out.println();
        System.out.println("✓ Die Personen sind automatisch sortiert:");
        System.out.println("  1. Zuerst nach Alter (22, 25, 30)");
        System.out.println("  2. Bei gleichem Alter nach Nachname (alphabetisch)");
        System.out.println("  3. Bei gleichem Nachname nach Vorname (alphabetisch)");
    }
}
