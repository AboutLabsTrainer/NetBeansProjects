package de.ibb.uebung.interfaces;

public enum Saison implements RabattRegel {

    NEBENSAISON(0.8),
    STANDARD(1.0),
    HAUPTSAISON(1.2);

    private final double faktor;

    Saison(double faktor) {
        this.faktor = faktor;
    }

    public double getFaktor() {
        return faktor;
    }

    @Override
    public double berechneRabattProzent(int tage) {
        return switch (this) {
            case NEBENSAISON -> tage >= 5 ? 10.0 : 0.0;
            case STANDARD -> tage >= 7 ? 5.0 : 0.0;
            case HAUPTSAISON -> 0.0;
        };
    }
}
