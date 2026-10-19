package de.ibb.listen;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrioritaetsListeTest {

    @Test
    void lehntDuplikatAbUndSortiertNachPrioritaet() {
        PrioritaetsListe liste = new PrioritaetsListe();

        assertTrue(liste.hinzufuegen("Bug", Prioritaet.HIGH));
        assertTrue(liste.hinzufuegen("Doku", Prioritaet.LOW));
        assertFalse(liste.hinzufuegen("Bug", Prioritaet.MEDIUM));
        assertEquals(List.of("Bug", "Doku"), liste.sortiert());

        assertTrue(liste.hinzufuegen("Test", Prioritaet.MEDIUM));
        assertEquals(List.of("Bug", "Test", "Doku"), liste.sortiert());
        assertEquals(1, liste.anzahl(Prioritaet.HIGH));
        assertEquals(1, liste.anzahl(Prioritaet.MEDIUM));
        assertEquals(1, liste.anzahl(Prioritaet.LOW));
    }
}
