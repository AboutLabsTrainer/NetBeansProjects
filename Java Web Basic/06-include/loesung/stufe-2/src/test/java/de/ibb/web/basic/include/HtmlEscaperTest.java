package de.ibb.web.basic.include;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HtmlEscaperTest {
    @Test
    void escapesQuotes() {
        assertEquals("&quot;a&#39;", HtmlEscaper.escape("\"a'"));
    }
}
