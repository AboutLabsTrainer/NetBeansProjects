package de.ibb.contacteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class EmailNormTest {

    @Test
    void trimUndKlein() {
        assertEquals("ada@ibb.de", EmailNorm.normalisiere("  Ada@IBB.de "));
        assertNull(EmailNorm.normalisiere(null));
    }
}
