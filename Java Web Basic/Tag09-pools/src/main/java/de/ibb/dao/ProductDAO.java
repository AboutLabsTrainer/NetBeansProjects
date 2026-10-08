package de.ibb.dao;

import de.ibb.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Schnittstelle der Produktzugriffe, die der Container als EJB bereitstellt.
 */

public interface ProductDAO {

    List<Product> findAll();

    Optional<Product> findById(int id);

    List<Product> searchByName(String query);

    void save(Product product);

    void update(Product product);

    void deleteById(int id);
}

