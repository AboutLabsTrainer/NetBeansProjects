package de.ibb.preis;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopRegelnTest {

    @Test
    void elektronikUndSkonto() {
        List<Artikel> artikel = List.of(
                new Artikel("Java-Buch", 29.90, "Buch"),
                new Artikel("Monitor 27\"", 199.00, "Elektronik"),
                new Artikel("Kaffeemaschine", 79.90, "Haushalt"),
                new Artikel("Maus", 19.99, "elektronik"),
                new Artikel("Roman", 12.50, "Buch"));
        assertEquals(List.of("Monitor 27\"", "Maus"), ShopRegeln.elektronikNamen(artikel));
        assertEquals(199 * 0.90 * 0.98, ShopRegeln.skontoWennGross(199), 1e-9);
        assertEquals(29.90 * 0.90, ShopRegeln.skontoWennGross(29.90), 1e-9);
    }
}
