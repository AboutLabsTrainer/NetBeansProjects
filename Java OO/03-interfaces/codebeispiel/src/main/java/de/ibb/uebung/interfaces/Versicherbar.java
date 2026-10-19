package de.ibb.uebung.interfaces;

/**
 * Interface für Objekte, die eine Versicherungsprämie berechnen können.
 */
public interface Versicherbar {

    /**
     * Berechnet die Versicherungsprämie in EUR.
     */
    double berechneVersicherungsPraemie();
}