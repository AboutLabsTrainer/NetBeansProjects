package de.ibb.boot.ak.relations.repository;

import de.ibb.boot.ak.relations.entity.Order;
import de.ibb.boot.ak.relations.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Spring-Data-Repository für Bestellungen.
 * Abgeleitete Methodennamen filtern über Status, Person und Preis. Die JPQL-Abfrage lädt die Person per Join Fetch mit.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByPersonId(Long personId);

    List<Order> findByPersonEmail(String email);

    List<Order> findByPriceGreaterThan(BigDecimal price);

    List<Order> findByStatusOrderByOrderDateDesc(OrderStatus status);

    List<Order> findByPersonIdAndStatus(Long personId, OrderStatus status);

    List<Order> findTop10ByOrderByOrderDateDesc();

    @Query("SELECT o FROM Order o JOIN FETCH o.person WHERE o.status = :status")
    List<Order> findByStatusWithPerson(@Param("status") OrderStatus status);
}
