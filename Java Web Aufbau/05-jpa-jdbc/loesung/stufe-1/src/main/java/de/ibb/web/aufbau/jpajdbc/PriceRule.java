package de.ibb.web.aufbau.jpajdbc;

import java.math.BigDecimal;

public final class PriceRule {
    private PriceRule() {}

    public static boolean accept(BigDecimal price) {
        return price != null && price.signum() >= 0;
    }
}
