package de.ibb.boot.ak.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet den Prozess, in dem der eingebettete ActiveMQ-Broker als Bean lebt.
 * Producer und Consumer verbinden sich mit dem TCP-Connector dieses Brokers.
 */
@SpringBootApplication
public class MessageBrokerApplication {

    public static void main(String[] args) {
        System.out.println("""
            
            ╔══════════════════════════════════════════════════════════════╗
            ║   Message Broker                                             ║
            ║   ActiveMQ läuft auf: tcp://localhost:61616                  ║
            ║   Web-Console:        http://localhost:8080                  ║
            ╚══════════════════════════════════════════════════════════════╝
            
            """);

        SpringApplication.run(MessageBrokerApplication.class, args);
    }
}
