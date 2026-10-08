package de.ibb.service;

import de.ibb.domain.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * Anwendungsweiter CDI-Dienst. Kapselt den EntityManager, damit die JSF-Bean nicht selbst persistiert.
 */
@ApplicationScoped
@Named
public class ProductService {

    @PersistenceContext(unitName = "Tag10JSFPrimeFacesPU")
    private EntityManager em;

    public Product find(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product p order by p.id", Product.class)
                 .getResultList();
    }

    public List<Product> searchByName(String q) {
        if (q == null || q.isBlank()) return findAll();
        return em.createQuery("select p from Product p where lower(p.name) like lower(:q) order by p.id", Product.class)
                 .setParameter("q", "%" + q + "%")
                 .getResultList();
    }

    @Transactional
    public Product save(Product p) {
        if (p.getId() == null) {
            em.persist(p);
            return p;
        } else {
            return em.merge(p);
        }
    }

    @Transactional
    public void delete(Long id) {
        Product ref = em.find(Product.class, id);
        if (ref != null) {
            em.remove(ref);
        }
    }
}
