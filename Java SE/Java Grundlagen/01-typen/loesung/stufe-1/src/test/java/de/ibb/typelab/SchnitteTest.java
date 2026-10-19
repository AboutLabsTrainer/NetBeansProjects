package de.ibb.typelab;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class SchnitteTest {
    @Test void schneidetAb() { assertEquals(19, Schnitte.alsInt(19.99)); }
    @Test void addiertNachLong() { assertEquals(734_400L, Schnitte.summe(350_000, 384_400L)); }
}
