package de.ibb.uebung.fahrzeuge;

public class MietpreisRechner {

    public static short alsShort(int tage) {
        return (short) tage;
    }

    public static double berechneMietpreis(double preisProTag, short tage) {
        return preisProTag * tage;
    }
}
