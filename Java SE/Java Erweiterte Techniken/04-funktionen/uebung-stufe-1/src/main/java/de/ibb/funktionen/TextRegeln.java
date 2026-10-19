package de.ibb.funktionen;

public class TextRegeln {

    public static boolean leerOderLang(String text) {
        // TODO leer oder länger als 10
        return false;
    }

    public static boolean gueltig(String text) {
        // TODO nicht leer und höchstens 10 Zeichen
        return false;
    }

    public static String trimUndGross(String text) {
        // TODO trim, danach Großbuchstaben
        return "";
    }

    public static String laengeAlsText(String text) {
        // TODO trim, Länge, "Länge: n"
        return "";
    }

    public static void main(String[] args) {
        System.out.println(leerOderLang(""));
        System.out.println(gueltig("Hallo"));
        System.out.println(trimUndGross(" hallo welt "));
        System.out.println(laengeAlsText("  hallo  "));
    }
}
