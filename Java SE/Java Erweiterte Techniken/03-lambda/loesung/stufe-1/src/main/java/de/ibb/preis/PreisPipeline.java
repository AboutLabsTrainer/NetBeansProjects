package de.ibb.preis;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PreisPipeline {

    public static double rabattDannMwst(double preis) {
        PreisRechner rabatt = wert -> wert * 0.90;
        PreisRechner mwst = wert -> wert * 1.19;
        return mwst.anwenden(rabatt.anwenden(preis));
    }

    public static double mitVersand(double preis) {
        PreisRechner versand = wert -> wert + 4.95;
        return versand.anwenden(rabattDannMwst(preis));
    }

    public static List<Artikel> filtern(List<Artikel> alle, Predicate<Artikel> regel) {
        List<Artikel> treffer = new ArrayList<>();
        for (Artikel artikel : alle) {
            if (regel.test(artikel)) {
                treffer.add(artikel);
            }
        }
        return treffer;
    }

    public static List<Integer> bestellnummern(int start, int anzahl) {
        int[] aktuell = {start};
        Supplier<Integer> lieferant = () -> aktuell[0]++;
        List<Integer> nummern = new ArrayList<>();
        for (int i = 0; i < anzahl; i++) {
            nummern.add(lieferant.get());
        }
        return nummern;
    }

    public static void main(String[] args) {
        System.out.println(rabattDannMwst(29.90));
        System.out.println(mitVersand(29.90));
        System.out.println(bestellnummern(1000, 5));
    }
}
