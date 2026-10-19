package de.ibb.web.basic.pools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SearchPatternTest {
    @Test
    void wrapsTheTerm() {
        assertEquals("jdbc/jwebb_tag09_pools", SearchPattern.JNDI);
        assertEquals("%Stift%", SearchPattern.like("Stift"));
    }
}
