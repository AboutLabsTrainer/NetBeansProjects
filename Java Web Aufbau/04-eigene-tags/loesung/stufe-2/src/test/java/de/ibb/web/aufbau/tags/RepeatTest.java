package de.ibb.web.aufbau.tags;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RepeatTest {
    @Test
    void repeatsOrReturnsEmpty() {
        assertEquals("ababab", Repeat.times(3, "ab"));
        assertEquals("", Repeat.times(0, "a"));
        assertEquals("", Repeat.times(2, null));
    }
}
