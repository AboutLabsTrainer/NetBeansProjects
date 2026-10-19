package de.ibb.boot.basic.ws;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ChatTextsTest {
    @Autowired
    private ChatController controller;

    @Test
    void joinText() {
        assertEquals("Ada ist dem Chat beigetreten!", controller.join("Ada"));
    }
}
