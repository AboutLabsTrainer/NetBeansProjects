package de.ibb.controlflow;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class KassenSummeTest {
    @Test void gruppenrabattNachTagesrabatt() {
        assertEquals(45, KassenSumme.gesamt(5, 10, false), 0.0001);
        assertEquals(36, KassenSumme.gesamt(5, 10, true), 0.0001);
        assertEquals(8, KassenSumme.gesamt(1, 10, true), 0.0001);
    }
}
