package de.ibb.boot.ak.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet den Producer, der Bestellungen an ActiveMQ schickt.
 * Der Broker muss vorher laufen. Dieser Prozess konsumiert die Queue nicht.
 */
@SpringBootApplication
public class MessageProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageProducerApplication.class, args);
    }
}
