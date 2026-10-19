package de.ibb.uebung.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AngebotszeileTest {

    @Test
    void liestGueltigeZeile() {
        Angebotszeile zeile = Angebotszeile.parse("Auto;49.99;3");
        assertEquals("Auto", zeile.bezeichnung());
        assertEquals(49.99, zeile.preisProTag(), 0.001);
        assertEquals(3, zeile.tage());
    }

    @Test
    void meldetFormatfehler() {
        assertThrows(IllegalArgumentException.class, () -> Angebotszeile.parse("KaputterEintrag;xx;3"));
        assertThrows(IllegalArgumentException.class, () -> Angebotszeile.parse("nur-eins"));
    }
}
