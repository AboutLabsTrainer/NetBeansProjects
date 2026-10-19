package de.ibb.typelab;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
class KontoTest {
    @Test void bewegtDenStandNurWennErReicht() {
        Konto konto = new Konto("Petra", 100);
        konto.einzahlen(25);
        assertEquals(125, konto.getStand(), 0.0001);
        assertFalse(konto.abheben(200));
        assertEquals(125, konto.getStand(), 0.0001);
        assertTrue(konto.abheben(25));
        assertEquals(100, konto.getStand(), 0.0001);
    }
}
