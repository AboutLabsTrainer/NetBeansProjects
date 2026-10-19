package de.ibb.debugchallenge;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class AuswerterTest {
    @Test void werte() {
        int[] werte = {5, 8, -3, 10, 42};
        assertEquals(62, Auswerter.summe(werte));
        assertEquals(42, Auswerter.maximum(werte));
        assertEquals(12.4, Auswerter.durchschnitt(werte), 0.0001);
        assertEquals(3, Auswerter.indexVon(werte, 10));
        assertEquals(-1, Auswerter.indexVon(werte, 99));
        assertEquals(-1, Auswerter.maximum(new int[] {-8, -3, -1}));
    }
}
