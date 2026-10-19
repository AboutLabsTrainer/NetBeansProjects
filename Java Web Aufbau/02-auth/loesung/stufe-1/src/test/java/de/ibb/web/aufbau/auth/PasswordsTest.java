package de.ibb.web.aufbau.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PasswordsTest {
    @Test
    void hashesTest123() {
        assertEquals("ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae", Passwords.sha256Hex("test123"));
    }
}
