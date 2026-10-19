package com.example.personmanagement.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Liefert die Chat-Seite und verteilt STOMP-Nachrichten an /topic/public.
 * Die HTML-Seite kommt über MVC. Die Nachrichten laufen über den Broker, nicht über den View-Namen.
 */
@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        return message;
    }
}

