package de.ibb.funktionen;

import java.util.function.Function;
import java.util.function.Predicate;

public class TextRegeln {

    public static boolean leerOderLang(String text) {
        Predicate<String> leer = String::isEmpty;
        Predicate<String> lang = wert -> wert.length() > 10;
        return leer.or(lang).test(text);
    }

    public static boolean gueltig(String text) {
        Predicate<String> nichtLeer = wert -> !wert.isEmpty();
        Predicate<String> kurz = wert -> wert.length() <= 10;
        return nichtLeer.and(kurz).test(text);
    }

    public static String trimUndGross(String text) {
        Function<String, String> trim = String::trim;
        Function<String, String> gross = String::toUpperCase;
        return trim.andThen(gross).apply(text);
    }

    public static String laengeAlsText(String text) {
        Function<String, String> trim = String::trim;
        Function<String, Integer> laenge = String::length;
        Function<Integer, String> alsText = n -> "Länge: " + n;
        return trim.andThen(laenge).andThen(alsText).apply(text);
    }

    public static void main(String[] args) {
        System.out.println(leerOderLang(""));
        System.out.println(gueltig("Hallo"));
        System.out.println(trimUndGross(" hallo welt "));
        System.out.println(laengeAlsText("  hallo  "));
    }
}
