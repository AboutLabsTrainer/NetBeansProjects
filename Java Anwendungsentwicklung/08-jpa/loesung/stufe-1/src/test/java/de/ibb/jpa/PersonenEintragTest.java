package de.ibb.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PersonenEintragTest {

    @Test
    void formatWieDieEntity() {
        PersonenEintrag person = new PersonenEintrag(4L, "Max Mustermann", 28, "max@example.com");
        assertEquals("Person[id=4, name=Max Mustermann, alter=28, email=max@example.com]", person.anzeige());
    }
}
