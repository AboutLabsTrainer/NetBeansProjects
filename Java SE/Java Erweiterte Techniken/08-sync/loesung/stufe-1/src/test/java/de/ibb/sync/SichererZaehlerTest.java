package de.ibb.sync;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SichererZaehlerTest {

    @Test
    void achtThreadsErreichenAchtTausend() throws InterruptedException {
        assertEquals(8000, SichererZaehler.parallel(8, 1000));
    }
}
