package de.ibb.web.aufbau.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GuardTest {
    @Test
    void protectsAdminAndApi() {
        assertEquals(Guard.Decision.REDIRECT_LOGIN, Guard.decide("/api/metrics", false));
        assertEquals(Guard.Decision.REDIRECT_LOGIN, Guard.decide("/admin", false));
        assertEquals(Guard.Decision.CONTINUE, Guard.decide("/api/metrics", true));
        assertEquals(Guard.Decision.CONTINUE, Guard.decide("/home", false));
    }
}
