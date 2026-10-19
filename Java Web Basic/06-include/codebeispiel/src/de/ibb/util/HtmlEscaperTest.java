package de.ibb.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HtmlEscaperTest {

    @Test
    @DisplayName("Null or empty input returns empty string")
    void handlesNullAndEmpty() {
        assertEquals("", HtmlEscaper.escape(null));
        assertEquals("", HtmlEscaper.escape(""));
    }

    @Test
    @DisplayName("Escapes the five basic HTML entities")
    void escapesBasicEntities() {
        assertEquals("&lt;div&gt;&amp;&quot;&#39;",
                HtmlEscaper.escape("<div>&\"'"));
    }

    @Test
    @DisplayName("Leaves safe text untouched")
    void leavesSafeText() {
        String text = "Hallo Welt! 123 äöü";
        assertEquals(text, HtmlEscaper.escape(text));
    }
}

