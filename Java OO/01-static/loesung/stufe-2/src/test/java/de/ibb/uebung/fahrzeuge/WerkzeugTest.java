package de.ibb.uebung.fahrzeuge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class WerkzeugTest {

    @Test
    void mieteZaehltNichtDoppelt() {
        int vorher = Werkzeug.getAnzahlVermietet();
        Werkzeug bohr = new Werkzeug();
        Werkzeug saege = new Werkzeug();
        bohr.miete();
        bohr.miete();
        saege.miete();
        assertEquals(vorher + 2, Werkzeug.getAnzahlVermietet());
        bohr.rueckgabe();
        bohr.rueckgabe();
        assertEquals(vorher + 1, Werkzeug.getAnzahlVermietet());
    }
}
