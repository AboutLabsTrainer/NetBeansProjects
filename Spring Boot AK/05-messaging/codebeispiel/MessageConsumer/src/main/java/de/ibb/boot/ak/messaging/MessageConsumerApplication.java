package de.ibb.boot.ak.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * Startet den Consumer und schaltet die JMS-Listener-Infrastruktur ein.
 * Ohne den Broker unter tcp://localhost:61616 kann der Listener die Queue nicht lesen.
 */
@SpringBootApplication
@EnableJms
public class MessageConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageConsumerApplication.class, args);
    }
}
