package de.ibb.boot.basic.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/chat.join")
    @SendTo("/topic/public")
    public String join(String sender) {
        return ChatTexts.join(sender);
    }

}
