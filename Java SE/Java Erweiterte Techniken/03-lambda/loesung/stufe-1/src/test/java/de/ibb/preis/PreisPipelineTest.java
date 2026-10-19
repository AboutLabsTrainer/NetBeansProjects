package de.ibb.preis;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreisPipelineTest {

    private List<Artikel> beispiel() {
        return List.of(
                new Artikel("Java-Buch", 29.90, "Buch"),
                new Artikel("Monitor 27\"", 199.00, "Elektronik"),
                new Artikel("Kaffeemaschine", 79.90, "Haushalt"),
                new Artikel("Maus", 19.99, "Elektronik"),
                new Artikel("Roman", 12.50, "Buch"));
    }

    @Test
    void preisFilterUndNummern() {
        assertEquals(29.90 * 0.90 * 1.19, PreisPipeline.rabattDannMwst(29.90), 1e-9);
        assertEquals(29.90 * 0.90 * 1.19 + 4.95, PreisPipeline.mitVersand(29.90), 1e-9);

        Predicate<Artikel> teuer = artikel -> artikel.preis() > 50.0;
        Predicate<Artikel> buch = artikel -> "Buch".equalsIgnoreCase(artikel.kategorie());
        assertEquals(List.of("Monitor 27\"", "Kaffeemaschine"),
                PreisPipeline.filtern(beispiel(), teuer).stream().map(Artikel::name).toList());
        assertEquals(List.of(), PreisPipeline.filtern(beispiel(), teuer.and(buch)));
        assertEquals(List.of(1000, 1001, 1002, 1003, 1004), PreisPipeline.bestellnummern(1000, 5));
    }
}
