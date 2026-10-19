package de.ibb.preis;

import java.util.List;
import java.util.function.Predicate;

public class PreisPipeline {

    public static double rabattDannMwst(double preis) {
        // TODO 10 Prozent Rabatt, danach 19 Prozent MwSt.
        return 0;
    }

    public static double mitVersand(double preis) {
        // TODO rabattDannMwst plus 4.95
        return 0;
    }

    public static List<Artikel> filtern(List<Artikel> alle, Predicate<Artikel> regel) {
        // TODO Reihenfolge beibehalten
        return List.of();
    }

    public static List<Integer> bestellnummern(int start, int anzahl) {
        // TODO Supplier ab start
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(rabattDannMwst(29.90));
        System.out.println(mitVersand(29.90));
        System.out.println(bestellnummern(1000, 5));
    }
}
