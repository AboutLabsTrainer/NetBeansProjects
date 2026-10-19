package de.ibb.generics;

public final class ZahlenListe<T extends Number> {

    public void add(T wert) {
        // TODO Wert merken
    }

    public double summe() {
        // TODO doubleValue addieren
        return 0;
    }

    public T maximum() {
        // TODO größter Wert, leer ergibt null
        return null;
    }

    public static void main(String[] args) {
        Paar<Integer, String> paar = new Paar<>("id", 7).tauschen();
        System.out.println(paar);
        ZahlenListe<Integer> zahlen = new ZahlenListe<>();
        zahlen.add(10);
        zahlen.add(3);
        zahlen.add(8);
        System.out.println(zahlen.summe() + " / " + zahlen.maximum());
    }
}
