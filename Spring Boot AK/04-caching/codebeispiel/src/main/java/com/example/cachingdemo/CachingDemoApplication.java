package com.example.cachingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet das Caching-Beispiel.
 * Der Cache-Proxy um den Service entsteht erst, weil Caching aktiviert ist und der Aufruf über die Spring-Bean läuft.
 */
@SpringBootApplication
public class CachingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CachingDemoApplication.class, args);
    }
}
