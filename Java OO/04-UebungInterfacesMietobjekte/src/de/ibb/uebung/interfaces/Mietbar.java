package de.ibb.uebung.interfaces;

/**
 * Gemeinsames Interface für alle Mietobjekte.
 */
public interface Mietbar {

    // Basis-Mehrwertsteuersatz als Konstante
    double BASIS_MWST = 0.19;

    /**
     * Berechnet den Nettomietpreis für die angegebene Anzahl von Tagen.
     */
    double berechneMietpreis(int tage);

    /**
     * Liefert eine kurze Beschreibung des Mietobjekts.
     */
    String getBeschreibung();

    /**
     * Default-Methode: berechnet den Bruttopreis mit MWSt.
     */
    default double berechneMietpreisMitMwst(int tage) {
        double netto = berechneMietpreis(tage);
        return netto * (1 + BASIS_MWST);
    }
}