package de.ibb.uebung.fahrzeuge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LkwTest {

    @Test
    void beschreibungNenntLadung() {
        String text = new Lkw("B-XY 9999", 2018, 7.5).beschreibung();
        assertTrue(text.startsWith("=== LKW ==="));
        assertTrue(text.contains("Kennzeichen: B-XY 9999"));
        assertTrue(text.contains("Baujahr: 2018"));
        assertTrue(text.contains("max. Ladung: 7.5 t"));
    }
}
