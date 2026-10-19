package de.ibb.boot.ak.messaging.controller;

import de.ibb.boot.ak.messaging.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * HTTP-Einstieg für Text-, HTML- und asynchrone Mails.
 * Der Controller wartet nur bei den synchronen Methoden auf den SMTP-Versand.
 */
@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/send")
    public ResponseEntity<Map<String, String>> sendSimple(
            @RequestParam String to) {
        emailService.sendSimpleEmail(
                to,
                "Test Email",
                "Hallo! Dies ist eine einfache Text-Email."
        );
        return ResponseEntity.ok(Map.of(
                "status", "SENT",
                "to", to,
                "type", "simple"
        ));
    }

    @GetMapping("/html")
    public ResponseEntity<Map<String, String>> sendHtml(
            @RequestParam String to,
            @RequestParam(defaultValue = "Gast") String name) {
        emailService.sendHtmlEmail(to, "Willkommen!", name, to);
        return ResponseEntity.ok(Map.of(
                "status", "SENT",
                "to", to,
                "type", "html"
        ));
    }

    @GetMapping("/async")
    public ResponseEntity<Map<String, String>> sendAsync(
            @RequestParam String to,
            @RequestParam(defaultValue = "Gast") String name) {
        emailService.sendWelcomeEmailAsync(to, "Willkommen!", name);
        return ResponseEntity.ok(Map.of(
                "status", "QUEUED",
                "to", to,
                "type", "async",
                "message", "Email wird im Hintergrund versendet!"
        ));
    }
}
