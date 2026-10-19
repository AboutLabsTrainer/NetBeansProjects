package de.ibb.boot.ak.messaging.controller;

import de.ibb.boot.ak.messaging.model.Order;
import de.ibb.boot.ak.messaging.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Nimmt eine Bestellung per HTTP an und übergibt sie dem JMS-Producer.
 * Die HTTP-Antwort kommt, sobald die Nachricht an die Queue übergeben wurde, nicht wenn der Consumer fertig ist.
 */
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderProducer orderProducer;

    @GetMapping("/send")
    public ResponseEntity<Map<String, Object>> sendOrder(
            @RequestParam String product,
            @RequestParam(defaultValue = "TestKunde") String customer,
            @RequestParam(defaultValue = "1") int quantity) {

        Order order = Order.create(customer, product, quantity);
        orderProducer.sendOrder(order);

        return ResponseEntity.ok(Map.of(
                "status", "QUEUED",
                "orderId", order.getOrderId(),
                "message", "Order sent to queue!"
        ));
    }
}
