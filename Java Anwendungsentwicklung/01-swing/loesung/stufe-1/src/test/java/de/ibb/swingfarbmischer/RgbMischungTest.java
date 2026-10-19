package de.ibb.swingfarbmischer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RgbMischungTest {

    @Test
    void hexAusDenSlidern() {
        assertEquals("#FF000F", RgbMischung.hex(255, 0, 15));
        assertEquals("#00FF00", RgbMischung.hex(-5, 300, 0));
    }
}
