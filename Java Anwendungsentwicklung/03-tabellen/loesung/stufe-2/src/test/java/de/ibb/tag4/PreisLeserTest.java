package de.ibb.tag4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PreisLeserTest {

    @Test
    void kommaUndUngueltigerText() {
        assertEquals(12.5, PreisLeser.lies("12,5", 0), 0.001);
        assertEquals(2.25, PreisLeser.lies("2.25", 0), 0.001);
        assertEquals(3.0, PreisLeser.lies("abc", 3.0), 0.001);
    }
}
