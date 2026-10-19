package de.ibb.web.basic.pools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SearchPatternTest {
    @Test
    void blankMatchesAll() {
        assertEquals("%", SearchPattern.like(null));
        assertEquals("%", SearchPattern.like("  "));
        assertEquals("%Stift%", SearchPattern.like(" Stift "));
    }
}
