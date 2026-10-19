package de.ibb.uebung.interfaces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MietpreisTest {

    @Test
    void autoUndSaison() {
        Auto auto = new Auto("B-AB 1234", 59.99);
        assertEquals(179.97, auto.berechneMietpreis(3), 0.001);
        assertEquals(214.1643, auto.berechneMietpreisMitMwst(3), 0.001);
        assertEquals(0.0, auto.berechneMietpreis(0), 0.001);
        assertEquals(1.2, Saison.HAUPTSAISON.getFaktor(), 0.001);
        assertEquals(0.0, Saison.HAUPTSAISON.berechneRabattProzent(7), 0.001);
        assertEquals(10.0, Saison.NEBENSAISON.berechneRabattProzent(5), 0.001);
        assertEquals(0.0, Saison.NEBENSAISON.berechneRabattProzent(4), 0.001);
        assertEquals(5.0, Saison.STANDARD.berechneRabattProzent(7), 0.001);
        assertEquals(0.0, Saison.STANDARD.berechneRabattProzent(6), 0.001);
    }
}
