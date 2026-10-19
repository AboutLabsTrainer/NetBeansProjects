package com.example.jaxrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet das Beispiel, in dem JAX-RS und Spring MVC nebeneinander laufen.
 * Jersey übernimmt die Ressourcen unter dem Application-Path, Spring MVC die übrigen Controller.
 */
@SpringBootApplication
public class JaxRsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaxRsApplication.class, args);
    }
}
