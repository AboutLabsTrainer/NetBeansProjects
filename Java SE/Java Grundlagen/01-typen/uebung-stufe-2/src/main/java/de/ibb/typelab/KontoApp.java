package de.ibb.typelab;
public class KontoApp {
    public static void main(String[] args) {
        Konto konto = new Konto("Petra", 100);
        konto.einzahlen(25);
        System.out.println(konto.getStand());
        System.out.println(konto.abheben(200));
        System.out.println(konto.abheben(25));
        System.out.println(konto.getStand());
    }
}
