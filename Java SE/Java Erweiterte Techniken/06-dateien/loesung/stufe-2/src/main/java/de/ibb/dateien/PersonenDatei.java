package de.ibb.dateien;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class PersonenDatei {

    public static void schreibePersonen(Path csv, List<Person> personen) throws IOException {
        if (csv.getParent() != null) {
            Files.createDirectories(csv.getParent());
        }
        List<String> zeilen = new ArrayList<>();
        zeilen.add("Name;Alter;Stadt");
        for (Person person : personen) {
            zeilen.add(person.name() + ";" + person.alter() + ";" + person.stadt());
        }
        Files.write(csv, zeilen, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<Person> lesePersonen(Path csv) throws IOException {
        try (var zeilen = Files.lines(csv, StandardCharsets.UTF_8)) {
            return zeilen
                    .skip(1)
                    .map(String::trim)
                    .filter(zeile -> !zeile.isBlank())
                    .map(zeile -> zeile.split(";", -1))
                    .map(teile -> new Person(
                            feld(teile, 0),
                            alter(feld(teile, 1)),
                            feld(teile, 2)))
                    .toList();
        }
    }

    private static String feld(String[] teile, int index) {
        if (index >= teile.length) {
            return "";
        }
        return teile[index].trim();
    }

    private static int alter(String roh) {
        try {
            return Integer.parseInt(roh.trim());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        Path csv = Path.of("ziel", "personen.csv");
        schreibePersonen(csv, List.of(new Person("Mia", 31, "Köln"), new Person("Leo", 0, "Bonn")));
        System.out.println(lesePersonen(csv));
    }
}
