package de.ibb.uebung.fahrzeuge;

/**
 * Verkürztes Fahrzeug aus der Static-Übung: Klassenzähler und Bruttopreis mit {@code MWST}.
 */
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
        // TODO: Netto aus dem Tagessatz dieses Objekts, dann MWST wie in Fahrzeug
        return 0.0;
    }

    public static double berechneMietpreisBrutto(double preisProTag, int tage) {
        // TODO: dieselbe Formel ohne Objekt; bei tage <= 0 den Wert 0 liefern
        return 0.0;
    }
}
