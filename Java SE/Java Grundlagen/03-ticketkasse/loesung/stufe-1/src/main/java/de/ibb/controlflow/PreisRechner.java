package de.ibb.controlflow;
public class PreisRechner {
    public static double preisFuerAlter(int alter) {
        if (alter < 14) {
            return 5;
        }
        if (alter < 65) {
            return 10;
        }
        return 7;
    }
    public static double mitRabatt(double preis, boolean rabattTag) {
        return rabattTag ? preis * 0.8 : preis;
    }
}
