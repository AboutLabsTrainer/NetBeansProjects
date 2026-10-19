package de.ibb.web.basic.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MethodLabelTest {
    @Test
    void mapsKnownMethods() {
        assertEquals("Lesen", MethodLabel.of("get"));
        assertEquals("Senden", MethodLabel.of("POST"));
        assertEquals("Ersetzen", MethodLabel.of("put"));
        assertEquals("Löschen", MethodLabel.of("delete"));
        assertEquals("Unbekannt", MethodLabel.of("PATCH"));
        assertEquals("Unbekannt", MethodLabel.of(null));
    }
}
