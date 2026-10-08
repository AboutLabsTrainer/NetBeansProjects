package de.ibb.springbootbasic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ContextController - Debug-Controller für Spring Container
 *
 * Dieser Controller zeigt dir, was Spring alles im Hintergrund macht!
 * Du kannst sehen:
 * - Welche Beans Spring erstellt hat
 * - Wie viele Beans existieren
 * - Details zu einzelnen Beans
 *
 * ApplicationContext = Der Spring Container selbst!
 * Er verwaltet alle Beans und ihren Lifecycle.
 */
@RestController
@RequestMapping("/api/context")
@RequiredArgsConstructor
public class ContextController {

    /**
     * ApplicationContext wird von Spring selbst injiziert
     * Das ist der Container, der alle anderen Beans verwaltet!
     */
    private final ApplicationContext context;

    /**
     * GET /api/context/beans
     * Gibt ALLE Beans im Spring Container zurück (100+)
     *
     * curl http://localhost:8081/api/context/beans
     */
    @GetMapping("/beans")
    public List<String> getAllBeans() {
        System.out.println("🔍 Alle Beans im Container auflisten...");
        return Arrays.stream(context.getBeanDefinitionNames())
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * GET /api/context/my-beans
     * Gibt nur UNSERE eigenen Beans zurück
     *
     * curl http://localhost:8081/api/context/my-beans
     */
    @GetMapping("/my-beans")
    public List<String> getMyBeans() {
        System.out.println("🔍 Nur eigene Beans auflisten...");
        return Arrays.stream(context.getBeanDefinitionNames())
                .filter(name ->
                        name.contains("person") ||
                                name.contains("context") ||
                                name.contains("hello") ||
                                name.toLowerCase().contains("ibb")
                )
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * GET /api/context/bean-count
     * Wie viele Beans hat Spring erstellt?
     *
     * curl http://localhost:8081/api/context/bean-count
     */
    @GetMapping("/bean-count")
    public Map<String, Object> getBeanCount() {
        int totalBeans = context.getBeanDefinitionNames().length;
        System.out.println("📊 Spring Container hat " + totalBeans + " Beans erstellt!");

        return Map.of(
                "totalBeans", totalBeans,
                "message", "Spring Boot Auto-Configuration hat " + totalBeans + " Beans für dich erstellt!"
        );
    }

    /**
     * GET /api/context/bean-info/{beanName}
     * Details zu einer bestimmten Bean
     *
     * curl http://localhost:8081/api/context/bean-info/databaseService
     */
    @GetMapping("/bean-info/{beanName}")
    public Map<String, Object> getBeanInfo(@org.springframework.web.bind.annotation.PathVariable String beanName) {
        try {
            Object bean = context.getBean(beanName);
            return Map.of(
                    "beanName", beanName,
                    "beanClass", bean.getClass().getName(),
                    "isSingleton", context.isSingleton(beanName),
                    "isPrototype", context.isPrototype(beanName),
                    "exists", true
            );
        } catch (Exception e) {
            return Map.of(
                    "beanName", beanName,
                    "exists", false,
                    "error", "Bean nicht gefunden: " + e.getMessage()
            );
        }
    }

    /**
     * GET /api/context/service-implementations
     * Zeigt alle Implementierungen von PersonServiceInterface
     *
     * curl http://localhost:8081/api/context/service-implementations
     */
    @GetMapping("/service-implementations")
    public Map<String, Object> getServiceImplementations() {
        String[] serviceInterfaces = context.getBeanNamesForType(
                de.ibb.springbootbasic.service.PersonServiceInterface.class
        );

        return Map.of(
                "interface", "PersonServiceInterface",
                "implementations", Arrays.asList(serviceInterfaces),
                "count", serviceInterfaces.length
        );
    }
}
