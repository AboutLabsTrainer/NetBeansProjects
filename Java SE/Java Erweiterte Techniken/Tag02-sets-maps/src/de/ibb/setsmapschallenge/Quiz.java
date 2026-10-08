package de.ibb.setsmapschallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Quiz-Teil der Mini-Challenge
 * Enthält die 5 Quiz-Fragen aus "Tag 2: Sets & Maps – Keine Duplikate"
 * mit interaktiver Beantwortung und Lösungen.
 */
public class Quiz {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Führt alle Quiz-Fragen durch.
     */
    public static void runQuiz() {
        System.out.println("\n==== QUIZ: Sets & Maps ====");
        System.out.println();

        frage1();
        warteAufEingabe();

        frage2();
        warteAufEingabe();

        frage3();
        warteAufEingabe();

        frage4();
        warteAufEingabe();

        frage5();
        warteAufEingabe();

        System.out.println("\n✓ Quiz abgeschlossen!");
    }

    /**
     * Quiz-Frage 1: Set<Person> ohne equals/hashCode
     * Basierend auf Quiz-Frage 1 aus dem Artikel.
     */
    private static void frage1() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("FRAGE 1:");
        System.out.println();
        System.out.println("Was gibt personen.size() aus, wenn Person KEIN equals/hashCode hat?");
        System.out.println();
        System.out.println("Code:");
        System.out.println("  Set<Person> personen = new HashSet<>();");
        System.out.println("  personen.add(new Person(\"Max\", \"Müller\"));");
        System.out.println("  personen.add(new Person(\"Max\", \"Müller\"));");
        System.out.println("  System.out.println(personen.size());");
        System.out.println();
        System.out.print("Deine Antwort (Zahl): ");

        String antwort = scanner.nextLine().trim();

        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("LÖSUNG:");
        System.out.println();
        System.out.println("Antwort: 2");
        System.out.println();
        System.out.println("Erklärung:");
        System.out.println("Ohne equals/hashCode-Override verwendet Java die Standardimplementierung");
        System.out.println("von Object: Zwei Objekte sind nur gleich, wenn sie dieselbe Referenz");
        System.out.println("haben. Da new Person() zwei verschiedene Objekte erstellt, werden sie");
        System.out.println("als unterschiedlich behandelt – das Set erkennt das Duplikat nicht!");
    }

    /**
     * Quiz-Frage 2: Welches Set behält die Sortierung?
     * Basierend auf Quiz-Frage 2 aus dem Artikel.
     */
    private static void frage2() {
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("FRAGE 2:");
        System.out.println();
        System.out.println("Welches Set behält die Sortierung:");
        System.out.println("  a) HashSet");
        System.out.println("  b) LinkedHashSet");
        System.out.println("  c) TreeSet");
        System.out.println();
        System.out.print("Deine Antwort (a/b/c): ");

        String antwort = scanner.nextLine().trim().toLowerCase();

        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("LÖSUNG:");
        System.out.println();
        System.out.println("Antwort: c) TreeSet");
        System.out.println();
        System.out.println("Erklärung:");
        System.out.println("  • HashSet: Keine garantierte Reihenfolge");
        System.out.println("  • LinkedHashSet: Behält die Einfüge-Reihenfolge");
        System.out.println("  • TreeSet: Automatisch sortiert (nach natürlicher Ordnung oder Comparator)");
        System.out.println();
        System.out.println("TreeSet ist die richtige Antwort, weil es die Elemente automatisch");
        System.out.println("sortiert hält (nicht nur die Einfüge-Reihenfolge wie LinkedHashSet).");
    }

    /**
     * Quiz-Frage 3: Map mit verändertem Key
     * Basierend auf Quiz-Frage 3 aus dem Artikel.
     */
    private static void frage3() {
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("FRAGE 3:");
        System.out.println();
        System.out.println("Was gibt dieser Code aus?");
        System.out.println();
        System.out.println("  List<String> key = new ArrayList<>();");
        System.out.println("  key.add(\"A\");");
        System.out.println("  Map<List<String>, String> map = new HashMap<>();");
        System.out.println("  map.put(key, \"Wert\");");
        System.out.println("  key.add(\"B\");");
        System.out.println("  System.out.println(map.get(key));");
        System.out.println();
        System.out.print("Deine Antwort: ");

        String antwort = scanner.nextLine().trim();

        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("LÖSUNG:");
        System.out.println();
        System.out.println("Antwort: null");
        System.out.println();
        System.out.println("Erklärung:");
        System.out.println("Wenn du den Key nach dem put() veränderst, ändert sich sein hashCode.");
        System.out.println("Die Map findet den Eintrag nicht mehr, weil sie im falschen Bucket");
        System.out.println("sucht. Der Wert ist praktisch 'verloren'.");
        System.out.println();
        System.out.println("⚠️ WICHTIG: Verwende nur immutable Keys (String, Integer, etc.)");
        System.out.println("   oder ändere Keys nie nach dem Einfügen in die Map!");
    }

    /**
     * Quiz-Frage 4: Zeitkomplexität von get()
     * Basierend auf Quiz-Frage 4 aus dem Artikel.
     */
    private static void frage4() {
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("FRAGE 4:");
        System.out.println();
        System.out.println("Was ist die Zeitkomplexität von get() bei:");
        System.out.println("  a) HashMap: O(1), TreeMap: O(1)");
        System.out.println("  b) HashMap: O(1), TreeMap: O(log n)");
        System.out.println("  c) HashMap: O(log n), TreeMap: O(log n)");
        System.out.println();
        System.out.print("Deine Antwort (a/b/c): ");

        String antwort = scanner.nextLine().trim().toLowerCase();

        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("LÖSUNG:");
        System.out.println();
        System.out.println("Antwort: b) HashMap: O(1), TreeMap: O(log n)");
        System.out.println();
        System.out.println("Erklärung:");
        System.out.println("  • HashMap: Verwendet Hash-Tabelle → O(1) durchschnittlich");
        System.out.println("  • TreeMap: Verwendet rot-schwarzen Baum → O(log n)");
        System.out.println();
        System.out.println("HashMap ist schneller für Zugriffe, aber TreeMap bietet");
        System.out.println("sortierte Keys und Range-Queries (subMap, headMap, etc.).");
    }

    /**
     * Quiz-Frage 5: equals und hashCode Beziehung
     * Basierend auf Quiz-Frage 5 aus dem Artikel.
     */
    private static void frage5() {
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("FRAGE 5:");
        System.out.println();
        System.out.println("Müssen zwei Objekte mit gleichem hashCode auch equals sein?");
        System.out.println("  a) Ja, immer");
        System.out.println("  b) Nein, aber wenn equals true ist, MÜSSEN die hashCodes gleich sein");
        System.out.println("  c) Nein, hashCode und equals haben nichts miteinander zu tun");
        System.out.println();
        System.out.print("Deine Antwort (a/b/c): ");

        String antwort = scanner.nextLine().trim().toLowerCase();

        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("LÖSUNG:");
        System.out.println();
        System.out.println("Antwort: b) Nein, aber wenn equals true ist, MÜSSEN die hashCodes gleich sein");
        System.out.println();
        System.out.println("Erklärung:");
        System.out.println("Der equals/hashCode-Vertrag besagt:");
        System.out.println("  • Wenn a.equals(b) → dann MUSS a.hashCode() == b.hashCode() sein");
        System.out.println("  • Umgekehrt: Gleiche hashCodes bedeuten NICHT automatisch equals");
        System.out.println();
        System.out.println("Beispiel: Zwei verschiedene Objekte können zufällig denselben");
        System.out.println("hashCode haben (Kollision), sind aber nicht gleich.");
        System.out.println();
        System.out.println("⚠️ Die goldene Regel: Wenn du equals() überschreibst,");
        System.out.println("   MUSST du auch hashCode() überschreiben!");
    }

    private static void warteAufEingabe() {
        System.out.println();
        System.out.print("Drücke Enter, um zur nächsten Frage zu gelangen...");
        scanner.nextLine();
        System.out.println();
    }
}
