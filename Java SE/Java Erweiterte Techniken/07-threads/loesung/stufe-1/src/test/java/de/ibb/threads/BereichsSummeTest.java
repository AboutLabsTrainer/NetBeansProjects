package de.ibb.threads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BereichsSummeTest {

    @Test
    void summiertGeteilteBereiche() throws InterruptedException {
        assertEquals(5050, BereichsSumme.summe(1, 101, 4));
        assertEquals(0, BereichsSumme.summe(1, 1, 2));
    }
}
