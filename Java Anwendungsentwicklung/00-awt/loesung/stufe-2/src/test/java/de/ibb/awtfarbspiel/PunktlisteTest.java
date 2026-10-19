package de.ibb.awtfarbspiel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PunktlisteTest {

    @Test
    void merktUndLoeschtPunkte() {
        Punktliste liste = new Punktliste();
        liste.hinzufuegen(10, 20);
        liste.hinzufuegen(30, 40);
        assertEquals(2, liste.anzahl());
        liste.loeschen();
        assertEquals(0, liste.anzahl());
    }
}
