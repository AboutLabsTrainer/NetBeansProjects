package de.ibb.web.basic.servlet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DispatchModeTest {
    @Test
    void parsesBothModes() {
        assertEquals(DispatchMode.Mode.FORWARD, DispatchMode.from("FORWARD"));
        assertEquals(DispatchMode.Mode.INCLUDE, DispatchMode.from("include"));
    }

    @Test
    void rejectsUnknownMode() {
        assertThrows(IllegalArgumentException.class, () -> DispatchMode.from("redirect"));
    }
}
