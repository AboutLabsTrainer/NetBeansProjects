package de.ibb.oop;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
class RegalTest {
    @Test void filtertAutorUndBehaeltReihenfolge() {
        Regal regal = new Regal();
        regal.hinzufuegen(new Buch("A", "Erika", 10));
        regal.hinzufuegen(new Buch("B", "Max", 12));
        regal.hinzufuegen(new Buch("C", "Erika", 14));
        List<Buch> treffer = regal.vonAutor("Erika");
        assertEquals(2, treffer.size());
        assertEquals("A", treffer.get(0).getTitel());
        assertEquals("C", treffer.get(1).getTitel());
    }
}
