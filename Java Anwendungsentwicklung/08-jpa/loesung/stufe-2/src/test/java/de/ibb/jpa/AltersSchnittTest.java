package de.ibb.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class AltersSchnittTest {

    @Test
    void mittelwertDerAlter() {
        assertEquals(31.0, AltersSchnitt.durchschnitt(List.of(28, 34)), 0.001);
        assertEquals(0.0, AltersSchnitt.durchschnitt(List.of()), 0.001);
    }
}
