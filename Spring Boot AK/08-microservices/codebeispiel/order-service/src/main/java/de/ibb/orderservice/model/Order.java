package de.ibb.orderservice.model;
import lombok.*;
import java.math.BigDecimal;
/**
 * Bestellung im Speicher des Order-Service.
 * Es gibt in diesem Demo keine Datenbank. Die Benutzerdaten kommen bei Bedarf über Feign.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private String product;
    private Integer quantity;
    private BigDecimal price;
    private String status;
}
