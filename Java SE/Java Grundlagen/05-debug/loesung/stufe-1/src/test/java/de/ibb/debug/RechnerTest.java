package de.ibb.debug;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class RechnerTest {
    @Test void quadratUndSumme() {
        assertEquals(25, Rechner.quadrat(5));
        assertEquals(15, Rechner.summeBis(5));
        assertEquals(1, Rechner.summeBis(1));
    }
}
