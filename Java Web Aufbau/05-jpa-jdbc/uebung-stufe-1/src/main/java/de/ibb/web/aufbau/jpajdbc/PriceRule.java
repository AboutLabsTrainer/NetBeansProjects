package de.ibb.web.aufbau.jpajdbc;

import java.math.BigDecimal;

public final class PriceRule {
    private PriceRule() {}

    public static boolean accept(BigDecimal price) {
        // TODO: null und negative Preise ablehnen. 0 ist erlaubt.
        throw new UnsupportedOperationException("TODO");
    }
}
