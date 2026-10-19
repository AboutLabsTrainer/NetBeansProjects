package de.ibb.uebung.exceptions;

/**
 * Eigene checked Exception für fachlich ungültige Mietdauer.
 * Musterlösung.
 */
public class UngueltigeMietdauerException extends Exception {

    public UngueltigeMietdauerException(String message) {
        super(message);
    }
}