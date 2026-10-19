package de.ibb.oop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class BuchTest {
    @Test void rabatt() {
        Buch buch = new Buch("Java", "Erika", 20);
        buch.rabatt(10);
        assertEquals(18, buch.getPreis(), 0.0001);
        Buch insel = new Buch("Insel", "Chris", 49.90);
        insel.rabatt(15);
        assertEquals(42.415, insel.getPreis(), 0.0001);
    }
}
