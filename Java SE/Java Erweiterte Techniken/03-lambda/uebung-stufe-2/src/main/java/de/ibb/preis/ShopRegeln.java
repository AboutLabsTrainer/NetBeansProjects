package de.ibb.preis;

import java.util.List;

public class ShopRegeln {

    public static List<String> elektronikNamen(List<Artikel> artikel) {
        // TODO Kategorie Elektronik, Groß/Klein egal
        return List.of();
    }

    public static double skontoWennGross(double preis) {
        // TODO 10 Prozent Rabatt, ab 50 noch 2 Prozent
        return 0;
    }

    public static void main(String[] args) {
        List<Artikel> artikel = List.of(
                new Artikel("Monitor 27\"", 199.00, "Elektronik"),
                new Artikel("Maus", 19.99, "Elektronik"),
                new Artikel("Roman", 12.50, "Buch"));
        System.out.println(elektronikNamen(artikel));
        System.out.println(skontoWennGross(199));
        System.out.println(skontoWennGross(29.90));
    }
}
