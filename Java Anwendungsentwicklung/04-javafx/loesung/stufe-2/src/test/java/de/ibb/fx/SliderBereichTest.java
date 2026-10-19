package de.ibb.fx;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SliderBereichTest {

    @Test
    void sliderVonNullBisHundert() {
        assertEquals(0, SliderBereich.begrenze(-3), 0.001);
        assertEquals(100, SliderBereich.begrenze(140), 0.001);
        assertEquals(50, SliderBereich.begrenze(50), 0.001);
    }
}
