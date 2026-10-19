package de.ibb.boot.ak.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet die Personenanwendung mit Spring Security.
 * Die SecurityFilterChain aus SecurityConfig liegt vor den Controllern und entscheidet, welche Pfade anonym bleiben.
 */
@SpringBootApplication
public class PersonManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonManagementApplication.class, args);
    }
}
