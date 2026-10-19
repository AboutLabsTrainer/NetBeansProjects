package com.example.websockets.listener;

import com.example.websockets.dto.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Reagiert auf Verbindungsereignisse des STOMP-Endpunkts.
 * Beim Trennen der Session schickt er eine Leave-Nachricht an das Topic, damit die anderen Clients den Abgang sehen.
 */
@Component
public class WebSocketEventListener {

    private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);

    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("New WebSocket connection established");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        if (headerAccessor.getSessionAttributes() == null) {
            return;
        }

        Object usernameObj = headerAccessor.getSessionAttributes().get("username");
        if (!(usernameObj instanceof String username) || username.isBlank()) {
            return;
        }

        log.info("User disconnected: {}", username);

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setType(ChatMessage.MessageType.LEAVE);
        chatMessage.setSender(username);
        chatMessage.setContent(username + " hat den Chat verlassen!");

        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }
}

