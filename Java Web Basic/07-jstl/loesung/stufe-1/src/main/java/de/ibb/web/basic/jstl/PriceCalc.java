package de.ibb.web.basic.jstl;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PriceCalc {
    private PriceCalc() {}

    public static BigDecimal line(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }
}
