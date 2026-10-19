package de.ibb.oop;
public class BuchPreisApp {
    public static void main(String[] args) {
        Buch buch = new Buch("Java", "Erika", 20);
        buch.rabatt(10);
        System.out.println(buch.getPreis());
    }
}
