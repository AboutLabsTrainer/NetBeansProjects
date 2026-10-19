package de.ibb.springbootbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootBasicTag2Application - Main Entry Point
 *
 * @SpringBootApplication ist eine Kombinierte Annotation aus:
 * - @SpringBootConfiguration (erweitert @Configuration)
 * - @EnableAutoConfiguration (Auto-Configuration aktivieren)
 * - @ComponentScan (Scannt alle Packages ab hier nach @Component, @Service, etc.)
 *
 * Was passiert beim Start?
 * 1. Spring Boot scannt alle Packages ab de.ibb.springbootbasic
 * 2. Findet alle @Component, @Service, @Controller, @RestController
 * 3. Erstellt für jede Annotation eine Bean im Container
 * 4. Löst Dependencies auf (Dependency Injection)
 * 5. Ruft @PostConstruct Methoden auf
 * 6. Startet Tomcat Server
 * 7. App ist bereit!
 */
@SpringBootApplication
public class SpringBootBasicTag2Application {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║        🚀 Spring Boot Basic - Tag 2                       ║");
        System.out.println("║        Der Spring Container & Dependency Injection        ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("🔧 Spring Container wird gestartet...");
        System.out.println("📦 Beans werden erstellt und injiziert...");
        System.out.println();

        SpringApplication.run(SpringBootBasicTag2Application.class, args);

        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ ✅ Spring Boot Application erfolgreich gestartet!          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("📍 Verfügbare Endpoints:");
        System.out.println("   🏠 http://localhost:8081/");
        System.out.println("   👥 http://localhost:8081/api/persons");
        System.out.println("   🔍 http://localhost:8081/api/context/my-beans");
        System.out.println();
        System.out.println("💡 Tipp: Öffne einen zweiten Terminal und probiere:");
        System.out.println("   curl http://localhost:8081/api/persons");
        System.out.println("   curl http://localhost:8081/api/context/bean-count");
        System.out.println();
    }
}
