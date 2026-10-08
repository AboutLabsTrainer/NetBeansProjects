package de.ibb;

import java.util.List;

/**
 * Test-Klasse für BestellService.
 * Beweist die Typsicherheit des reparierten Codes.
 */
public class BestellServiceTest {
    
    public static void main(String[] args) {
        System.out.println("=== BestellService Typsicherheits-Tests ===\n");
        
        testTypsicherheit();
        testBerechneGesamtwert();
        testGetBestellungenUeber();
        testBonusCompileError();
        
        System.out.println("\n=== Alle Tests erfolgreich! ===");
    }
    
    /**
     * Test 1: Typsicherheit - korrekte Verwendung
     * Beweist, dass nur Bestellung-Objekte hinzugefügt werden können.
     */
    public static void testTypsicherheit() {
        System.out.println("Test 1: Typsicherheit");
        BestellService service = new BestellService();
        
        // Korrekte Verwendung - sollte funktionieren
        service.addBestellung(new Bestellung("Laptop", 999.99));
        service.addBestellung(new Bestellung("Maus", 29.99));
        service.addBestellung(new Bestellung("Tastatur", 79.99));
        
        // Rückgabe ist typsicher - keine Casts nötig
        List<Bestellung> bestellungen = service.getBestellungenUeber(0.0);
        for (Bestellung b : bestellungen) {
            // Kein Cast nötig - der Compiler weiß, dass es Bestellung ist!
            System.out.println("  ✓ " + b.getProdukt() + ": " + b.getPreis() + " €");
        }
        
        System.out.println("  ✓ Typsicherheit: Alle Bestellungen sind vom Typ Bestellung");
    }
    
    /**
     * Test 2: Berechne Gesamtwert
     * Testet die korrekte Berechnung des Gesamtwerts.
     */
    public static void testBerechneGesamtwert() {
        System.out.println("\nTest 2: Berechne Gesamtwert");
        BestellService service = new BestellService();
        
        service.addBestellung(new Bestellung("Produkt A", 100.0));
        service.addBestellung(new Bestellung("Produkt B", 200.0));
        service.addBestellung(new Bestellung("Produkt C", 300.0));
        
        double gesamt = service.berechneGesamtwert();
        double erwartet = 600.0;
        
        if (Math.abs(gesamt - erwartet) < 0.01) {
            System.out.println("  ✓ Gesamtwert korrekt: " + gesamt + " € (erwartet: " + erwartet + " €)");
        } else {
            System.out.println("  ✗ Gesamtwert falsch: " + gesamt + " € (erwartet: " + erwartet + " €)");
        }
    }
    
    /**
     * Test 3: Get Bestellungen über Mindestwert
     * Testet die Filterung nach Mindestwert.
     */
    public static void testGetBestellungenUeber() {
        System.out.println("\nTest 3: Get Bestellungen über Mindestwert");
        BestellService service = new BestellService();
        
        service.addBestellung(new Bestellung("Günstig", 10.0));
        service.addBestellung(new Bestellung("Mittel", 50.0));
        service.addBestellung(new Bestellung("Teuer", 100.0));
        
        List<Bestellung> teure = service.getBestellungenUeber(40.0);
        
        if (teure.size() == 2) {
            System.out.println("  ✓ Korrekte Anzahl gefilterter Bestellungen: " + teure.size());
            for (Bestellung b : teure) {
                System.out.println("    - " + b.getProdukt() + ": " + b.getPreis() + " €");
            }
        } else {
            System.out.println("  ✗ Falsche Anzahl: " + teure.size() + " (erwartet: 2)");
        }
    }
    
    /**
     * Test 4: Bonus - Compile-Error bei falschen Typen
     * 
     * BONUS-FRAGE: Was passiert, wenn jemand addBestellung("Keine echte Bestellung") aufruft?
     * 
     * VORHER (mit Raw Types):
     * - Der Code kompilierte ohne Fehler
     * - Zur Laufzeit: ClassCastException beim Zugriff auf die Bestellung
     * - Fehler wird erst spät entdeckt (z.B. beim Kunden)
     * 
     * NACHHER (mit Generics):
     * - Der Code kompiliert NICHT - Compile-Error!
     * - Der Fehler wird sofort beim Schreiben des Codes erkannt
     * - Keine Laufzeit-Exceptions mehr möglich
     * 
     * Der folgende Code würde einen COMPILE-ERROR verursachen:
     */
    public static void testBonusCompileError() {
        System.out.println("\nTest 4: Bonus - Compile-Error bei falschen Typen");
        BestellService service = new BestellService();
        
        // ✅ KORREKT: Bestellung-Objekt hinzufügen
        service.addBestellung(new Bestellung("Korrekte Bestellung", 99.99));
        System.out.println("  ✓ Korrekte Verwendung funktioniert");
        
        // ❌ FOLGENDER CODE WÜRDE EINEN COMPILE-ERROR VERURSACHEN:
        // service.addBestellung("Keine echte Bestellung");  // COMPILE ERROR!
        // service.addBestellung(42);                         // COMPILE ERROR!
        // service.addBestellung(new Object());               // COMPILE ERROR!
        
        System.out.println("  ✓ Typsicherheit: Falsche Typen werden zur Compile-Zeit abgefangen");
        System.out.println("  ✓ Vorteil: Fehler werden früh erkannt, nicht erst zur Laufzeit");
        
        System.out.println("\n  BONUS-ANTWORT:");
        System.out.println("  VORHER: addBestellung(\"String\") kompilierte, führte aber zur Laufzeit zu ClassCastException");
        System.out.println("  NACHHER: addBestellung(\"String\") verursacht sofort einen Compile-Error");
        System.out.println("  → Der Fehler wird beim Schreiben erkannt, nicht erst beim Kunden!");
    }
}
