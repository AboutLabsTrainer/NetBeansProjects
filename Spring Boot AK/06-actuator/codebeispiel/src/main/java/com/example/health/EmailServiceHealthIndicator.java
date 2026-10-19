package com.example.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;

/**
 * Health-Beitrag für den Mailversand.
 * Ob wirklich eine SMTP-Verbindung geprüft wird, steuert ein Property. Sonst meldet der Indikator nur, dass der Check aus ist.
 */
@Component
public class EmailServiceHealthIndicator implements HealthIndicator {

    private final JavaMailSender mailSender;
    private final boolean checkConnection;

    public EmailServiceHealthIndicator(JavaMailSender mailSender,
                                       @Value("${health.email.check-connection:false}") boolean checkConnection) {
        this.mailSender = mailSender;
        this.checkConnection = checkConnection;
    }

    @Override
    public Health health() {
        try {
            if (checkConnection && mailSender instanceof JavaMailSenderImpl impl) {
                impl.testConnection();
                return Health.up()
                        .withDetail("service", "Email Service")
                        .withDetail("status", "SMTP connection successful")
                        .build();
            }

            return Health.up()
                    .withDetail("service", "Email Service")
                    .withDetail("status", "Mail sender configured (connection check disabled)")
                    .withDetail("checkConnection", false)
                    .build();
        } catch (MessagingException e) {
            return Health.down()
                    .withDetail("service", "Email Service")
                    .withDetail("error", e.getMessage())
                    .withException(e)
                    .build();
        }
    }
}
