package com.example.websockets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet den STOMP-Chat.
 * Neben dem HTTP-Server steht der Message-Broker bereit, den die WebSocket-Konfiguration einrichtet.
 */
@SpringBootApplication
public class WebsocketsChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketsChatApplication.class, args);
    }
}

