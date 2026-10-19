package de.ibb.web.aufbau.tags;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TruncateTest {
    @Test
    void shortensWithEllipsis() {
        assertEquals("", Truncate.shorten(null, 5));
        assertEquals("abc", Truncate.shorten("abc", 10));
        assertEquals("Hal…", Truncate.shorten("Hallo", 4));
        assertEquals("…", Truncate.shorten("Hallo", 1));
    }
}
