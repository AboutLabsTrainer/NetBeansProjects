package com.example.websockets.controller;

import com.example.websockets.dto.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * Nimmt Chat-Nachrichten vom STOMP-Client entgegen und schickt sie an alle Abonnenten von /topic/public.
 * Der Rückgabewert der Methode ist die Payload, die der Broker weiterleitet.
 */
@Controller
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        log.info("Received message from {}: {}", chatMessage.getSender(), chatMessage.getContent());
        chatMessage.setType(ChatMessage.MessageType.CHAT);
        return chatMessage;
    }

    @MessageMapping("/chat.join")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        if (headerAccessor.getSessionAttributes() != null) {
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        }

        log.info("User joined: {}", chatMessage.getSender());
        chatMessage.setType(ChatMessage.MessageType.JOIN);
        chatMessage.setContent(chatMessage.getSender() + " ist dem Chat beigetreten!");
        return chatMessage;
    }
}

