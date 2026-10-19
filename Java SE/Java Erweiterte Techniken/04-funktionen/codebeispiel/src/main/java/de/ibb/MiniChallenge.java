package de.ibb;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Mini-Challenge Level 1: Functional Interfaces
 * 
 * Diese Klasse demonstriert die Verwendung von Functional Interfaces:
 * - Predicate, Function, Consumer, Supplier für Strings
 * - Kombination von Predicates mit and() und or()
 * - Verkettung von Functions mit andThen()
 * 
 * @author Java Developer Online - Tag 5
 */
public class MiniChallenge {
    
    public static void main(String[] args) {
        System.out.println("=== Functional Interfaces Mini-Challenge (Level 1) ===\n");
        
        // 1. Predicate, Function, Consumer, Supplier für Strings erstellen
        demonstrateBasicInterfaces();
        
        // 2. Zwei Predicates mit and() und or() kombinieren
        demonstratePredicateCombination();
        
        // 3. Zwei Functions mit andThen() verkettet
        demonstrateFunctionChaining();
        
        System.out.println("\n=== Challenge abgeschlossen! ===");
    }
    
    /**
     * Demonstriert die grundlegenden Functional Interfaces für Strings
     */
    private static void demonstrateBasicInterfaces() {
        System.out.println("1. Grundlegende Functional Interfaces für Strings:\n");
        
        // Predicate<String>: Prüft ob ein String leer ist
        Predicate<String> istLeer = s -> s.isEmpty();
        System.out.println("   Predicate<String> istLeer:");
        System.out.println("   - \"\" ist leer: " + istLeer.test(""));
        System.out.println("   - \"Hallo\" ist leer: " + istLeer.test("Hallo"));
        
        // Predicate<String>: Prüft ob ein String eine bestimmte Länge hat
        Predicate<String> istLang = s -> s.length() > 10;
        System.out.println("\n   Predicate<String> istLang (Länge > 10):");
        System.out.println("   - \"Hallo\" ist lang: " + istLang.test("Hallo"));
        System.out.println("   - \"Hallo Welt, das ist ein langer Text\" ist lang: " + 
                          istLang.test("Hallo Welt, das ist ein langer Text"));
        
        // Function<String, Integer>: Transformiert String zu Integer (Länge)
        Function<String, Integer> laenge = s -> s.length();
        System.out.println("\n   Function<String, Integer> laenge:");
        System.out.println("   - Länge von \"Hallo\": " + laenge.apply("Hallo"));
        System.out.println("   - Länge von \"Java\": " + laenge.apply("Java"));
        
        // Function<String, String>: Transformiert String zu String (toUpperCase)
        Function<String, String> toUpper = s -> s.toUpperCase();
        System.out.println("\n   Function<String, String> toUpper:");
        System.out.println("   - \"hallo\" -> " + toUpper.apply("hallo"));
        System.out.println("   - \"welt\" -> " + toUpper.apply("welt"));
        
        // Consumer<String>: Verarbeitet String ohne Rückgabe
        Consumer<String> drucker = s -> System.out.println("   [Consumer] Ausgabe: " + s);
        System.out.println("\n   Consumer<String> drucker:");
        drucker.accept("Hallo Welt");
        drucker.accept("Functional Interfaces");
        
        // Supplier<String>: Erzeugt String-Werte
        Supplier<String> helloSupplier = () -> "Hello World";
        System.out.println("\n   Supplier<String> helloSupplier:");
        System.out.println("   - Wert: " + helloSupplier.get());
        
        Supplier<String> zufallsString = () -> "Zufälliger String " + Math.random();
        System.out.println("   Supplier<String> zufallsString:");
        System.out.println("   - Wert 1: " + zufallsString.get());
        System.out.println("   - Wert 2: " + zufallsString.get());
        
        System.out.println();
    }
    
    /**
     * Demonstriert die Kombination von zwei Predicates mit and() und or()
     */
    private static void demonstratePredicateCombination() {
        System.out.println("2. Kombination von Predicates mit and() und or():\n");
        
        // Erstelle zwei Predicates
        Predicate<String> istLeer = s -> s.isEmpty();
        Predicate<String> istLang = s -> s.length() > 10;
        
        System.out.println("   Ausgangs-Predicates:");
        System.out.println("   - istLeer: prüft ob String leer ist");
        System.out.println("   - istLang: prüft ob String länger als 10 Zeichen ist");
        
        // Kombiniere mit and(): beide Bedingungen müssen erfüllt sein
        Predicate<String> leerUndLang = istLeer.and(istLang);
        System.out.println("\n   Kombination mit and() (leer UND lang):");
        System.out.println("   - \"\" (leer): " + leerUndLang.test(""));
        System.out.println("   - \"Hallo\" (weder leer noch lang): " + leerUndLang.test("Hallo"));
        System.out.println("   - \"Hallo Welt, das ist ein langer Text\" (lang, aber nicht leer): " + 
                          leerUndLang.test("Hallo Welt, das ist ein langer Text"));
        
        // Kombiniere mit or(): mindestens eine Bedingung muss erfüllt sein
        Predicate<String> leerOderLang = istLeer.or(istLang);
        System.out.println("\n   Kombination mit or() (leer ODER lang):");
        System.out.println("   - \"\" (leer): " + leerOderLang.test(""));
        System.out.println("   - \"Hallo\" (weder leer noch lang): " + leerOderLang.test("Hallo"));
        System.out.println("   - \"Hallo Welt, das ist ein langer Text\" (lang): " + 
                          leerOderLang.test("Hallo Welt, das ist ein langer Text"));
        
        // Weitere Beispiele mit anderen Predicates
        Predicate<String> nichtLeer = s -> !s.isEmpty();
        Predicate<String> kurzGenug = s -> s.length() <= 10;
        
        Predicate<String> gueltig = nichtLeer.and(kurzGenug);
        System.out.println("\n   Weitere Kombination: nichtLeer.and(kurzGenug)");
        System.out.println("   - \"\" (leer): " + gueltig.test(""));
        System.out.println("   - \"Hallo\" (nicht leer und kurz): " + gueltig.test("Hallo"));
        System.out.println("   - \"Hallo Welt, das ist ein langer Text\" (zu lang): " + 
                          gueltig.test("Hallo Welt, das ist ein langer Text"));
        
        System.out.println();
    }
    
    /**
     * Demonstriert die Verkettung von zwei Functions mit andThen()
     */
    private static void demonstrateFunctionChaining() {
        System.out.println("3. Verkettung von Functions mit andThen():\n");
        
        // Erstelle zwei Functions
        Function<String, String> trim = s -> s.trim();
        Function<String, String> toUpper = s -> s.toUpperCase();
        
        System.out.println("   Ausgangs-Functions:");
        System.out.println("   - trim: entfernt Leerzeichen am Anfang und Ende");
        System.out.println("   - toUpper: konvertiert zu Großbuchstaben");
        
        // Verkette mit andThen(): zuerst trim, dann toUpper
        Function<String, String> pipeline = trim.andThen(toUpper);
        System.out.println("\n   Verkettung mit andThen() (trim -> toUpper):");
        System.out.println("   - \" hallo welt \" -> \"" + pipeline.apply(" hallo welt ") + "\"");
        System.out.println("   - \"  Java  \" -> \"" + pipeline.apply("  Java  ") + "\"");
        System.out.println("   - \"  Functional Interfaces  \" -> \"" + 
                          pipeline.apply("  Functional Interfaces  ") + "\"");
        
        // Weitere Beispiele mit anderen Functions
        Function<String, String> toLower = s -> s.toLowerCase();
        Function<String, String> addPrefix = s -> ">>> " + s;
        
        Function<String, String> komplexePipeline = trim.andThen(toLower).andThen(addPrefix);
        System.out.println("\n   Komplexe Pipeline (trim -> toLower -> addPrefix):");
        System.out.println("   - \"  HALLO WELT  \" -> \"" + 
                          komplexePipeline.apply("  HALLO WELT  ") + "\"");
        
        // Function mit Typ-Transformation
        Function<String, Integer> laenge = s -> s.length();
        Function<Integer, String> zuText = n -> "Länge: " + n;
        
        Function<String, String> laengeAlsText = trim.andThen(laenge).andThen(zuText);
        System.out.println("\n   Pipeline mit Typ-Transformation (trim -> laenge -> zuText):");
        System.out.println("   - \"  hallo  \" -> \"" + laengeAlsText.apply("  hallo  ") + "\"");
        
        System.out.println();
    }
}
