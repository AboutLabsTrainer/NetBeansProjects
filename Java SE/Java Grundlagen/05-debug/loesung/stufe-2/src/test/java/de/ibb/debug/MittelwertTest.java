package de.ibb.debug;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class MittelwertTest {
    @Test void mittel() {
        assertEquals(12.4, Mittelwert.durchschnitt(new int[] {5, 8, -3, 10, 42}), 0.0001);
        assertEquals(0, Mittelwert.durchschnitt(new int[] {}), 0.0001);
    }
}
