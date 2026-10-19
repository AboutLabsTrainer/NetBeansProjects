package de.ibb.boot.ak.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class LabHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // TODO: Health.up() mit Detail modul=actuator
        return Health.unknown().build();
    }
}
