package de.ibb.controlflow;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class PreisRechnerTest {
    @Test void staffeltNachAlter() {
        assertEquals(5, PreisRechner.preisFuerAlter(13), 0.0001);
        assertEquals(10, PreisRechner.preisFuerAlter(14), 0.0001);
        assertEquals(10, PreisRechner.preisFuerAlter(64), 0.0001);
        assertEquals(7, PreisRechner.preisFuerAlter(65), 0.0001);
    }
    @Test void rabattNurAmRabatttag() {
        assertEquals(8, PreisRechner.mitRabatt(10, true), 0.0001);
        assertEquals(10, PreisRechner.mitRabatt(10, false), 0.0001);
    }
}
