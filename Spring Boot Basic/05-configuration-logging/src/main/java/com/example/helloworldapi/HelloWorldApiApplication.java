package com.example.helloworldapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet das Beispiel zu Konfiguration, Profilen und Logging.
 * Welches Profil aktiv ist, steht in den Properties und entscheidet, welche profilbezogenen Beans und Werte geladen werden.
 */
@SpringBootApplication
public class HelloWorldApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApiApplication.class, args);
    }
}
