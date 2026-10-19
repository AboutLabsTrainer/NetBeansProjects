package de.ibb.uebung.fahrzeuge;

/**
 * Unterklasse für LKW.
 */
public class Lkw extends Fahrzeug {

    private double maxZuladungTonnen;

    public Lkw(String kennzeichen, int baujahr, double mietpreisProTag, double maxZuladungTonnen) {
        super(kennzeichen, baujahr, mietpreisProTag);
        this.maxZuladungTonnen = maxZuladungTonnen;
    }

    public double getMaxZuladungTonnen() {
        return maxZuladungTonnen;
    }

    public void setMaxZuladungTonnen(double maxZuladungTonnen) {
        this.maxZuladungTonnen = maxZuladungTonnen;
    }

    @Override
    public void druckeDaten() {
        System.out.println("=== LKW ===");
        // super reicht die gemeinsamen Felder an die Basisklasse durch,
        // statt Kennzeichen und Baujahr hier noch einmal auszugeben.
        super.druckeDaten();
        System.out.println("max. Zuladung:   " + maxZuladungTonnen + " t");
    }

    @Override
    public String toString() {
        return "Lkw{" +
                "kennzeichen='" + getKennzeichen() + '\'' +
                ", baujahr=" + getBaujahr() +
                ", mietpreisProTag=" + getMietpreisProTag() +
                ", maxZuladungTonnen=" + maxZuladungTonnen +
                '}';
    }
}
