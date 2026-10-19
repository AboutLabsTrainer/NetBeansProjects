package de.ibb;

import java.util.ArrayList;
import java.util.List;

/**
 * Reparierter BestellService mit Generics.
 * Alle Raw Types wurden durch typsichere Generics ersetzt.
 * Keine Casts mehr nötig - Typsicherheit zur Compile-Zeit!
 */
public class BestellService {
    private List<Bestellung> bestellungen = new ArrayList<>();
    
    public void addBestellung(Bestellung bestellung) {
        bestellungen.add(bestellung);
    }
    
    public double berechneGesamtwert() {
        double summe = 0;
        for (Bestellung b : bestellungen) {
            summe += b.getPreis();
        }
        return summe;
    }
    
    public List<Bestellung> getBestellungenUeber(double minWert) {
        List<Bestellung> ergebnis = new ArrayList<>();
        for (Bestellung b : bestellungen) {
            if (b.getPreis() > minWert) {
                ergebnis.add(b);
            }
        }
        return ergebnis;
    }
}
