package de.ibb.uebung.interfaces;

/**
 * Saison mit Faktor und Rabattlogik.
 */
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
        switch (this) {
            case NEBENSAISON:
                // ab 5 Tagen 10 % Rabatt
                return tage >= 5 ? 10.0 : 0.0;
            case STANDARD:
                // ab 7 Tagen 5 % Rabatt
                return tage >= 7 ? 5.0 : 0.0;
            case HAUPTSAISON:
            default:
                // kein Rabatt in der Hauptsaison
                return 0.0;
        }
    }
}