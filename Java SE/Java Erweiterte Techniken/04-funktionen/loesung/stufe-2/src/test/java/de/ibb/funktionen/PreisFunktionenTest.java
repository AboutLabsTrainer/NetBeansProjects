package de.ibb.funktionen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PreisFunktionenTest {

    @Test
    void produktSpanneUndText() {
        assertEquals(19.99 * 3, PreisFunktionen.gesamt(19.99, 3), 1e-9);
        assertEquals("59.97 EUR", PreisFunktionen.betragText(19.99, 3));
        assertTrue(PreisFunktionen.istMittel(20));
        assertTrue(PreisFunktionen.istMittel(50));
        assertTrue(PreisFunktionen.istMittel(79.90));
        assertTrue(PreisFunktionen.istMittel(80));
        assertFalse(PreisFunktionen.istMittel(19.99));
        assertFalse(PreisFunktionen.istMittel(80.01));
        assertFalse(PreisFunktionen.istMittel(199));
    }
}
