package de.ibb.web.aufbau.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RoleGateTest {
    @Test
    void matchesTheSameRole() {
        assertTrue(RoleGate.allows("user", "user"));
        assertFalse(RoleGate.allows("admin", "user"));
        assertFalse(RoleGate.allows("guest", "guest"));
    }
}
