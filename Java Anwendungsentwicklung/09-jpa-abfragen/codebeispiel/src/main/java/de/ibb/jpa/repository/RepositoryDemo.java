package de.ibb.jpa.repository;

import de.ibb.jpa.entity.Person;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * RepositoryDemo - Das Repository Pattern mit JPA.
 * 
 * Tag 9 des Kurses "Java Anwendungsentwicklung"
 * 
 * Zeigt wie man ein sauberes Repository implementiert.
 * Spring Data JPA macht das später automatisch!
 */
public class RepositoryDemo {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  🏗️ Repository Pattern Demo");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("de.ibb");
        
        // Repository erstellen
        PersonRepository repository = new PersonRepository(emf);
        
        try {
            // ────────────────────────────────────────────────────────
            // CREATE
            // ────────────────────────────────────────────────────────
            
            System.out.println("📝 CREATE:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            Person max = repository.save(new Person("Max Mustermann", 28, "max@example.com"));
            System.out.println("   Gespeichert: " + max);
            
            Person lisa = repository.save(new Person("Lisa Schmidt", 34, "lisa@example.com"));
            System.out.println("   Gespeichert: " + lisa);
            
            Person tom = repository.save(new Person("Tom Müller", 22, null));
            System.out.println("   Gespeichert: " + tom);
            
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // READ
            // ────────────────────────────────────────────────────────
            
            System.out.println("📖 READ:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            System.out.println("   findAll():");
            repository.findAll().forEach(p -> System.out.println("      " + p));
            
            System.out.println("   findById(" + max.getId() + "):");
            repository.findById(max.getId())
                      .ifPresent(p -> System.out.println("      " + p));
            
            System.out.println("   findByNameContaining('Müller'):");
            repository.findByNameContaining("Müller")
                      .forEach(p -> System.out.println("      " + p));
            
            System.out.println("   count(): " + repository.count());
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // UPDATE
            // ────────────────────────────────────────────────────────
            
            System.out.println("✏️ UPDATE:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            max.setAlter(29);
            max.setEmail("max.neu@example.com");
            Person updated = repository.save(max);  // save() für Update!
            System.out.println("   Aktualisiert: " + updated);
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // DELETE
            // ────────────────────────────────────────────────────────
            
            System.out.println("🗑️ DELETE:");
            System.out.println("─────────────────────────────────────────────────────────");
            
            repository.deleteById(tom.getId());
            System.out.println("   Tom gelöscht");
            System.out.println("   count(): " + repository.count());
            System.out.println();
            
            // ────────────────────────────────────────────────────────
            // Finale Übersicht
            // ────────────────────────────────────────────────────────
            
            System.out.println("📋 FINALE ÜBERSICHT:");
            System.out.println("─────────────────────────────────────────────────────────");
            repository.findAll().forEach(p -> System.out.println("   " + p));
            
        } finally {
            emf.close();
        }
        
        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  💡 Das Repository Pattern:");
        System.out.println("     • Kapselt alle DB-Operationen");
        System.out.println("     • Service-Code sieht keine JPA-Details");
        System.out.println("     • Spring Data JPA generiert das automatisch!");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}

/**
 * PersonRepository - Ein einfaches JPA Repository.
 * 
 * In Spring Data JPA würde das so aussehen:
 * 
 *   public interface PersonRepository extends JpaRepository<Person, Long> {
 *       List<Person> findByNameContaining(String name);
 *   }
 * 
 * Das ist ALLES - Spring generiert die Implementation!
 */
class PersonRepository {
    
    private final EntityManagerFactory emf;
    
    public PersonRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public Person save(Person person) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            if (person.getId() == null) {
                em.persist(person);
            } else {
                person = em.merge(person);
            }
            
            tx.commit();
            return person;
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }
    
    public Optional<Person> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return Optional.ofNullable(em.find(Person.class, id));
        } finally {
            em.close();
        }
    }
    
    public List<Person> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Person p ORDER BY p.id", Person.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Person> findByNameContaining(String name) {
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
    
    public void deleteById(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Person person = em.find(Person.class, id);
            if (person != null) {
                em.remove(person);
            }
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }
    
    public long count() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT COUNT(p) FROM Person p", Long.class)
                     .getSingleResult();
        } finally {
            em.close();
        }
    }
}
