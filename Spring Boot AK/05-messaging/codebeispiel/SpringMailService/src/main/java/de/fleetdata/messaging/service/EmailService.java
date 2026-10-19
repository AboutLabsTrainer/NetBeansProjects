package de.fleetdata.messaging.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

/**
 * Versendet Mails über den JavaMailSender aus der Auto-Configuration.
 * Die asynchrone Methode läuft auf dem Executor, weil Async auf der Anwendung aktiv ist.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.from:noreply@java-developer.online}")
    private String fromEmail;

    public void sendSimpleEmail(String to, String subject, String text) {
        log.info("Sending simple email to: {}", to);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
        log.info("Email sent to: {}", to);
    }

    public void sendHtmlEmail(String to, String subject, String username, String email) {
        log.info("Sending HTML email to: {}", to);

        try {
            Context context = new Context();
            context.setVariable("username", username);
            context.setVariable("email", email);

            String htmlContent = templateEngine.process("email/welcome", context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);
            log.info("HTML email sent to: {}", to);

        } catch (MessagingException e) {
            log.error("Failed to send email: {}", e.getMessage());
            throw new RuntimeException("Email sending failed", e);
        }
    }

    @Async
    public void sendWelcomeEmailAsync(String to, String subject, String username) {
        log.info("[ASYNC] Starting async email to: {}", to);

        try {
            Thread.sleep(2000); // Simuliert lange Verarbeitung
            sendHtmlEmail(to, subject, username, to);
            log.info("[ASYNC] Email sent!");
        } catch (Exception e) {
            log.error("[ASYNC] Failed: {}", e.getMessage());
        }
    }
}
