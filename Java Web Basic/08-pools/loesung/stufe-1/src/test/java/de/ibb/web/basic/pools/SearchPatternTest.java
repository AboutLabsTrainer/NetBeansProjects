package de.ibb.web.basic.pools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SearchPatternTest {
    @Test
    void wrapsTheTerm() {
        assertEquals("jdbc/ibb", SearchPattern.JNDI);
        assertEquals("%Stift%", SearchPattern.like("Stift"));
    }
}
