package de.ibb.service;

import de.ibb.domain.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * Zustandslose Session-Bean. Persistiert Product mit dem EntityManager, ohne SQL im Servlet.
 */
@Stateless
public class ProductService {

    @PersistenceContext(unitName = "Tag7PU")
    private EntityManager em;

    public Product createProduct(String name, BigDecimal price) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setStock(0);
        em.persist(p);
        return p;
    }

    public Product findProductById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAllProducts() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    public void updateStock(Long id, int newStock) {
        Product p = em.find(Product.class, id);
        if (p != null) {
            p.setStock(newStock);
        }
    }

    public void deleteProduct(Long id) {
        Product p = em.find(Product.class, id);
        if (p != null) {
            em.remove(p);
        }
    }
}
