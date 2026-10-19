package de.ibb.debugchallenge;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class SpanneTest {
    @Test void spanne() {
        assertEquals(45, Spanne.differenz(new int[] {5, 8, -3, 10, 42}));
        assertEquals(7, Spanne.differenz(new int[] {-3, -1, -8}));
    }
}
