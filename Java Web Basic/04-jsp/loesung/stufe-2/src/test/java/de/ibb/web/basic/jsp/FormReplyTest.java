package de.ibb.web.basic.jsp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FormReplyTest {
    @Test
    void requiresCommentAndShortName() {
        assertEquals("Danke, Eva", FormReply.thanks("Eva", "passt"));
        assertEquals("Danke, Gast", FormReply.thanks(" ", "passt"));
        assertThrows(IllegalArgumentException.class, () -> FormReply.thanks("Eva", " "));
        assertThrows(IllegalArgumentException.class, () -> FormReply.thanks("A".repeat(41), "passt"));
    }
}
