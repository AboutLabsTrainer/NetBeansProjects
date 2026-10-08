package de.ibb.uebung.interfaces;

/**
 * Beschreibt eine Regel zur Berechnung eines prozentualen Rabatts
 * in Abhängigkeit von der Mietdauer.
 */
public interface RabattRegel {

    /**
     * @param tage Anzahl der Miettage
     * @return Rabatt in Prozent (z.B. 10.0 für 10 %)
     */
    double berechneRabattProzent(int tage);
}