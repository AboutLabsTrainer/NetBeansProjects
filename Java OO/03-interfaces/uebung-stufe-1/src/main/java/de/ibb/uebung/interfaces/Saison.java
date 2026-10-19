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
        // TODO: Nebensaison ab 5 Tagen 10 %, Standard ab 7 Tagen 5 %, Hauptsaison 0
        return 0.0;
    }
}
