package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Startet das Actuator-Beispiel und das Scheduling für den Metrik-Export.
 * Health, Metriken und der eigene Endpunkt hängen an diesem Prozess.
 */
@SpringBootApplication
@EnableScheduling
public class ActuatorMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorMonitoringApplication.class, args);
    }
}
