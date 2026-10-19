package de.ibb.tag4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BuchZeileTest {

    @Test
    void spaltenWieDasTableModel() {
        assertEquals("Insel", BuchZeile.wert("Insel", "Ullenboom", 2023, 49.9, true, 0));
        assertEquals("Ullenboom", BuchZeile.wert("Insel", "Ullenboom", 2023, 49.9, true, 1));
        assertEquals(2023, BuchZeile.wert("Insel", "Ullenboom", 2023, 49.9, true, 2));
        assertEquals(49.9, (Double) BuchZeile.wert("Insel", "Ullenboom", 2023, 49.9, true, 3), 0.001);
        assertEquals(true, BuchZeile.wert("Insel", "Ullenboom", 2023, 49.9, true, 4));
    }
}
