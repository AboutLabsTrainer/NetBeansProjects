package de.ibb.boot.ak.messaging.controller;

import de.ibb.boot.ak.messaging.model.Order;
import de.ibb.boot.ak.messaging.service.OrderConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Zeigt die Bestellungen, die der Listener bereits aus der Queue genommen hat.
 * Senden passiert im Producer. Dieser Prozess bestätigt nur, was angekommen ist.
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderConsumer orderConsumer;

    @GetMapping("/processed")
    public ResponseEntity<Map<String, Object>> getProcessedOrders() {
        List<Order> orders = orderConsumer.getProcessedOrders();
        return ResponseEntity.ok(Map.of(
                "count", orders.size(),
                "orders", orders
        ));
    }
}
