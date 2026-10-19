package de.ibb.workshop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
class NamensTeileTest {
    @Test void trenntUndSucht() {
        assertEquals("Max", NamensTeile.vorname("Max Mustermann"));
        assertEquals("Mustermann", NamensTeile.nachname("Max Mustermann"));
        assertTrue(NamensTeile.enthaelt("Java macht Spaß", "Java"));
    }
}
