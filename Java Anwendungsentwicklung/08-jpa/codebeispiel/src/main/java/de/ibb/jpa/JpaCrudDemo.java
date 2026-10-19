package de.ibb.jpa;

import de.ibb.jpa.entity.Person;
import jakarta.persistence.*;
import java.util.List;

/**
 * JpaCrudDemo - Create, Read, Update, Delete mit JPA.
 * 
 * Tag 8 des Kurses "Java Anwendungsentwicklung"
 * 
 * Vergleiche das mit dem JDBC-Code von Tag 6!
 * Hier: Keine SQL-Strings, keine ResultSet-Verarbeitung.
 */
public class JpaCrudDemo {
    
    private static EntityManagerFactory emf;
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  📊 JPA CRUD Demo - Vergleiche mit JDBC Tag 6!");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        emf = Persistence.createEntityManagerFactory("de.ibb");
        
        try {
            // ────────────────────────────────────────────────────────
            // CREATE
            // ────────────────────────────────────────────────────────
            
            System.out.println("📝 CREATE - Personen anlegen:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            Person max = create(new Person("Max Mustermann", 28, "max@example.com"));
            System.out.println("   Erstellt: " + max);
            
            Person lisa = create(new Person("Lisa Schmidt", 34, "lisa@example.com"));
            System.out.println("   Erstellt: " + lisa);
            
            Person tom = create(new Person("Tom Müller", 22, null));
            System.out.println("   Erstellt: " + tom);
            
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // READ - Alle
            // ────────────────────────────────────────────────────────
            
            System.out.println("📖 READ ALL - Alle Personen (JPQL):");
            System.out.println("─────────────────────────────────────────────────────────");
            
            List<Person> alle = findAll();
            alle.forEach(p -> System.out.println("   " + p));
            System.out.println("   Anzahl: " + count());
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // READ - Nach ID
            // ────────────────────────────────────────────────────────
            
            System.out.println("🔍 READ BY ID - Person mit ID " + max.getId() + ":");
            System.out.println("─────────────────────────────────────────────────────────");
            
            Person gefunden = findById(max.getId());
            System.out.println("   Gefunden: " + gefunden);
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // READ - Nach Name (JPQL)
            // ────────────────────────────────────────────────────────
            
            System.out.println("🔎 SEARCH - Suche nach 'Müller':");
            System.out.println("─────────────────────────────────────────────────────────");
            
            List<Person> muellers = findByNameContaining("Müller");
            muellers.forEach(p -> System.out.println("   " + p));
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // UPDATE
            // ────────────────────────────────────────────────────────
            
            System.out.println("✏️ UPDATE - Max wird älter:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            max.setAlter(29);
            max.setEmail("max.neu@example.com");
            Person updated = update(max);
            System.out.println("   Aktualisiert: " + updated);
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // DELETE
            // ────────────────────────────────────────────────────────
            
            System.out.println("🗑️ DELETE - Tom wird gelöscht:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            boolean deleted = deleteById(tom.getId());
            System.out.println("   Gelöscht: " + deleted);
            System.out.println("   Anzahl jetzt: " + count());
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // Finale Übersicht
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 FINALE ÜBERSICHT:");
            System.out.println("─────────────────────────────────────────────────────────");
            findAll().forEach(p -> System.out.println("   " + p));
            
        } finally {
            emf.close();
        }
        
        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  💡 Vergleiche das mit dem JDBC-Code von Tag 6!");
        System.out.println("     Kein SQL, keine ResultSet-Verarbeitung!");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
    
    // ══════════════════════════════════════════════════════════════
    // CRUD Methoden - So einfach mit JPA!
    // ══════════════════════════════════════════════════════════════
    
    private static Person create(Person person) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(person);
            tx.commit();
            return person;
        } catch (RuntimeException ex) {
            rollbackIfActive(tx);
            throw ex;
        } finally {
            em.close();
        }
    }
    
    private static Person findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Person.class, id);
        } finally {
            em.close();
        }
    }
    
    private static List<Person> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL - Java Persistence Query Language
            return em.createQuery("SELECT p FROM Person p ORDER BY p.id", Person.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
    
    private static List<Person> findByNameContaining(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Person p WHERE p.name LIKE :name ORDER BY p.name", 
                    Person.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    private static Person update(Person person) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Person merged = em.merge(person);
            tx.commit();
            return merged;
        } catch (RuntimeException ex) {
            rollbackIfActive(tx);
            throw ex;
        } finally {
            em.close();
        }
    }
    
    private static boolean deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Person person = em.find(Person.class, id);
            if (person != null) {
                em.remove(person);
                tx.commit();
                return true;
            }
            tx.rollback();
            return false;
        } catch (RuntimeException ex) {
            rollbackIfActive(tx);
            throw ex;
        } finally {
            em.close();
        }
    }

    private static void rollbackIfActive(EntityTransaction tx) {
        if (tx.isActive()) {
            tx.rollback();
        }
    }
    
    private static long count() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(p) FROM Person p", Long.class)
                     .getSingleResult();
        } finally {
            em.close();
        }
    }
}
