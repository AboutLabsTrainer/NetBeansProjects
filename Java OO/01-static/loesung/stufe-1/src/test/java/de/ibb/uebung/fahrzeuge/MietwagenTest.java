package de.ibb.uebung.fahrzeuge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MietwagenTest {

    @Test
    void zaehlerUndBrutto() {
        int vorher = Mietwagen.getAnzahl();
        Mietwagen a = new Mietwagen("B-AB 1234", 49.99);
        new Mietwagen("B-CD 4321", 59.99);
        assertEquals(vorher + 2, Mietwagen.getAnzahl());
        assertEquals(178.4643, a.berechneMietpreisBrutto(3), 0.001);
        assertEquals(0.0, Mietwagen.berechneMietpreisBrutto(39.99, 0), 0.001);
        assertEquals(237.9405, Mietwagen.berechneMietpreisBrutto(39.99, 5), 0.001);
    }
}
