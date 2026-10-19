package de.ibb.dateien;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonenDateiTest {

    @TempDir
    Path ordner;

    @Test
    void liestFehlerhaftesAlterUndRundet() throws Exception {
        Path csv = ordner.resolve("personen.csv");
        Files.writeString(csv, """
                Name;Alter;Stadt
                Mia;31;Köln
                Leo;x;Bonn

                """, StandardCharsets.UTF_8);

        assertEquals(
                List.of(new Person("Mia", 31, "Köln"), new Person("Leo", 0, "Bonn")),
                PersonenDatei.lesePersonen(csv));
    }

    @Test
    void schreibtUndLiestDieselbeListe() throws Exception {
        Path csv = ordner.resolve("rund.csv");
        List<Person> personen = List.of(
                new Person("Mäx", 25, "Berlin"),
                new Person("Ann", 4, "Hof"));
        PersonenDatei.schreibePersonen(csv, personen);
        assertEquals(personen, PersonenDatei.lesePersonen(csv));
    }
}
