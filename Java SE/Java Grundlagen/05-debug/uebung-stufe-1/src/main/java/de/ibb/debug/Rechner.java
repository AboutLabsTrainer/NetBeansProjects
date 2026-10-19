package de.ibb.debug;
public class Rechner {
    public static int quadrat(int wert) {
        // TODO: reparieren, aktuell wird addiert
        return wert + wert;
    }
    public static int summeBis(int n) {
        int summe = 0;
        for (int i = 1; i < n; i++) { // TODO: Grenze
            summe += i;
        }
        return summe;
    }
}
