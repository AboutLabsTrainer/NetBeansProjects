package de.ibb.orderservice.controller;
import de.ibb.orderservice.model.*;
import de.ibb.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * REST-Schnittstelle der Bestellungen.
 * Das Gateway leitet /api/orders an diesen Service weiter.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    @GetMapping("/{id}/with-user")
    public OrderWithUser getOrderWithUser(@PathVariable Long id) {
        return orderService.getOrderWithUser(id);
    }
}
