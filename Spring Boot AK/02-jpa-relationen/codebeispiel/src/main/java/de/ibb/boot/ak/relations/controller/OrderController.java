package de.ibb.boot.ak.relations.controller;

import de.ibb.boot.ak.relations.entity.Order;
import de.ibb.boot.ak.relations.entity.OrderStatus;
import de.ibb.boot.ak.relations.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST-Zugriff auf Bestellungen. Der Controller übersetzt HTTP; OrderService hält die Transaktion.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/persons/{personId}/orders")
    public ResponseEntity<Order> createOrder(
            @PathVariable Long personId,
            @Valid @RequestBody Order order) {
        return orderService.createOrder(personId, order)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/orders/by-status")
    public List<Order> getByStatus(@RequestParam OrderStatus status) {
        return orderService.getByStatus(status);
    }

    @GetMapping("/orders/by-status-with-person")
    public List<Order> getByStatusWithPerson(@RequestParam OrderStatus status) {
        return orderService.getByStatusWithPerson(status);
    }

    @GetMapping("/orders/by-person/{personId}")
    public List<Order> getByPersonId(@PathVariable Long personId) {
        return orderService.getByPersonId(personId);
    }

    @GetMapping("/orders/price-greater-than")
    public List<Order> getByPriceGreaterThan(@RequestParam BigDecimal price) {
        return orderService.getByPriceGreaterThan(price);
    }

    @GetMapping("/orders/latest")
    public List<Order> getLatestOrders() {
        return orderService.getLatestOrders();
    }
}
