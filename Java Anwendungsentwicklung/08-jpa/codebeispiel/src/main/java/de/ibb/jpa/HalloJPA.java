package de.ibb.jpa;

import de.ibb.jpa.entity.Person;
import jakarta.persistence.*;

/**
 * HalloJPA - Deine erste JPA-Anwendung.
 * 
 * Tag 8 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * Zeigt die Grundbausteine von JPA:
 * - EntityManagerFactory (einmal pro Anwendung)
 * - EntityManager (einmal pro "Unit of Work")
 * - Entity (Person)
 * - persist(), find(), remove()
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public class HalloJPA {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  🚀 HALLO JPA - Deine erste JPA-Anwendung");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        // ══════════════════════════════════════════════════════════════
        // EntityManagerFactory - EINMAL pro Anwendung!
        // ══════════════════════════════════════════════════════════════
        
        // Der Name muss mit persistence.xml übereinstimmen
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.ibb");
        
        System.out.println("✅ EntityManagerFactory erstellt");
        System.out.println("   (Das ist teuer - nur EINMAL machen!)");
        System.out.println();
        
        // ══════════════════════════════════════════════════════════════
        // EntityManager - für jede "Arbeitseinheit"
        // ══════════════════════════════════════════════════════════════
        
        EntityManager em = emf.createEntityManager();
        
        try {
            // ──────────────────────────────────────────────────────────
            // SPEICHERN (persist)
            // ──────────────────────────────────────────────────────────
            
            System.out.println("📝 SPEICHERN - Neue Person anlegen:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            // Transaktion starten
            em.getTransaction().begin();
            
            // Neue Person erstellen (noch nicht in DB!)
            Person max = new Person("Max Mustermann", 28, "max@example.com");
            System.out.println("   Vor persist: " + max);
            System.out.println("   ID ist noch: " + max.getId());
            
            // persist() = "JPA, kümmere dich um dieses Objekt!"
            em.persist(max);
            System.out.println("   Nach persist: JPA 'kennt' das Objekt jetzt");
            
            // Transaktion abschließen → INSERT wird ausgeführt!
            em.getTransaction().commit();
            System.out.println("   Nach commit: " + max);
            System.out.println("   ID ist jetzt: " + max.getId() + " (von DB generiert!)");
            System.out.println();
            
            // ──────────────────────────────────────────────────────────
            // LADEN (find)
            // ──────────────────────────────────────────────────────────
            
            System.out.println("🔍 LADEN - Person nach ID suchen:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            // find() = "Gib mir die Person mit ID X"
            Person gefunden = em.find(Person.class, max.getId());
            System.out.println("   Gefunden: " + gefunden);
            
            // Sind es die gleichen Objekte?
            System.out.println("   max == gefunden: " + (max == gefunden));
            System.out.println("   (JPA cached Entities im EntityManager!)");
            System.out.println();
            
            // ──────────────────────────────────────────────────────────
            // AKTUALISIEREN (automatisch!)
            // ──────────────────────────────────────────────────────────
            
            System.out.println("✏️ AKTUALISIEREN - Einfach Setter aufrufen:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            em.getTransaction().begin();
            
            // Einfach den Setter aufrufen - JPA bemerkt die Änderung!
            gefunden.setAlter(29);
            gefunden.setEmail("max.neu@example.com");
            
            System.out.println("   Geändert: " + gefunden);
            System.out.println("   (Kein merge() nötig - JPA trackt Änderungen!)");
            
            em.getTransaction().commit();
            System.out.println("   UPDATE wurde automatisch ausgeführt!");
            System.out.println();
            
            // ──────────────────────────────────────────────────────────
            // LÖSCHEN (remove)
            // ──────────────────────────────────────────────────────────
            
            System.out.println("🗑️ LÖSCHEN - Person entfernen:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            em.getTransaction().begin();
            
            em.remove(gefunden);
            System.out.println("   Person zum Löschen markiert");
            
            em.getTransaction().commit();
            System.out.println("   DELETE wurde ausgeführt!");
            
            // Prüfen
            Person nochmal = em.find(Person.class, max.getId());
            System.out.println("   Nochmal suchen: " + nochmal);
            
        } catch (RuntimeException ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
            emf.close();
        }
        
        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  💡 Beachte: EclipseLink hat das SQL generiert!");
        System.out.println("     Du hast KEIN SQL geschrieben!");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}
