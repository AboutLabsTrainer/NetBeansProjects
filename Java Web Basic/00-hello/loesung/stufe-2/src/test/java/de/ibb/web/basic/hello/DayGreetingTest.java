package de.ibb.web.basic.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DayGreetingTest {
    @Test
    void partsOfDay() {
        assertEquals("Guten Morgen, Anna!", DayGreeting.text("Anna", 9));
        assertEquals("Guten Tag, Anna!", DayGreeting.text("Anna", 15));
        assertEquals("Guten Abend, Anna!", DayGreeting.text("Anna", 20));
        assertEquals("Gute Nacht, Gast!", DayGreeting.text("  ", 23));
    }
}
