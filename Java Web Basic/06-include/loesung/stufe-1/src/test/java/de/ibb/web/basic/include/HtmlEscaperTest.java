package de.ibb.web.basic.include;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HtmlEscaperTest {
    @Test
    void escapesAmpersandBeforeBrackets() {
        assertEquals("", HtmlEscaper.escape(null));
        assertEquals("a&amp;b&lt;c&gt;", HtmlEscaper.escape("a&b<c>"));
    }
}
