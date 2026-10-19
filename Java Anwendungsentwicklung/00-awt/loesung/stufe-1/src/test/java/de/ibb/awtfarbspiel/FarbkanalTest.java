package de.ibb.awtfarbspiel;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FarbkanalTest {

    @Test
    void haeltKanaeleImFarbbereich() {
        assertEquals(0, Farbkanal.begrenze(-4));
        assertEquals(255, Farbkanal.begrenze(300));
        assertEquals(128, Farbkanal.begrenze(128));
        assertArrayEquals(new int[] {0, 255, 16}, Farbkanal.rgb(-1, 400, 16));
    }
}
