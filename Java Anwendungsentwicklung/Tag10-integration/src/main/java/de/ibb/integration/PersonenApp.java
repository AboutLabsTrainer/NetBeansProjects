package de.ibb.integration;

import com.formdev.flatlaf.FlatDarkLaf;
import de.ibb.integration.entity.Person;
import de.ibb.integration.repository.PersonRepository;
import de.ibb.integration.service.PersonService;
import de.ibb.integration.ui.*;
import jakarta.persistence.*;
import javax.swing.*;
import java.awt.*;

/**
 * PersonenApp - Die vollständige Integration.
 * 
 * Tag 10 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * Diese Anwendung zeigt die Schichtenarchitektur:
 * - UI (Swing) → Service → Repository → Database
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public class PersonenApp {
    
    public static void main(String[] args) {
        // Look & Feel
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Anwendung starten
        SwingUtilities.invokeLater(() -> {
            // ══════════════════════════════════════════════════════════
            // SCHICHTEN AUFBAUEN (Dependency Injection manuell)
            // ══════════════════════════════════════════════════════════
            
            // 1. Database Layer
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.ibb");
            
            // 2. Repository Layer
            PersonRepository repository = new PersonRepository(emf);
            
            // 3. Service Layer
            PersonService service = new PersonService(repository);
            
            // 4. Testdaten anlegen
            createTestData(service);
            
            // 5. UI Layer
            PersonenFrame frame = new PersonenFrame(service);
            frame.setVisible(true);
            
            // Cleanup beim Schließen
            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    emf.close();
                }
            });
        });
    }
    
    private static void createTestData(PersonService service) {
        service.save(new Person("Max Mustermann", 28, "max@example.com"));
        service.save(new Person("Lisa Schmidt", 34, "lisa@example.com"));
        service.save(new Person("Tom Müller", 22, "tom@example.com"));
        service.save(new Person("Anna Weber", 45, "anna@example.com"));
        service.save(new Person("Peter Fischer", 31, null));
    }
}
