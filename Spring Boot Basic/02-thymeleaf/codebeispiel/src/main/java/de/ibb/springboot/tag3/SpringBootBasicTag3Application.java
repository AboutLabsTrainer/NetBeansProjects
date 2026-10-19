package de.ibb.springboot.tag3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application - Tag 3
 * Thymeleaf & MVC Views
 *
 * Von JSON zu HTML - Menschen-freundliche Webseiten!
 *
 * @author Java Fleet Systems - Elyndra Valen
 */
@SpringBootApplication
public class SpringBootBasicTag3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBasicTag3Application.class, args);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 Spring Boot Basic - Tag 3 gestartet!");
        System.out.println("=".repeat(60));
        System.out.println("\n📍 Verfügbare Endpoints:\n");
        System.out.println(" HTML Views (für Menschen):");
        System.out.println(" → http://localhost:8081/");
        System.out.println(" → http://localhost:8081/persons");
        System.out.println(" → http://localhost:8081/persons/1");
        System.out.println("\n REST API (für Programme):");
        System.out.println(" → http://localhost:8081/api/persons");
        System.out.println(" → http://localhost:8081/api/persons/1");
        System.out.println("\n Test:");
        System.out.println(" → http://localhost:8081/hello");
        System.out.println("\n" + "=".repeat(60));
        System.out.println("💡 Tipp: DevTools aktiviert - Code-Änderungen werden");
        System.out.println(" automatisch neu geladen!");
        System.out.println("=".repeat(60) + "\n");
    }
}
