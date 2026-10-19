package de.ibb.streamuebung;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamAufgabenTest {

    @Test
    void filterMaxUndZaehlen() {
        assertEquals(List.of(12, 14, 16, 18, 20),
                StreamAufgaben.ueberFuenfVerdoppelt(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
        assertEquals(Optional.of("Alexandra"),
                StreamAufgaben.laengsterName(List.of("Max", "Anna", "Tom", "Alexandra", "Tim", "Christoph")));
        assertEquals(Optional.empty(), StreamAufgaben.laengsterName(List.of()));
        assertEquals(3, StreamAufgaben.beginneMitA(List.of("Max", "Anna", "Tom", "Alexandra", "Tim", "Alfred")));
    }
}
