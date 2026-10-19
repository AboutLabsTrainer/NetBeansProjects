package de.ibb.swingfarbmischer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HelligkeitTest {

    @Test
    void schwarzIstDunkelWeissNicht() {
        assertTrue(Helligkeit.dunkel(0, 0, 0));
        assertFalse(Helligkeit.dunkel(255, 255, 255));
    }
}
