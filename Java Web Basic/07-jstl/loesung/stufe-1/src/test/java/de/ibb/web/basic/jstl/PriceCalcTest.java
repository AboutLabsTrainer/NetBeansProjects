package de.ibb.web.basic.jstl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PriceCalcTest {
    @Test
    void multipliesAndScales() {
        assertEquals(new BigDecimal("5.00"), PriceCalc.line(new BigDecimal("1.25"), 4));
        assertEquals(new BigDecimal("0.30"), PriceCalc.line(new BigDecimal("0.1"), 3));
    }
}
