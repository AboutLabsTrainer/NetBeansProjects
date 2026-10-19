package de.ibb.uebung.fahrzeuge;

/**
 * Nachbau von {@code FahrzeugDemo.berechneMietpreis} und dem expliziten Cast
 * von {@code tageAlsInt} nach {@code short}.
 */
public class MietpreisRechner {

    /**
     * Expliziter Cast, wie {@code short tageAlsShort = (short) tageAlsInt}.
     */
    public static short alsShort(int tage) {
        // TODO: int explizit nach short casten
        return 0;
    }

    /**
     * Nettomietpreis. Der zweite Parameter ist bewusst {@code short},
     * damit der kleinere Typ in die Berechnung eingeht.
     */
    public static double berechneMietpreis(double preisProTag, short tage) {
        // TODO: Tagessatz mal Tage
        return 0.0;
    }
}
