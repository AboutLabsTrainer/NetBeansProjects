package de.ibb.lambda;

/**
 * Funktionale Schnittstelle zur Preisberechnung.
 *
 * Ein Lambda vom Typ PriceOperation bekommt einen ursprünglichen Preis
 * und gibt den neuen Preis zurück (z.B. mit Rabatt oder MwSt.).
 */
@FunctionalInterface
public interface PriceOperation {

    double apply(double originalPrice);

    // Hinweis: Es darf nur EINE abstrakte Methode geben,
    // sonst ist es nicht mehr "functional".
}