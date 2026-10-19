package de.ibb.integration.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PersonPruefungTest {

    @Test
    void gueltigePerson() {
        assertDoesNotThrow(() -> PersonPruefung.pruefe("Ada", 30, "ada@ibb.de"));
        assertDoesNotThrow(() -> PersonPruefung.pruefe("Ada", null, null));
        assertDoesNotThrow(() -> PersonPruefung.pruefe("Ada", 0, ""));
    }

    @Test
    void meldungenWieImService() {
        assertEquals("Name darf nicht leer sein!",
                assertThrows(IllegalArgumentException.class, () -> PersonPruefung.pruefe("  ", 30, null)).getMessage());
        assertEquals("Name darf max. 100 Zeichen haben!",
                assertThrows(IllegalArgumentException.class,
                        () -> PersonPruefung.pruefe("A".repeat(101), 30, null)).getMessage());
        assertEquals("Alter muss zwischen 0 und 150 liegen!",
                assertThrows(IllegalArgumentException.class, () -> PersonPruefung.pruefe("Ada", 151, null)).getMessage());
        assertEquals("Ungültige E-Mail-Adresse!",
                assertThrows(IllegalArgumentException.class, () -> PersonPruefung.pruefe("Ada", 30, "ohne-at")).getMessage());
    }
}
