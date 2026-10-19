package de.ibb.preis;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ShopRegeln {

    public static List<String> elektronikNamen(List<Artikel> artikel) {
        Predicate<Artikel> elektronik = stueck -> "Elektronik".equalsIgnoreCase(stueck.kategorie());
        Function<Artikel, String> name = Artikel::name;
        List<String> namen = new ArrayList<>();
        for (Artikel stueck : artikel) {
            if (elektronik.test(stueck)) {
                namen.add(name.apply(stueck));
            }
        }
        return namen;
    }

    public static double skontoWennGross(double preis) {
        PreisRechner rabatt = wert -> wert * 0.90;
        double nachRabatt = rabatt.anwenden(preis);
        if (nachRabatt >= 50.0) {
            PreisRechner skonto = wert -> wert * 0.98;
            return skonto.anwenden(nachRabatt);
        }
        return nachRabatt;
    }

    public static void main(String[] args) {
        List<Artikel> artikel = List.of(
                new Artikel("Monitor 27\"", 199.00, "Elektronik"),
                new Artikel("Maus", 19.99, "Elektronik"),
                new Artikel("Roman", 12.50, "Buch"));
        System.out.println(elektronikNamen(artikel));
        System.out.println(skontoWennGross(199));
    }
}
