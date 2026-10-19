package de.ibb.boot.ak.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class QueueHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("queue", "orders").build();
    }
}
