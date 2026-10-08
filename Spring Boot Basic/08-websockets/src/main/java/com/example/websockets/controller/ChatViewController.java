package com.example.websockets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Liefert die HTML-Seite des Chats über normales MVC.
 * Die Seite selbst öffnet danach die WebSocket-Verbindung. Der Nachrichtenfluss läuft nicht über diese Methode.
 */
@Controller
public class ChatViewController {

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }
}

