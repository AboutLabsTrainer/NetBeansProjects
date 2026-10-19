package de.ibb.uebung.fahrzeuge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WrapperTageTest {

    @Test
    void intWirdUeberLongVerpackt() {
        assertEquals(42L, WrapperTage.alsLong(42));
        assertEquals(7L, WrapperTage.alsLong(7));
    }
}
