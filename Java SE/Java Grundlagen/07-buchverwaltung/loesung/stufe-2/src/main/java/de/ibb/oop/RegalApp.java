package de.ibb.oop;
public class RegalApp {
    public static void main(String[] args) {
        Regal regal = new Regal();
        regal.hinzufuegen(new Buch("A", "Erika", 10));
        regal.hinzufuegen(new Buch("B", "Max", 12));
        regal.hinzufuegen(new Buch("C", "Erika", 14));
        for (Buch buch : regal.vonAutor("Erika")) {
            System.out.println(buch.getTitel());
        }
    }
}
