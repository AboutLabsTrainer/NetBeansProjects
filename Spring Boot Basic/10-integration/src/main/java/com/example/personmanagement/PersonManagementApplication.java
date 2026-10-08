package com.example.personmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet das Abschlussbeispiel, das die vorherigen Themen in einer Anwendung zusammenführt.
 * MVC, REST, JAX-RS, ein Session-Scope, AOP und STOMP teilen sich denselben Container.
 */
@SpringBootApplication
public class PersonManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonManagementApplication.class, args);
    }
}

