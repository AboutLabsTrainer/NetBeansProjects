package de.ibb.web.aufbau.jpajdbc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PriceRuleTest {
    @Test
    void rejectsNullAndNegative() {
        assertTrue(PriceRule.accept(new BigDecimal("0")));
        assertTrue(PriceRule.accept(new BigDecimal("1.50")));
        assertFalse(PriceRule.accept(new BigDecimal("-0.01")));
        assertFalse(PriceRule.accept(null));
    }
}
