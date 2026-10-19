package de.ibb.generics;

import java.util.ArrayList;
import java.util.List;

public class BestellBuch {

    private final List<Posten> posten = new ArrayList<>();

    public void hinzufuegen(Posten posten) {
        // TODO nur diesen Posten aufnehmen
    }

    public double gesamtwert() {
        // TODO Preise addieren
        return 0;
    }

    public List<Posten> ueber(double minWert) {
        // TODO Preis strikt größer als minWert
        return List.of();
    }

    public static void main(String[] args) {
        BestellBuch buch = new BestellBuch();
        buch.hinzufuegen(new Posten("Produkt A", 100));
        buch.hinzufuegen(new Posten("Produkt B", 200));
        buch.hinzufuegen(new Posten("Produkt C", 300));
        System.out.println(buch.gesamtwert());
    }
}
