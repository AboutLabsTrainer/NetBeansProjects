package de.ibb.web.aufbau.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SessionPeakTest {
    @Test
    void keepsTheHigherCount() {
        assertEquals(5, SessionPeak.peak(3, 5));
        assertEquals(7, SessionPeak.peak(7, 4));
    }
}
