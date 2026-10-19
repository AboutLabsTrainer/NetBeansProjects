package de.ibb.dateien;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class PersonenDatei {

    public static void schreibePersonen(Path csv, List<Person> personen) throws IOException {
        // TODO Kopfzeile Name;Alter;Stadt, dann die Personen
    }

    public static List<Person> lesePersonen(Path csv) throws IOException {
        // TODO Kopf und Leerzeilen weg, ungültiges Alter wird 0
        return List.of();
    }

    public static void main(String[] args) throws IOException {
        Path csv = Path.of("ziel", "personen.csv");
        schreibePersonen(csv, List.of(new Person("Mia", 31, "Köln"), new Person("Leo", 0, "Bonn")));
        System.out.println(lesePersonen(csv));
    }
}
