package de.ibb.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JpqlKatalogTest {

    @Test
    void texteAusDerDemo() {
        assertEquals("SELECT p FROM Person p WHERE p.alter > :minAlter ORDER BY p.alter", JpqlKatalog.aelterAls());
        assertEquals("SELECT COUNT(p) FROM Person p", JpqlKatalog.anzahl());
    }
}
