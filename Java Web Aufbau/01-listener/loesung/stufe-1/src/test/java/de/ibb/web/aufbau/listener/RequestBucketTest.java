package de.ibb.web.aufbau.listener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RequestBucketTest {
    @Test
    void sortsDurations() {
        assertEquals("FAST", RequestBucket.of(99));
        assertEquals("OK", RequestBucket.of(100));
        assertEquals("OK", RequestBucket.of(499));
        assertEquals("SLOW", RequestBucket.of(500));
    }
}
