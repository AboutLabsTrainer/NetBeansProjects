package de.ibb.jpa;

import java.math.BigDecimal;
import java.util.List;

public final class BestellSumme {

    private BestellSumme() {
    }

    public static BigDecimal summe(List<BigDecimal> betraege) {
        BigDecimal summe = BigDecimal.ZERO;
        if (betraege == null) {
            return summe;
        }
        for (BigDecimal betrag : betraege) {
            if (betrag != null) {
                summe = summe.add(betrag);
            }
        }
        return summe;
    }
}
