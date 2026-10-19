package de.ibb.boot.ak.messaging.service;

import de.ibb.boot.ak.messaging.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Sendet die Bestellung mit dem JmsTemplate an die konfigurierte Queue.
 * Das Template nutzt die Connection-Factory aus der ActiveMQ-Auto-Configuration.
 */
@Service
@Slf4j
public class OrderProducer {

    private final JmsTemplate jmsTemplate;
    private final String queueName;

    public OrderProducer(
            JmsTemplate jmsTemplate,
            @Value("${app.queue.orders}") String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public void sendOrder(Order order) {
        log.info("Sending order to queue '{}': {}", queueName, order.getOrderId());

        try {
            jmsTemplate.convertAndSend(queueName, order);
            log.info("Order {} sent successfully!", order.getOrderId());
        } catch (Exception e) {
            log.error("Failed to send order {}: {}", order.getOrderId(), e.getMessage());
            throw new RuntimeException("Could not send order to queue", e);
        }
    }
}
