package de.ibb.uebung.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MietpreisTest {

    @Test
    void gueltigerPreis() throws UngueltigeMietdauerException {
        assertEquals(149.97, Mietpreis.berechne(49.99, 3), 0.001);
    }

    @Test
    void regeln() {
        assertEquals("Mietdauer muss positiv sein.",
                assertThrows(UngueltigeMietdauerException.class, () -> Mietpreis.berechne(10, 0)).getMessage());
        assertEquals("Mietdauer darf 30 Tage nicht überschreiten.",
                assertThrows(UngueltigeMietdauerException.class, () -> Mietpreis.berechne(10, 31)).getMessage());
        assertEquals("Tagespreis darf nicht negativ sein.",
                assertThrows(UngueltigeMietdauerException.class, () -> Mietpreis.berechne(-1, 2)).getMessage());
    }
}
