package de.ibb.flow;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class RatspielTest {
    @Test void vergleicht() {
        assertEquals("zu klein", Ratspiel.hinweis(4, 2));
        assertEquals("zu gross", Ratspiel.hinweis(4, 6));
        assertEquals("getroffen", Ratspiel.hinweis(4, 4));
    }
}
