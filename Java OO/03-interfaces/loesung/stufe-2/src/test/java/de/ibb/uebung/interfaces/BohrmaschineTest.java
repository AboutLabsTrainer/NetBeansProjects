package de.ibb.uebung.interfaces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BohrmaschineTest {

    @Test
    void aufschlagAb1000Watt() {
        assertEquals(40.0, new Bohrmaschine("Klein", 800).berechneMietpreis(2), 0.001);
        assertEquals(50.0, new Bohrmaschine("ProDrill 500", 1200).berechneMietpreis(2), 0.001);
        assertEquals(0.0, new Bohrmaschine("ProDrill 500", 1200).berechneMietpreis(0), 0.001);
    }
}
