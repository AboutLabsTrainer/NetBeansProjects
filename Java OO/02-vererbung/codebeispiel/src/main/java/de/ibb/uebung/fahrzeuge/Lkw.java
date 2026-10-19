package de.ibb.uebung.fahrzeuge;

/**
 * Unterklasse für LKW-Fahrzeuge.
 *
 * Zeigt:
 *  - Vererbung mit extends Fahrzeug
 *  - zusätzlichen Attributwert (maxLadungTonnen)
 *  - super(...) im Konstruktor
 *  - Überschreiben von druckeDaten()
 */
public class Lkw extends Fahrzeug {

    private double maxLadungTonnen;

    public Lkw(String kennzeichen, int baujahr, double maxLadungTonnen) {
        super(kennzeichen, baujahr);
        this.maxLadungTonnen = maxLadungTonnen;
    }

    public double getMaxLadungTonnen() {
        return maxLadungTonnen;
    }

    public void setMaxLadungTonnen(double maxLadungTonnen) {
        this.maxLadungTonnen = maxLadungTonnen;
    }

    @Override
    public void druckeDaten() {
        System.out.println("=== LKW ===");
        super.druckeDaten();
        System.out.println("max. Ladung: " + maxLadungTonnen + " t");
    }

    @Override
    public String toString() {
        return "Lkw{" +
                "kennzeichen='" + getKennzeichen() + '\'' +
                ", baujahr=" + getBaujahr() +
                ", maxLadungTonnen=" + maxLadungTonnen +
                '}';
    }
}