package de.ibb.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

class BestellSummeTest {

    @Test
    void addiertBetraege() {
        BigDecimal summe = BestellSumme.summe(List.of(new BigDecimal("10.00"), new BigDecimal("2.50")));
        assertEquals(0, new BigDecimal("12.50").compareTo(summe));
        assertEquals(0, BigDecimal.ZERO.compareTo(BestellSumme.summe(List.of())));
    }
}
