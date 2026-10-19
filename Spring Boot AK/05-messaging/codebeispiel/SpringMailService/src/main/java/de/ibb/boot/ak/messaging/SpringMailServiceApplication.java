package de.ibb.boot.ak.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Startet den Mail-Dienst und schaltet asynchrone Methoden ein.
 * SMTP zeigt in diesem Beispiel auf MailDev. Der Versand selbst steckt im EmailService.
 */
@SpringBootApplication
@EnableAsync
public class SpringMailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMailServiceApplication.class, args);
    }
}
