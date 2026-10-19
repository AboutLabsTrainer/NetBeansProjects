package de.ibb.uebung.fahrzeuge;

public class Lkw extends Fahrzeug {

    private final double maxLadungTonnen;

    public Lkw(String kennzeichen, int baujahr, double maxLadungTonnen) {
        super(kennzeichen, baujahr);
        this.maxLadungTonnen = maxLadungTonnen;
    }

    public String beschreibung() {
        return "=== LKW ===\n" + basis() + "\nmax. Ladung: " + maxLadungTonnen + " t";
    }
}
