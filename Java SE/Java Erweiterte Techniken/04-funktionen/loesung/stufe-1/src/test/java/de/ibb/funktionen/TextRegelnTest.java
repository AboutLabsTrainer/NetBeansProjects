package de.ibb.funktionen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextRegelnTest {

    @Test
    void predicateUndFunction() {
        assertTrue(TextRegeln.leerOderLang(""));
        assertFalse(TextRegeln.leerOderLang("Hallo"));
        assertTrue(TextRegeln.leerOderLang("Hallo Welt, das ist ein langer Text"));

        assertFalse(TextRegeln.gueltig(""));
        assertTrue(TextRegeln.gueltig("Hallo"));
        assertFalse(TextRegeln.gueltig("Hallo Welt, das ist ein langer Text"));

        assertEquals("HALLO WELT", TextRegeln.trimUndGross(" hallo welt "));
        assertEquals("Länge: 5", TextRegeln.laengeAlsText("  hallo  "));
    }
}
