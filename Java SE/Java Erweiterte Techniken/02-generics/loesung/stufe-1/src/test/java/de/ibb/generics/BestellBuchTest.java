package de.ibb.generics;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BestellBuchTest {

    @Test
    void summeUndFilter() {
        BestellBuch summe = new BestellBuch();
        summe.hinzufuegen(new Posten("Produkt A", 100));
        summe.hinzufuegen(new Posten("Produkt B", 200));
        summe.hinzufuegen(new Posten("Produkt C", 300));
        assertEquals(600.0, summe.gesamtwert(), 0.001);

        BestellBuch filter = new BestellBuch();
        filter.hinzufuegen(new Posten("Guenstig", 10));
        filter.hinzufuegen(new Posten("Mittel", 50));
        filter.hinzufuegen(new Posten("Teuer", 100));
        List<Posten> teuer = filter.ueber(40);
        assertEquals(2, teuer.size());
        assertEquals("Mittel", teuer.get(0).getProdukt());
        assertEquals(50.0, teuer.get(0).getPreis(), 0.001);
        assertEquals("Teuer", teuer.get(1).getProdukt());

        assertEquals(0.0, new BestellBuch().gesamtwert(), 0.001);
    }
}
