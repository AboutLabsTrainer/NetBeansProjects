package de.ibb.web.basic.servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VisitCounterTest {
    @Test
    void countsUpFromOne() {
        VisitCounter counter = new VisitCounter();
        assertEquals(1, counter.next());
        assertEquals(2, counter.next());
    }
}
