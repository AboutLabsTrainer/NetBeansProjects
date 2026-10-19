package com.fleetdata.personmanagement.service;

import com.fleetdata.personmanagement.entity.Order;
import com.fleetdata.personmanagement.entity.OrderStatus;
import com.fleetdata.personmanagement.repository.OrderRepository;
import com.fleetdata.personmanagement.repository.PersonRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transaktionsgrenze für Bestellungen.
 * Eine neue Order wird über die Person angehängt, damit beide Seiten der Beziehung zusammen gespeichert werden.
 */
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;

    public OrderService(OrderRepository orderRepository, PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
    }

    public Optional<Order> createOrder(Long personId, Order order) {
        return personRepository.findById(personId).map(person -> {
            person.addOrder(order);
            personRepository.saveAndFlush(person);
            return order;
        });
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getByStatusWithPerson(OrderStatus status) {
        return orderRepository.findByStatusWithPerson(status);
    }

    public List<Order> getByPersonId(Long personId) {
        return orderRepository.findByPersonId(personId);
    }

    public List<Order> getByPriceGreaterThan(BigDecimal price) {
        return orderRepository.findByPriceGreaterThan(price);
    }

    public List<Order> getLatestOrders() {
        return orderRepository.findTop10ByOrderByOrderDateDesc();
    }
}
