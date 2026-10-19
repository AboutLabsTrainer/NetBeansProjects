package de.ibb.boot.basic.jaxrs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteResourceTest {
    @Test
    void listsNote() {
        assertEquals("Erste Notiz", new NoteResource().list());
    }

}
