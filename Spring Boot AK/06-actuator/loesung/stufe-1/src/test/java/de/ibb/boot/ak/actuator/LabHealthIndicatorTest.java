package de.ibb.boot.ak.actuator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LabHealthIndicatorTest {
    @Test
    void labIsUp() {
        Health health = new LabHealthIndicator().health();
        assertEquals(Status.UP, health.getStatus());
        assertEquals("actuator", health.getDetails().get("modul"));
    }

}
