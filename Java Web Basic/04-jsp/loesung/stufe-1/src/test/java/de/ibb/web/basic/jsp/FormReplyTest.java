package de.ibb.web.basic.jsp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FormReplyTest {
    @Test
    void thanksGuestOrName() {
        assertEquals("Danke, Gast", FormReply.thanks(null));
        assertEquals("Danke, Eva", FormReply.thanks(" Eva "));
    }
}
