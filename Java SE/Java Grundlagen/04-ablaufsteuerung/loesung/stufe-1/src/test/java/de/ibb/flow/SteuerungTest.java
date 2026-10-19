package de.ibb.flow;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class SteuerungTest {
    @Test void noten() {
        assertEquals("Sehr gut / Gut", Steuerung.notenText(1));
        assertEquals("Ausreichend", Steuerung.notenText(4));
        assertEquals("Nicht bestanden", Steuerung.notenText(6));
        assertEquals("Ungültige Note", Steuerung.notenText(0));
    }
    @Test void tage() {
        assertEquals("Montag", Steuerung.wochentag(1));
        assertEquals("Sonntag", Steuerung.wochentag(7));
        assertEquals("Ungültiger Tag", Steuerung.wochentag(8));
    }
}
