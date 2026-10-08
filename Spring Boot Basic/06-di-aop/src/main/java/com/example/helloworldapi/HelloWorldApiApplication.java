package com.example.helloworldapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet das Beispiel zu Dependency Injection und AOP.
 * Aspect-Beans im Unterpaket werden mitgescannt und legen sich um die markierten Service-Aufrufe.
 */
@SpringBootApplication
public class HelloWorldApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApiApplication.class, args);
    }
}
