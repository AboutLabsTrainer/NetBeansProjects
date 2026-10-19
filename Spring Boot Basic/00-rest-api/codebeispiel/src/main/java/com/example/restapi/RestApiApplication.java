package com.example.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet die REST-API und damit den Spring-Container.
 * Ab diesem Paket werden Komponenten gefunden und der eingebettete Webserver nimmt HTTP-Anfragen entgegen.
 */
@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }
}
