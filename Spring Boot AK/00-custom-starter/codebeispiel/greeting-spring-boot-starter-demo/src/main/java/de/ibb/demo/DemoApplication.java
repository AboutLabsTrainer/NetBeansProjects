package de.ibb.demo;

import de.ibb.greeting.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Demo-Anwendung für den Greeting Spring Boot Starter.
 *
 * <p>Zeigt, dass {@link GreetingService} ohne manuelles {@code @Bean} oder
 * {@code @Import} verfügbar ist – die Auto-Configuration des Starters
 * stellt den Bean bereit.</p>
 */
@SpringBootApplication
public class DemoApplication {

    private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Nutzt den vom Starter auto-konfigurierten {@link GreetingService},
     * loggt Beispiel-Begrüßungen und den REST-Endpunkt.
     */
    @Bean
    CommandLineRunner demo(GreetingService greetingService) {
        return args -> {
            log.info("========================================");
            log.info("  Greeting Starter Demo");
            log.info("========================================");
            log.info("Test 1: {}", greetingService.greet("Petra"));
            log.info("Test 2: {}", greetingService.greet("Nova"));
            log.info("Test 3: {}", greetingService.greet("Elyndra"));
            log.info("========================================");
            log.info("  REST: http://localhost:8101/greet/{name}");
            log.info("========================================");
        };
    }
}
