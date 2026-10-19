package de.ibb.web.basic.jstl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PriceCalcTest {
    @Test
    void shippingStartsAboveFifty() {
        assertFalse(PriceCalc.freeShipping(new BigDecimal("50.00")));
        assertTrue(PriceCalc.freeShipping(new BigDecimal("50.01")));
        assertFalse(PriceCalc.freeShipping(null));
    }
}
