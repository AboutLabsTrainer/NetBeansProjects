package com.example.websockets.dto;

import java.time.LocalDateTime;

/**
 * Payload einer Chat-Nachricht zwischen Browser und Server.
 * Jackson wandelt das Objekt in JSON um, der Broker transportiert es unverändert an die Abonnenten.
 */
public class ChatMessage {

    private String content;
    private String sender;
    private MessageType type;
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * Unterscheidet Chat-Text, Beitritt und Verlassen.
     * Clients stellen daran dar, ob die Nachricht Inhalt oder ein Ereignis ist.
     */
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public ChatMessage() {
    }

    public ChatMessage(String content, String sender, MessageType type) {
        this.content = content;
        this.sender = sender;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

