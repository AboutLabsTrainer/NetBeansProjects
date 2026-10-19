package de.ibb.streams;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream API Challenges - Alle drei Levels
 */
public class StreamChallenges {
    
    public static void main(String[] args) {
        System.out.println("=== Stream API Challenges ===\n");
        
        // Level 1 - Einsteiger
        System.out.println("🟢 LEVEL 1 - EINSTEIGER");
        System.out.println("==================================================");
        challenge1_1();
        challenge1_2();
        challenge1_3();
        
        System.out.println("\n🟡 LEVEL 2 - FORTGESCHRITTEN");
        System.out.println("==================================================");
        challenge2_1();
        challenge2_2();
        challenge2_3();
        
        System.out.println("\n🔵 LEVEL 3 - PROFI");
        System.out.println("==================================================");
        challenge3_1();
        challenge3_2();
        challenge3_3();
    }
    
    // ==================== LEVEL 1 - EINSTEIGER ====================
    
    /**
     * Challenge 1.1: Filtere alle Zahlen > 5 und verdopple sie
     */
    private static void challenge1_1() {
        System.out.println("\n📌 Challenge 1.1: Filtere alle Zahlen > 5 und verdopple sie");
        List<Integer> zahlen = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Eingabe: " + zahlen);
        
        List<Integer> ergebnis = zahlen.stream()
                .filter(n -> n > 5)
                .map(n -> n * 2)
                .toList();
        
        System.out.println("Ergebnis: " + ergebnis);
        System.out.println("✅ [12, 14, 16, 18, 20]");
    }
    
    /**
     * Challenge 1.2: Finde den längsten Namen in einer Liste
     */
    private static void challenge1_2() {
        System.out.println("\n📌 Challenge 1.2: Finde den längsten Namen in einer Liste");
        List<String> namen = List.of("Max", "Anna", "Tom", "Alexandra", "Tim", "Christoph");
        System.out.println("Eingabe: " + namen);
        
        Optional<String> laengsterName = namen.stream()
                .max(Comparator.comparing(String::length));
        
        laengsterName.ifPresentOrElse(
                name -> System.out.println("Ergebnis: " + name),
                () -> System.out.println("Keine Namen gefunden")
        );
        System.out.println("✅ Alexandra oder Christoph");
    }
    
    /**
     * Challenge 1.3: Zähle wie viele Strings mit „A" beginnen
     */
    private static void challenge1_3() {
        System.out.println("\n📌 Challenge 1.3: Zähle wie viele Strings mit 'A' beginnen");
        List<String> namen = List.of("Max", "Anna", "Tom", "Alexandra", "Tim", "Alfred");
        System.out.println("Eingabe: " + namen);
        
        long anzahl = namen.stream()
                .filter(s -> s.startsWith("A"))
                .count();
        
        System.out.println("Ergebnis: " + anzahl);
        System.out.println("✅ 3");
    }
    
    // ==================== LEVEL 2 - FORTGESCHRITTEN ====================
    
    /**
     * Challenge 2.1: Gruppiere Personen nach Alter
     */
    private static void challenge2_1() {
        System.out.println("\n📌 Challenge 2.1: Gruppiere Personen nach Alter");
        List<Person> personen = List.of(
                new Person("Max", 25, "IT"),
                new Person("Anna", 30, "HR"),
                new Person("Tom", 25, "IT"),
                new Person("Lisa", 28, "HR"),
                new Person("Peter", 30, "IT")
        );
        System.out.println("Eingabe: " + personen);
        
        Map<Integer, List<Person>> nachAlter = personen.stream()
                .collect(Collectors.groupingBy(Person::getAlter));
        
        System.out.println("Ergebnis:");
        nachAlter.forEach((alter, personenListe) -> 
                System.out.println("  Alter " + alter + ": " + personenListe));
        System.out.println("✅ {25=[Max, Tom], 28=[Lisa], 30=[Anna, Peter]}");
    }
    
    /**
     * Challenge 2.2: Berechne Durchschnittsalter pro Abteilung
     */
    private static void challenge2_2() {
        System.out.println("\n📌 Challenge 2.2: Berechne Durchschnittsalter pro Abteilung");
        List<Person> personen = List.of(
                new Person("Max", 25, "IT"),
                new Person("Anna", 30, "HR"),
                new Person("Tom", 25, "IT"),
                new Person("Lisa", 28, "HR")
        );
        System.out.println("Eingabe: " + personen);
        
        Map<String, Double> durchschnittProAbteilung = personen.stream()
                .collect(Collectors.groupingBy(
                        Person::getAbteilung,
                        Collectors.averagingInt(Person::getAlter)
                ));
        
        System.out.println("Ergebnis:");
        durchschnittProAbteilung.forEach((abteilung, durchschnitt) -> 
                System.out.println("  " + abteilung + ": " + String.format("%.1f", durchschnitt)));
        System.out.println("✅ IT: 25.0, HR: 29.0");
    }
    
    /**
     * Challenge 2.3: Baue einen CSV-String aus einer Liste von Objekten
     */
    private static void challenge2_3() {
        System.out.println("\n📌 Challenge 2.3: Baue einen CSV-String aus einer Liste von Objekten");
        List<Person> personen = List.of(
                new Person("Max", 25, "IT"),
                new Person("Anna", 30, "HR"),
                new Person("Tom", 25, "IT")
        );
        System.out.println("Eingabe: " + personen);
        
        String csv = personen.stream()
                .map(p -> p.getName() + "," + p.getAlter() + "," + p.getAbteilung())
                .collect(Collectors.joining("\n"));
        
        System.out.println("Ergebnis:");
        System.out.println(csv);
        System.out.println("✅ CSV-Format mit Name,Alter,Abteilung");
    }
    
    // ==================== LEVEL 3 - PROFI ====================
    
    /**
     * Challenge 3.1: Implementiere Pagination mit skip/limit
     */
    private static void challenge3_1() {
        System.out.println("\n📌 Challenge 3.1: Implementiere Pagination mit skip/limit");
        List<Integer> zahlen = IntStream.rangeClosed(1, 20)
                .boxed()
                .toList();
        System.out.println("Eingabe: " + zahlen.size() + " Elemente");
        
        int seite = 2; // Seite 2 (0-basiert wäre Seite 1)
        int seitenGroesse = 5;
        
        List<Integer> seite2 = zahlen.stream()
                .skip((seite - 1) * seitenGroesse)
                .limit(seitenGroesse)
                .toList();
        
        System.out.println("Seite " + seite + " (Seitengröße: " + seitenGroesse + "): " + seite2);
        System.out.println("✅ [6, 7, 8, 9, 10]");
    }
    
    /**
     * Challenge 3.2: Schreibe einen Custom Collector (Oxford Comma Collector)
     */
    private static void challenge3_2() {
        System.out.println("\n📌 Challenge 3.2: Schreibe einen Custom Collector (Oxford Comma)");
        List<String> namen = List.of("Max", "Anna", "Tom");
        System.out.println("Eingabe: " + namen);
        
        // Custom Collector: [A, B, C] -> "A, B und C"
        Collector<String, ArrayList<String>, String> oxfordComma = Collector.of(
                ArrayList<String>::new, // Supplier
                ArrayList<String>::add, // Accumulator
                (left, right) -> { // Combiner
                    left.addAll(right);
                    return left;
                },
                list -> { // Finisher
                    if (list.isEmpty()) return "";
                    if (list.size() == 1) return list.get(0);
                    List<String> sublist = list.subList(0, list.size() - 1);
                    return String.join(", ", sublist)
                            + " und " + list.get(list.size() - 1);
                }
        );
        
        String result = namen.stream()
                .collect(oxfordComma);
        
        System.out.println("Ergebnis: " + result);
        System.out.println("✅ 'Max, Anna und Tom'");
    }
    
    /**
     * Challenge 3.3: Vergleiche Performance: Stream vs. for vs. parallelStream
     */
    private static void challenge3_3() {
        System.out.println("\n📌 Challenge 3.3: Vergleiche Performance: Stream vs. for vs. parallelStream");
        
        // Große Datenmenge für aussagekräftigen Vergleich
        int groesse = 1_000_000;
        List<Integer> zahlen = new ArrayList<>();
        for (int i = 0; i < groesse; i++) {
            zahlen.add(i);
        }
        
        // Test: Summe aller geraden Zahlen
        System.out.println("Test: Summe aller geraden Zahlen aus " + groesse + " Elementen");
        
        // 1. For-Schleife
        long startFor = System.currentTimeMillis();
        long summeFor = 0;
        for (Integer zahl : zahlen) {
            if (zahl % 2 == 0) {
                summeFor += zahl;
            }
        }
        long zeitFor = System.currentTimeMillis() - startFor;
        
        // 2. Stream
        long startStream = System.currentTimeMillis();
        long summeStream = zahlen.stream()
                .filter(n -> n % 2 == 0)
                .mapToLong(Integer::longValue)
                .sum();
        long zeitStream = System.currentTimeMillis() - startStream;
        
        // 3. Parallel Stream
        long startParallel = System.currentTimeMillis();
        long summeParallel = zahlen.parallelStream()
                .filter(n -> n % 2 == 0)
                .mapToLong(Integer::longValue)
                .sum();
        long zeitParallel = System.currentTimeMillis() - startParallel;
        
        System.out.println("Ergebnisse (alle sollten gleich sein):");
        System.out.println("  For-Schleife:     " + summeFor + " (" + zeitFor + " ms)");
        System.out.println("  Stream:           " + summeStream + " (" + zeitStream + " ms)");
        System.out.println("  Parallel Stream:  " + summeParallel + " (" + zeitParallel + " ms)");
        System.out.println("\n✅ Performance-Vergleich durchgeführt");
        System.out.println("   (Hinweis: Bei kleinen Datenmengen ist der Overhead größer als der Gewinn)");
    }
}
