package de.ibb.uebung.fahrzeuge;

public class Mietwagen {

    public static final double MWST = 0.19;

    private static int anzahl;

    private final double mietpreisProTag;

    public Mietwagen(String kennzeichen, double mietpreisProTag) {
        this.mietpreisProTag = mietpreisProTag;
        anzahl++;
    }

    public static int getAnzahl() {
        return anzahl;
    }

    public double berechneMietpreisBrutto(int tage) {
        return berechneMietpreisBrutto(mietpreisProTag, tage);
    }

    public static double berechneMietpreisBrutto(double preisProTag, int tage) {
        if (tage <= 0) {
            return 0.0;
        }
        double netto = preisProTag * tage;
        return netto * (1 + MWST);
    }
}
