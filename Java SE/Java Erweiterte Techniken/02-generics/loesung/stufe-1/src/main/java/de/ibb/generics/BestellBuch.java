package de.ibb.generics;

import java.util.ArrayList;
import java.util.List;

public class BestellBuch {

    private final List<Posten> posten = new ArrayList<>();

    public void hinzufuegen(Posten neu) {
        posten.add(neu);
    }

    public double gesamtwert() {
        double summe = 0;
        for (Posten eintrag : posten) {
            summe += eintrag.getPreis();
        }
        return summe;
    }

    public List<Posten> ueber(double minWert) {
        List<Posten> treffer = new ArrayList<>();
        for (Posten eintrag : posten) {
            if (eintrag.getPreis() > minWert) {
                treffer.add(eintrag);
            }
        }
        return treffer;
    }

    public static void main(String[] args) {
        BestellBuch buch = new BestellBuch();
        buch.hinzufuegen(new Posten("Produkt A", 100));
        buch.hinzufuegen(new Posten("Produkt B", 200));
        buch.hinzufuegen(new Posten("Produkt C", 300));
        System.out.println(buch.gesamtwert());
    }
}
