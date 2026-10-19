package com.example.personmanagement.websocket;

/**
 * Payload einer Chat-Nachricht im Integrationsbeispiel.
 * Der Broker schickt das Objekt als JSON an alle Abonnenten des Topics.
 */
public class ChatMessage {

    private String from;
    private String text;

    public ChatMessage() {
    }

    public ChatMessage(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

