package de.ibb.web.basic.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GreetingTest {
    @Test
    void blankNameUsesDefault() {
        assertEquals("Hallo Welt", Greeting.text(null));
        assertEquals("Hallo Welt", Greeting.text("  "));
    }

    @Test
    void nameIsTrimmed() {
        assertEquals("Hallo, Anna!", Greeting.text(" Anna "));
    }
}
