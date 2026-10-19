package de.ibb.boot.basic.jaxrs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteResourceTest {
    @Test
    void listsNote() {
        assertEquals("Erste Notiz", new NoteResource().list());
    }

    @Test
    void addsNote() {
        NoteResource resource = new NoteResource();
        assertEquals("Erste Notiz,Zweite", resource.add("Zweite"));
    }

}
