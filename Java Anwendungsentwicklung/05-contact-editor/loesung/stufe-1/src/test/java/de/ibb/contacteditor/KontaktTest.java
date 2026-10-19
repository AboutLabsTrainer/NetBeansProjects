package de.ibb.contacteditor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class KontaktTest {

    @Test
    void nameUndMail() {
        assertTrue(new Kontakt("Ada", "ada@ibb.de").istVollstaendig());
        assertFalse(new Kontakt("  ", "ada@ibb.de").istVollstaendig());
        assertFalse(new Kontakt("Ada", "ohne-at").istVollstaendig());
        assertFalse(new Kontakt("Ada", null).istVollstaendig());
    }
}
