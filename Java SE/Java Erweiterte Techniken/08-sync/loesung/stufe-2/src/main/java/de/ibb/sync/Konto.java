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
        if (betrag < 0) {
            throw new IllegalArgumentException("betrag");
        }
        Konto erstes = von;
        Konto zweites = nach;
        int vergleich = von.name.compareTo(nach.name);
        if (vergleich > 0 || (vergleich == 0 && System.identityHashCode(von) > System.identityHashCode(nach))) {
            erstes = nach;
            zweites = von;
        }
        if (erstes == zweites) {
            synchronized (erstes) {
                buchen(von, nach, betrag);
            }
            return;
        }
        synchronized (erstes) {
            synchronized (zweites) {
                buchen(von, nach, betrag);
            }
        }
    }

    private static void buchen(Konto von, Konto nach, int betrag) {
        if (von.stand < betrag) {
            return;
        }
        von.stand -= betrag;
        nach.stand += betrag;
    }

    public static void main(String[] args) {
        Konto a = new Konto("A", 100);
        Konto b = new Konto("B", 40);
        ueberweisen(a, b, 30);
        ueberweisen(b, a, 10);
        System.out.println(a.stand() + " / " + b.stand());
    }
}
