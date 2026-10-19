package de.ibb.streamuebung;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonenStreamsTest {

    @Test
    void durchschnittUndErwachsene() {
        List<Person> personen = List.of(
                new Person("Max", 25, "IT"),
                new Person("Anna", 30, "HR"),
                new Person("Tom", 17, "IT"),
                new Person("Max", 25, "HR"),
                new Person("Lisa", 28, "HR"));

        Map<String, Double> durchschnitt = PersonenStreams.durchschnittAlter(personen);
        assertEquals(21.0, durchschnitt.get("IT"), 0.0001);
        assertEquals(83.0 / 3.0, durchschnitt.get("HR"), 0.0001);
        assertEquals(List.of("Anna", "Lisa", "Max"), PersonenStreams.erwachseneNamen(personen));
    }
}
