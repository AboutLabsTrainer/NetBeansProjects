package de.ibb.notebook;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TemperaturTest {
    @Test
    void rechnetFahrenheit() {
        assertEquals(32, Temperatur.celsiusNachFahrenheit(0), 0.0001);
        assertEquals(212, Temperatur.celsiusNachFahrenheit(100), 0.0001);
    }

    @Test
    void nenntKategorie() {
        assertEquals("kalt", Temperatur.kategorie(-1));
        assertEquals("mild", Temperatur.kategorie(10));
        assertEquals("warm", Temperatur.kategorie(25));
    }
}
