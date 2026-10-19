package de.ibb.springbootbasic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * HelloController - Einfacher Welcome-Endpoint
 *
 * @RestController - Spring verwaltet diese Klasse als Bean
 * Demonstriert den einfachsten Controller
 */
@RestController
public class HelloController {

    /**
     * GET /
     * Root-Endpoint mit Willkommensnachricht
     *
     * curl http://localhost:8081/
     */
    @GetMapping("/")
    public Map<String, Object> welcome() {
        return Map.of(
                "message", "🚀 Willkommen bei Spring Boot Basic - Tag 2",
                "course", "Der Spring Container & Dependency Injection",
                "author", "Anna",
                "timestamp", LocalDateTime.now().toString(),
                "endpoints", Map.of(
                        "persons", "/api/persons",
                        "context", "/api/context",
                        "health", "/actuator/health (wenn aktiviert)"
                )
        );
    }

    /**
     * GET /api/hello
     * Alternativer Hello-Endpoint
     *
     * curl http://localhost:8081/api/hello
     */
    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Hello from Spring Boot!",
                "container", "Spring Container verwaltet diese Bean automatisch!",
                "di", "Constructor Injection FTW! 💪"
        );
    }
}
