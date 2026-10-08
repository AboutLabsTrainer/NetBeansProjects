package de.ibb;

import java.util.ArrayList;
import java.util.List;

/**
 * AUFGABE: Finde und behebe alle Generics-Probleme.
 * Der Code kompiliert – aber er ist eine Zeitbombe!
 * 
 * ORIGINAL-VERSION (unverbessert) - Diese Version zeigt die Probleme:
 * - Raw Types: List ohne Generics
 * - Unsichere Parameter: Object statt Bestellung
 * - Explizite Casts erforderlich
 * - Keine Typsicherheit zur Compile-Zeit
 */
public class BestellServiceLegacy {
    private List bestellungen = new ArrayList();
    
    public void addBestellung(Object bestellung) {
        bestellungen.add(bestellung);
    }
    
    public double berechneGesamtwert() {
        double summe = 0;
        for (Object obj : bestellungen) {
            Bestellung b = (Bestellung) obj;  // Gefährlicher Cast!
            summe += b.getPreis();
        }
        return summe;
    }
    
    public List getBestellungenUeber(double minWert) {
        List ergebnis = new ArrayList();
        for (Object obj : bestellungen) {
            Bestellung b = (Bestellung) obj;  // Gefährlicher Cast!
            if (b.getPreis() > minWert) {
                ergebnis.add(b);
            }
        }
        return ergebnis;
    }
}
