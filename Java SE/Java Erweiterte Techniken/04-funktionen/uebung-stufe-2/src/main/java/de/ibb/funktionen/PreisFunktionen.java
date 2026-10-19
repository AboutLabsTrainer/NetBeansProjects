package de.ibb.funktionen;

public class PreisFunktionen {

    public static double gesamt(double preis, int menge) {
        // TODO BiFunction aus Preis und Menge
        return 0;
    }

    public static boolean istMittel(double preis) {
        // TODO von 20 bis 80 einschließlich
        return false;
    }

    public static String betragText(double preis, int menge) {
        // TODO "%.2f EUR" mit Locale.US
        return "";
    }

    public static void main(String[] args) {
        System.out.println(gesamt(19.99, 3));
        System.out.println(betragText(19.99, 3));
        System.out.println(istMittel(50));
    }
}
