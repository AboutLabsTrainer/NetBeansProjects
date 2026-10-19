package de.ibb.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ZahlenListeTest {

    @Test
    void paarUndMaximum() {
        Paar<String, Integer> paar = new Paar<>("id", 7);
        Paar<Integer, String> gedreht = paar.tauschen();
        assertEquals("id", paar.erstes());
        assertEquals(7, gedreht.erstes());
        assertEquals("id", gedreht.zweites());

        ZahlenListe<Integer> ganz = new ZahlenListe<>();
        ganz.add(10);
        ganz.add(3);
        ganz.add(8);
        assertEquals(21.0, ganz.summe(), 0.001);
        assertEquals(10, ganz.maximum());

        ZahlenListe<Double> kommazahlen = new ZahlenListe<>();
        kommazahlen.add(1.5);
        kommazahlen.add(2.5);
        assertEquals(4.0, kommazahlen.summe(), 0.001);
        assertEquals(2.5, kommazahlen.maximum());

        assertNull(new ZahlenListe<Integer>().maximum());
    }
}
