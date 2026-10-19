package de.ibb.web.basic.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class LastViewedTest {
    @Test
    void keepsCurrentWhenRequestedIsNotPositive() {
        assertNull(LastViewed.remember(null, 0));
        assertEquals(4, LastViewed.remember(4, -1));
    }

    @Test
    void storesPositiveId() {
        assertEquals(9, LastViewed.remember(4, 9));
    }
}
