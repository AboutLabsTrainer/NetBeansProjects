package com.example.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kleinster REST-Einstieg, um zu sehen, dass die Anwendung läuft.
 * Die Methode liefert Text, Spring MVC serialisiert ihn als HTTP-Antwort.
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
