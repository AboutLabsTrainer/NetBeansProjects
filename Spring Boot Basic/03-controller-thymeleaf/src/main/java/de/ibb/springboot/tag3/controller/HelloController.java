package de.ibb.springboot.tag3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController - Einfacher Test-Controller aus Tag 1
 *
 * @author Java Fleet Systems
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World from Spring Boot! 🚀";
    }
}
