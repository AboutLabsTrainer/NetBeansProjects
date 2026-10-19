package de.ibb.uebung.exceptions;

public final class Mietpreis {

    private Mietpreis() {
    }

    public static double berechne(double preisProTag, int tage) throws UngueltigeMietdauerException {
        // TODO: tage <= 0, tage > 30 und negativer Tagespreis werfen UngueltigeMietdauerException
        // Meldungen wie in MietRechner.berechneMietpreis
        return 0.0;
    }
}
