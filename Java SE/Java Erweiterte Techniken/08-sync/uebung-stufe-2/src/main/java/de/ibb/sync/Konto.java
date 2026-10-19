package de.ibb.sync;

public class Konto {

    private final String name;
    private int stand;

    public Konto(String name, int stand) {
        this.name = name;
        this.stand = stand;
    }

    public String name() {
        return name;
    }

    public int stand() {
        return stand;
    }

    public static void ueberweisen(Konto von, Konto nach, int betrag) {
        // TODO beide Konten in fester Namensreihenfolge sperren
    }

    public static void main(String[] args) {
        Konto a = new Konto("A", 100);
        Konto b = new Konto("B", 40);
        ueberweisen(a, b, 30);
        ueberweisen(b, a, 10);
        System.out.println(a.stand() + " / " + b.stand());
    }
}
