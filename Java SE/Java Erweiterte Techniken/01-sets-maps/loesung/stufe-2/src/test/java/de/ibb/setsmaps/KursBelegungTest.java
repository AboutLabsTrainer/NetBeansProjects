package de.ibb.setsmaps;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KursBelegungTest {

    @Test
    void nameStehtProKursNurEinmal() {
        KursBelegung belegung = new KursBelegung();

        assertTrue(belegung.anmelden("Java", "Mia"));
        assertFalse(belegung.anmelden("Java", "Mia"));
        assertTrue(belegung.anmelden("Java", "Leo"));
        assertTrue(belegung.anmelden("SQL", "Mia"));

        assertEquals(Set.of("Mia", "Leo"), belegung.teilnehmer("Java"));
        assertEquals(Set.of(), belegung.teilnehmer("Netz"));
        assertEquals(Set.of("Java", "SQL"), belegung.kurseVon("Mia"));
        assertEquals(Set.of("Java"), belegung.kurseVon("Leo"));
    }
}
