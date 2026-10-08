package com.fleetdata.personmanagement.controller;

import com.fleetdata.personmanagement.entity.Order;
import com.fleetdata.personmanagement.entity.OrderStatus;
import com.fleetdata.personmanagement.repository.OrderRepository;
import com.fleetdata.personmanagement.repository.PersonRepository;
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
 * REST-Zugriff auf Bestellungen und ihre Abfragen über das Repository.
 * Join-Fetch-Methoden laden die Person mit, damit die Beziehung in derselben Transaktion gelesen wird.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;

    @PostMapping("/persons/{personId}/orders")
    public ResponseEntity<Order> createOrder(
            @PathVariable Long personId,
            @Valid @RequestBody Order order) {
        return personRepository.findById(personId)
                .map(person -> {
                    person.addOrder(order);
                    personRepository.saveAndFlush(person);
                    return ResponseEntity.status(HttpStatus.CREATED).body(order);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/orders/by-status")
    public List<Order> getByStatus(@RequestParam OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    @GetMapping("/orders/by-status-with-person")
    public List<Order> getByStatusWithPerson(@RequestParam OrderStatus status) {
        return orderRepository.findByStatusWithPerson(status);
    }

    @GetMapping("/orders/by-person/{personId}")
    public List<Order> getByPersonId(@PathVariable Long personId) {
        return orderRepository.findByPersonId(personId);
    }

    @GetMapping("/orders/price-greater-than")
    public List<Order> getByPriceGreaterThan(@RequestParam BigDecimal price) {
        return orderRepository.findByPriceGreaterThan(price);
    }

    @GetMapping("/orders/latest")
    public List<Order> getLatestOrders() {
        return orderRepository.findTop10ByOrderByOrderDateDesc();
    }
}
