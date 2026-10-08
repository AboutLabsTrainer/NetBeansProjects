package de.ibb.scopes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet das Beispiel zu den Bean-Scopes.
 * Dieselbe Zähler-Idee steckt in mehreren Beans. Unterschiedlich ist nur, wie lange der Container die Instanz behält.
 */
@SpringBootApplication
public class SpringScopesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringScopesApplication.class, args);
    }
}

