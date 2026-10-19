package de.ibb.workshop;
public class NamensTeile {
    public static String vorname(String voll) {
        return voll.substring(0, voll.indexOf(' '));
    }
    public static String nachname(String voll) {
        return voll.substring(voll.indexOf(' ') + 1);
    }
    public static boolean enthaelt(String text, String suche) {
        return text.contains(suche);
    }
}
