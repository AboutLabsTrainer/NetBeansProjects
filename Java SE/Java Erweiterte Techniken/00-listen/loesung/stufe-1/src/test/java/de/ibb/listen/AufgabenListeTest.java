package de.ibb.listen;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AufgabenListeTest {

    @Test
    void verschiebtErledigteInDieZweiteListe() {
        AufgabenListe liste = new AufgabenListe();
        liste.hinzufuegen("Java-Artikel lesen");
        liste.hinzufuegen("Mini-Challenge nachbauen");

        assertEquals(List.of("Java-Artikel lesen", "Mini-Challenge nachbauen"), liste.offene());
        assertEquals(List.of(), liste.erledigte());

        assertTrue(liste.erledigen(0));
        assertEquals(List.of("Mini-Challenge nachbauen"), liste.offene());
        assertEquals(List.of("Java-Artikel lesen"), liste.erledigte());

        assertFalse(liste.erledigen(9));
        assertEquals(List.of("Mini-Challenge nachbauen"), liste.offene());

        assertTrue(liste.entfernen(0));
        assertEquals(List.of(), liste.offene());
        assertFalse(liste.entfernen(0));
        assertEquals(List.of("Java-Artikel lesen"), liste.erledigte());
    }
}
