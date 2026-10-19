package de.ibb.integration.repository;

import de.ibb.integration.entity.Person;
import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * PersonRepository - Datenzugriffsschicht.
 * 
 * Diese Klasse kapselt alle Datenbankoperationen.
 * Der Service und die UI wissen NICHTS über JPA!
 */
public class PersonRepository {
    
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
            return em.createQuery("SELECT p FROM Person p ORDER BY p.name", Person.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Person> findByNameContaining(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT p FROM Person p WHERE LOWER(p.name) LIKE :name ORDER BY p.name",
                    Person.class)
                    .setParameter("name", "%" + name.toLowerCase() + "%")
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
