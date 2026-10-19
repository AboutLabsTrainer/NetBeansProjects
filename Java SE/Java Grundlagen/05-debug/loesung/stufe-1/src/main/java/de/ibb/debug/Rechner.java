package de.ibb.debug;
public class Rechner {
    public static int quadrat(int wert) {
        return wert * wert;
    }
    public static int summeBis(int n) {
        int summe = 0;
        for (int i = 1; i <= n; i++) {
            summe += i;
        }
        return summe;
    }
}
