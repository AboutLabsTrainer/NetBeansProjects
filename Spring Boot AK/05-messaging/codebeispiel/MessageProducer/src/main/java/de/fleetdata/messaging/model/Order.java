package de.fleetdata.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Nachrichtenkörper, den der Producer serialisiert auf die Queue legt.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;
    private String customerName;
    private String product;
    private int quantity;
    private LocalDateTime orderTime;
    private String status;

    public static Order create(String customerName, String product, int quantity) {
        return new Order(
                UUID.randomUUID().toString().substring(0, 8),
                customerName,
                product,
                quantity,
                LocalDateTime.now(),
                "PENDING"
        );
    }
}
