package de.ibb.fenstertrainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StatuszeileTest {

    @Test
    void textWieImPanel() {
        assertEquals("Mausklick bei (12, 34)", Statuszeile.fuerKlick(12, 34));
    }
}
