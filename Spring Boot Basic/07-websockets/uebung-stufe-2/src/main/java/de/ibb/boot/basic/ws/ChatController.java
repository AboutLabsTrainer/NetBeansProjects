package de.ibb.boot.basic.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {
    @MessageMapping("/chat.join")
    @SendTo("/topic/public")
    public String join(String sender) {
        return ChatTexts.join(sender);
    }

    @GetMapping("/api/preview")
    @ResponseBody
    public String preview(@RequestParam(defaultValue = "Ada") String sender) {
        // TODO: return ChatTexts.join(sender);
        return "";
    }

}
