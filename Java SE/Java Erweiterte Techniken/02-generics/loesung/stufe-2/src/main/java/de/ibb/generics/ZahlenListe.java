package de.ibb.generics;

import java.util.ArrayList;
import java.util.List;

public final class ZahlenListe<T extends Number> {

    private final List<T> werte = new ArrayList<>();

    public void add(T wert) {
        werte.add(wert);
    }

    public double summe() {
        double summe = 0;
        for (T wert : werte) {
            summe += wert.doubleValue();
        }
        return summe;
    }

    public T maximum() {
        T max = null;
        for (T wert : werte) {
            if (max == null || wert.doubleValue() > max.doubleValue()) {
                max = wert;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Paar<Integer, String> paar = new Paar<>("id", 7).tauschen();
        System.out.println(paar.erstes() + " / " + paar.zweites());
        ZahlenListe<Integer> zahlen = new ZahlenListe<>();
        zahlen.add(10);
        zahlen.add(3);
        zahlen.add(8);
        System.out.println(zahlen.summe() + " / " + zahlen.maximum());
    }
}
