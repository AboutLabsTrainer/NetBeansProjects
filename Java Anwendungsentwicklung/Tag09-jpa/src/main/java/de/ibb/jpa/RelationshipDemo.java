package de.ibb.jpa;

import de.ibb.jpa.entity.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * RelationshipDemo - Entity Relationships in JPA.
 * 
 * Tag 9 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * Zeigt:
 * - @OneToOne (Person → Adresse)
 * - @OneToMany / @ManyToOne (Person ↔ Bestellungen)
 * - Cascade-Operationen
 * - Lazy Loading
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public class RelationshipDemo {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  🔗 Entity Relationships Demo");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.ibb");
        EntityManager em = emf.createEntityManager();
        
        try {
            // ────────────────────────────────────────────────────────
            // @OneToOne: Person → Adresse
            // ────────────────────────────────────────────────────────
            
            System.out.println("🏠 @OneToOne: Person → Adresse");
            System.out.println("─────────────────────────────────────────────────────────");
            
            em.getTransaction().begin();
            
            Person max = new Person("Max Mustermann", 28, "max@example.com");
            Adresse adresse = new Adresse("Hauptstraße 1", "12345", "Berlin");
            
            max.setAdresse(adresse);  // Verknüpfung
            
            em.persist(max);  // Cascade: Adresse wird mit gespeichert!
            
            em.getTransaction().commit();
            
            System.out.println("   Person: " + max);
            System.out.println("   Adresse: " + max.getAdresse());
            System.out.println("   → Adresse wurde MIT Person gespeichert (Cascade)");
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // @OneToMany: Person → Bestellungen
            // ────────────────────────────────────────────────────────
            
            System.out.println("📦 @OneToMany: Person → Bestellungen");
            System.out.println("─────────────────────────────────────────────────────────");
            
            em.getTransaction().begin();
            
            // Bestellungen hinzufügen
            max.addBestellung(new Bestellung(
                LocalDate.now().minusDays(5), 
                new BigDecimal("99.99"), 
                "Laptop-Tasche"
            ));
            max.addBestellung(new Bestellung(
                LocalDate.now().minusDays(2), 
                new BigDecimal("29.99"), 
                "USB-Kabel"
            ));
            max.addBestellung(new Bestellung(
                LocalDate.now(), 
                new BigDecimal("149.99"), 
                "Tastatur"
            ));
            
            em.getTransaction().commit();
            
            System.out.println("   " + max.getBestellungen().size() + " Bestellungen hinzugefügt:");
            max.getBestellungen().forEach(b -> System.out.println("   → " + b));
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // Navigieren: Bestellung → Person
            // ────────────────────────────────────────────────────────
            
            System.out.println("🔄 @ManyToOne: Bestellung → Person");
            System.out.println("─────────────────────────────────────────────────────────");
            
            // Cache leeren für realistische Abfrage
            em.clear();
            
            // Bestellung laden
            Bestellung bestellung = em.createQuery(
                    "SELECT b FROM Bestellung b WHERE b.beschreibung = :beschr",
                    Bestellung.class)
                    .setParameter("beschr", "Tastatur")
                    .getSingleResult();
            
            System.out.println("   Bestellung: " + bestellung);
            System.out.println("   Gehört zu: " + bestellung.getPerson().getName());
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // JOIN FETCH: N+1 Problem vermeiden
            // ────────────────────────────────────────────────────────
            
            System.out.println("⚡ JOIN FETCH: Alle Daten in einer Query");
            System.out.println("─────────────────────────────────────────────────────────");
            
            em.clear();
            
            // OHNE JOIN FETCH: N+1 Queries!
            // MIT JOIN FETCH: 1 Query!
            List<Person> personenMitBestellungen = em.createQuery(
                    "SELECT DISTINCT p FROM Person p " +
                    "LEFT JOIN FETCH p.bestellungen " +
                    "LEFT JOIN FETCH p.adresse",
                    Person.class)
                    .getResultList();
            
            personenMitBestellungen.forEach(p -> {
                System.out.println("   " + p.getName() + ":");
                System.out.println("      Adresse: " + p.getAdresse());
                System.out.println("      Bestellungen: " + p.getBestellungen().size());
            });
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // Cascade DELETE: Person löschen → Bestellungen auch
            // ────────────────────────────────────────────────────────
            
            System.out.println("🗑️ Cascade DELETE:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            em.getTransaction().begin();
            
            Person zuLoeschen = em.find(Person.class, max.getId());
            int anzahlBestellungen = zuLoeschen.getBestellungen().size();
            
            em.remove(zuLoeschen);
            
            em.getTransaction().commit();
            
            System.out.println("   Person gelöscht");
            System.out.println("   → " + anzahlBestellungen + " Bestellungen wurden MIT gelöscht!");
            System.out.println("   → Adresse wurde MIT gelöscht!");
            
            // Prüfen
            Long anzahlPersonen = em.createQuery("SELECT COUNT(p) FROM Person p", Long.class)
                                    .getSingleResult();
            Long anzahlBest = em.createQuery("SELECT COUNT(b) FROM Bestellung b", Long.class)
                                .getSingleResult();
            
            System.out.println("   Verbleibend: " + anzahlPersonen + " Personen, " + anzahlBest + " Bestellungen");
            
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
        System.out.println("  💡 Wichtige Konzepte:");
        System.out.println("     • mappedBy = 'wer besitzt die Beziehung?'");
        System.out.println("     • CascadeType.ALL = Operationen weitergeben");
        System.out.println("     • orphanRemoval = Waisen löschen");
        System.out.println("     • JOIN FETCH = N+1 Problem vermeiden");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}
