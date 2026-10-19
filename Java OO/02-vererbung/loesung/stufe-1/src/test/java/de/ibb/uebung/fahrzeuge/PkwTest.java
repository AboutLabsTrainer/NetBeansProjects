package de.ibb.uebung.fahrzeuge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PkwTest {

    @Test
    void beschreibungNenntBasisUndTueren() {
        Pkw pkw = new Pkw("B-AB 1234", 2015, 5);
        assertEquals("=== PKW ===\nKennzeichen: B-AB 1234\nBaujahr: 2015\nAnzahl Türen: 5",
                pkw.beschreibung());
    }
}
