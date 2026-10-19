package de.ibb.notebook;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfilTest {
    @Test
    void beschreibungNenntNameUndAlter() {
        assertEquals("Name: Petra, Alter: 30", new Profil("Petra", 30).beschreibung());
    }

    @Test
    void kreisUmfangFuerRadiusFuenf() {
        assertEquals(31.4159, Profil.kreisUmfang(5), 0.0001);
    }
}
