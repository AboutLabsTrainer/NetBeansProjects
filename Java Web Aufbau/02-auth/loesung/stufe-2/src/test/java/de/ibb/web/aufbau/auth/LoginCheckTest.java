package de.ibb.web.aufbau.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LoginCheckTest {
    private static final String HASH = "ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae";

    @Test
    void checksEmailAndHash() {
        assertTrue(LoginCheck.accept("test@example.com", "test123", HASH));
        assertFalse(LoginCheck.accept("test.example.com", "test123", HASH));
        assertFalse(LoginCheck.accept("@example.com", "test123", HASH));
        assertFalse(LoginCheck.accept("test@example.com", "falsch", HASH));
    }
}
