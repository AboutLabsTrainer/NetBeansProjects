package de.ibb.debugchallenge;
public class AuswerterApp {
    public static void main(String[] args) {
        int[] werte = {5, 8, -3, 10, 42};
        System.out.println(Auswerter.summe(werte));
        System.out.println(Auswerter.maximum(werte));
        System.out.println(Auswerter.durchschnitt(werte));
        System.out.println(Auswerter.indexVon(werte, 10));
    }
}
