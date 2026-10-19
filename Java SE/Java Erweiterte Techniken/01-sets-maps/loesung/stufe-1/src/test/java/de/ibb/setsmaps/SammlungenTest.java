package de.ibb.setsmaps;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SammlungenTest {

    @Test
    void staedteUndWoerter() {
        assertEquals(Set.of("Berlin", "Hamburg"),
                Sammlungen.staedte(java.util.List.of("Berlin", "Hamburg", "Berlin")));

        Map<String, Integer> zaehlung = Sammlungen.wortHaeufigkeit(
                "Java ist eine Programmiersprache. Java ist objektorientiert.");
        assertEquals(2, zaehlung.get("java"));
        assertEquals(2, zaehlung.get("ist"));
        assertEquals(1, zaehlung.get("eine"));
        assertEquals(1, zaehlung.get("programmiersprache"));
        assertEquals(1, zaehlung.get("objektorientiert"));
        assertEquals(5, zaehlung.size());
    }
}
