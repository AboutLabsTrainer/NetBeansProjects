package com.example.helloworldapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet das Personenbeispiel zu Formularen und dem MVC-Muster.
 * Der Container scannt ab diesem Paket und der eingebettete Server liefert die Thymeleaf-Seiten aus.
 */
@SpringBootApplication
public class HelloWorldApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApiApplication.class, args);
    }
}
