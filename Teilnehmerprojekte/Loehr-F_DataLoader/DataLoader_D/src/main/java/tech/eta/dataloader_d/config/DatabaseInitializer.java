package tech.eta.dataloader_d.config;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


//* Spring Boot Components (like CommandLineRunner or custom @Component services)
//* are managed and executed as follows:
//* 
//* 1. DEFINITION: 
//*    - Use @Component, @Service, or @Repository on a class to let Spring's Component Scan 
//*      instantiate it automatically as a Bean.
//*    - Alternatively, use @Bean inside a @Configuration class to define it programmatically.
//* 
//* 2. EXECUTION / CALLING:
//*    - Infrastructure Runners: Interfaces like CommandLineRunner or ApplicationRunner 
//*      are automatically discovered by Spring Boot and their run() method is triggered 
//*      exactly once immediately after the application startup sequence completes.
//*    - Standard Business Logic: Custom component methods are called dynamically at runtime 
//*      by injecting the Bean into other classes (e.g., via Constructor Injection into a @RestController).
//*



@Component
// Läuft NUR an, wenn der Datenbank-Modus aktiv ist
@ConditionalOnProperty(name = "app.auth.mode", havingValue = "database", matchIfMissing = true)
public class DatabaseInitializer implements CommandLineRunner {

    private final EntityManager entityManager;

    public DatabaseInitializer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void run(String @NonNull ... args) throws Exception { // currently handled by the start-up script "data.sql"
    //    // Hier fügst du deine Standard-User direkt per Java-Code beim Start ein
    //    UserDB admin = new UserDB();
    //    admin.setUsername("admin");
    //    admin.setPassword("$2a$10$e0MszvFw6l8zM2w.yWqYI.9tLpbyK7r97YshXg9bNfE3UvL8Tve4S"); // password123
    //    admin.getRoles().addAll(List.of(UserRole.ADMIN, UserRole.USER));
    //
    //    entityManager.persist(admin);
    //    System.out.println("H2-Datenbank erfolgreich mit Standard-Usern initialisiert.");
    }
}

/* CURRENTLY DISABLED
//*/
