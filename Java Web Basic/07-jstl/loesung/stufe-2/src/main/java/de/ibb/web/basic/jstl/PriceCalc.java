package de.ibb.web.basic.jstl;

import java.math.BigDecimal;

public final class PriceCalc {
    private PriceCalc() {}

    public static boolean freeShipping(BigDecimal total) {
        return total != null && total.compareTo(new BigDecimal("50")) > 0;
    }
}
