package de.ibb.fx;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SliderTextTest {

    @Test
    void formatWieDasBinding() {
        assertEquals("Wert: 50", SliderText.format(50));
        assertEquals("Wert: 51", SliderText.format(50.6));
    }
}
