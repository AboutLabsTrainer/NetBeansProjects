package de.fleetdata.messaging.service;

import de.fleetdata.messaging.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * JMS-Listener auf der Bestell-Queue.
 * Trifft eine Nachricht ein, ruft der Listener-Container diese Methode auf und die Bestellung landet in der lokalen Liste.
 */
@Service
@Slf4j
public class OrderConsumer {

    private final List<Order> processedOrders =
            Collections.synchronizedList(new ArrayList<>());

    @JmsListener(destination = "${app.queue.orders}")
    public void receiveOrder(Order order) {
        log.info("======================================");
        log.info("ORDER RECEIVED!");
        log.info("Order ID:  {}", order.getOrderId());
        log.info("Customer:  {}", order.getCustomerName());
        log.info("Product:   {}", order.getProduct());
        log.info("Quantity:  {}", order.getQuantity());
        log.info("======================================");

        processOrder(order);
    }

    private void processOrder(Order order) {
        log.info("Processing order {}...", order.getOrderId());

        // Simuliere Verarbeitungszeit
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        order.setStatus("COMPLETED");
        processedOrders.add(order);

        log.info("Order {} completed! Total: {}",
                order.getOrderId(), processedOrders.size());
    }

    public List<Order> getProcessedOrders() {
        return new ArrayList<>(processedOrders);
    }
}
