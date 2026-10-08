package de.ibb.dao;

import de.ibb.model.Product;
import java.util.List;
import java.util.Optional;

/**
 * Zugriff für Einzel-Insert, Batch und die Transaktionsübung.
 */

public interface ProductDAO {
    Optional<Product> findById(int id) throws Exception;
    void insertSingle(Product p) throws Exception;
    void insertBatch(List<Product> items) throws Exception;
    void transactionCommitAndRollback() throws Exception;
}
