package de.ibb.jpa;

import de.ibb.jpa.entity.Person;
import jakarta.persistence.*;
import java.util.List;

/**
 * JpqlDemo - JPQL (Java Persistence Query Language) in Aktion.
 * 
 * Tag 9 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * JPQL ist wie SQL, aber für Objekte:
 * - SELECT p FROM Person p (nicht: SELECT * FROM personen)
 * - Arbeitet mit Entity-Namen, nicht Tabellennamen
 * - Datenbank-unabhängig
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public class JpqlDemo {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  📝 JPQL Demo - Objekt-orientierte Abfragen");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.ibb");
        EntityManager em = emf.createEntityManager();
        
        try {
            // Testdaten anlegen
            setupTestData(em);
            
            // ────────────────────────────────────────────────────────
            // 1. Alle laden
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 1. SELECT p FROM Person p:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            List<Person> alle = em.createQuery("SELECT p FROM Person p", Person.class)
                                  .getResultList();
            alle.forEach(p -> System.out.println("   " + p));
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // 2. Mit WHERE-Klausel
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 2. WHERE p.alter > 25:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            List<Person> aelter = em.createQuery(
                    "SELECT p FROM Person p WHERE p.alter > :minAlter ORDER BY p.alter", 
                    Person.class)
                    .setParameter("minAlter", 25)
                    .getResultList();
            aelter.forEach(p -> System.out.println("   " + p));
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // 3. LIKE-Suche
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 3. WHERE name LIKE '%Müller%':");
            System.out.println("─────────────────────────────────────────────────────────");
            
            List<Person> muellers = em.createQuery(
                    "SELECT p FROM Person p WHERE p.name LIKE :name", 
                    Person.class)
                    .setParameter("name", "%Müller%")
                    .getResultList();
            muellers.forEach(p -> System.out.println("   " + p));
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // 4. Aggregat-Funktionen
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 4. COUNT, AVG, MAX:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            Long anzahl = em.createQuery("SELECT COUNT(p) FROM Person p", Long.class)
                            .getSingleResult();
            System.out.println("   Anzahl: " + anzahl);
            
            Double durchschnitt = em.createQuery("SELECT AVG(p.alter) FROM Person p", Double.class)
                                    .getSingleResult();
            System.out.println("   Durchschnittsalter: " + String.format("%.1f", durchschnitt));
            
            Integer maxAlter = em.createQuery("SELECT MAX(p.alter) FROM Person p", Integer.class)
                                 .getSingleResult();
            System.out.println("   Älteste Person: " + maxAlter + " Jahre");
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // 5. Einzelnes Ergebnis
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 5. getSingleResult() für ein Ergebnis:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            try {
                Person max = em.createQuery(
                        "SELECT p FROM Person p WHERE p.email = :email", 
                        Person.class)
                        .setParameter("email", "max@example.com")
                        .getSingleResult();
                System.out.println("   Gefunden: " + max);
            } catch (NoResultException e) {
                System.out.println("   Nicht gefunden!");
            }
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // 6. Pagination
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 6. Pagination (Seite 1, 2 Einträge):");
            System.out.println("─────────────────────────────────────────────────────────");
            
            List<Person> seite1 = em.createQuery("SELECT p FROM Person p ORDER BY p.name", Person.class)
                                    .setFirstResult(0)   // Offset
                                    .setMaxResults(2)    // Limit
                                    .getResultList();
            seite1.forEach(p -> System.out.println("   " + p));
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // 7. UPDATE/DELETE mit JPQL
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 7. Bulk UPDATE mit JPQL:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            em.getTransaction().begin();
            
            int updated = em.createQuery(
                    "UPDATE Person p SET p.alter = p.alter + 1 WHERE p.alter < :maxAlter")
                    .setParameter("maxAlter", 30)
                    .executeUpdate();
            
            em.getTransaction().commit();
            System.out.println("   " + updated + " Person(en) aktualisiert");
            
            // Cache leeren um aktualisierte Daten zu sehen
            em.clear();
            
            em.createQuery("SELECT p FROM Person p ORDER BY p.name", Person.class)
              .getResultList()
              .forEach(p -> System.out.println("   " + p));
            
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
        System.out.println("  💡 JPQL = SQL für Objekte!");
        System.out.println("     Entity-Namen statt Tabellennamen");
        System.out.println("     Field-Namen statt Spaltennamen");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
    
    private static void setupTestData(EntityManager em) {
        em.getTransaction().begin();
        
        em.persist(new Person("Max Mustermann", 28, "max@example.com"));
        em.persist(new Person("Lisa Schmidt", 34, "lisa@example.com"));
        em.persist(new Person("Tom Müller", 22, "tom@example.com"));
        em.persist(new Person("Anna Müller", 45, "anna@example.com"));
        em.persist(new Person("Peter Weber", 31, null));
        
        em.getTransaction().commit();
        em.clear();  // Cache leeren für saubere Demos
        
        System.out.println("📦 5 Testpersonen angelegt");
        System.out.println();
    }
}
