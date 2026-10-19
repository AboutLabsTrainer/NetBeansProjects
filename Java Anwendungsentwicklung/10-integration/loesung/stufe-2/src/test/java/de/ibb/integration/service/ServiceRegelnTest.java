package de.ibb.integration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ServiceRegelnTest {

    @Test
    void emailUndSuche() {
        assertEquals("ada@ibb.de", ServiceRegeln.normalisiereEmail("  Ada@IBB.de "));
        assertTrue(ServiceRegeln.alleLaden("   "));
        assertTrue(ServiceRegeln.alleLaden(null));
        assertFalse(ServiceRegeln.alleLaden("Ada"));
    }
}
