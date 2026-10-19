package de.ibb.funktionen;

import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class PreisFunktionen {

    public static double gesamt(double preis, int menge) {
        BiFunction<Double, Integer, Double> produkt = (wert, stueck) -> wert * stueck;
        return produkt.apply(preis, menge);
    }

    public static boolean istMittel(double preis) {
        Predicate<Double> ab = wert -> wert >= 20.0;
        Predicate<Double> bis = wert -> wert <= 80.0;
        return ab.and(bis).test(preis);
    }

    public static String betragText(double preis, int menge) {
        BiFunction<Double, Integer, Double> produkt = (wert, stueck) -> wert * stueck;
        Function<Double, String> format = wert -> String.format(Locale.US, "%.2f EUR", wert);
        return format.apply(produkt.apply(preis, menge));
    }

    public static void main(String[] args) {
        System.out.println(gesamt(19.99, 3));
        System.out.println(betragText(19.99, 3));
        System.out.println(istMittel(50));
    }
}
