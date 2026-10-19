package de.ibb.orderservice.model;
import lombok.*;
/**
 * Zusammengeführte Sicht aus Bestellung und dem Benutzer, den Feign geladen hat.
 */
@Data
@AllArgsConstructor
public class OrderWithUser {
    private Order order;
    private User user;
}
