package de.ibb.controlflow;
public class PreisApp {
    public static void main(String[] args) {
        double preis = PreisRechner.preisFuerAlter(14);
        System.out.println(PreisRechner.mitRabatt(preis, true));
    }
}
