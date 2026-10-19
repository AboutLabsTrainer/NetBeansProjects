package de.ibb.uebung.fahrzeuge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MietpreisRechnerTest {

    @Test
    void castUndPreisWieInDerDemo() {
        short tage = MietpreisRechner.alsShort(5);
        assertEquals(5, tage);
        assertEquals(249.95, MietpreisRechner.berechneMietpreis(49.99, tage), 0.001);
    }
}
