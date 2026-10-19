package de.ibb.boot.ak.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class QueueHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // TODO: Health.up() mit Detail queue=orders
        return Health.unknown().build();
    }
}
