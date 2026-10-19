package de.ibb.jdbc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    void withIdBehaeltDieFelder() {
        Person neu = new Person("Lisa Schmidt", 34, "lisa@example.com");
        assertNull(neu.id());
        Person gespeichert = neu.withId(5);
        assertEquals(5, gespeichert.id());
        assertEquals("Lisa Schmidt", gespeichert.name());
        assertEquals(34, gespeichert.alter());
        assertEquals("lisa@example.com", gespeichert.email());
    }
}
