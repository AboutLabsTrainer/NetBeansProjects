package de.ibb.controlflow;
public class KassenApp {
    public static void main(String[] args) {
        System.out.println(KassenSumme.gesamt(5, 10, false));
        System.out.println(KassenSumme.gesamt(5, 10, true));
    }
}
