package de.ibb.web.aufbau.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RoleGateTest {
    @Test
    void adminOpensEveryArea() {
        assertTrue(RoleGate.allows("manager", "admin"));
        assertTrue(RoleGate.allows("user", "admin"));
        assertFalse(RoleGate.allows("admin", "manager"));
        assertTrue(RoleGate.allows("manager", "manager"));
    }
}
