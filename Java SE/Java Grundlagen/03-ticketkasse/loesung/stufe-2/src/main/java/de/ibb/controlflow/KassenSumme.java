package de.ibb.controlflow;
public class KassenSumme {
    public static double gesamt(int anzahl, double einzelpreis, boolean rabattTag) {
        double summe = anzahl * einzelpreis;
        if (rabattTag) {
            summe = summe * 0.8;
        }
        if (anzahl >= 5) {
            summe = summe * 0.9;
        }
        return summe;
    }
}
