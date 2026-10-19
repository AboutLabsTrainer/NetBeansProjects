package de.ibb.dateien;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DateiHelfer {

    public static long zeilen(Path datei) throws IOException {
        try (var strom = Files.lines(datei, StandardCharsets.UTF_8)) {
            return strom.count();
        }
    }

    public static void schreibe(Path datei, List<String> zeilen) throws IOException {
        if (datei.getParent() != null) {
            Files.createDirectories(datei.getParent());
        }
        Files.write(datei, zeilen, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static boolean lesbar(Path datei) {
        return Files.exists(datei) && Files.isReadable(datei);
    }

    public static void main(String[] args) throws IOException {
        Path datei = Path.of("ziel", "probe.txt");
        schreibe(datei, List.of("Zeile 1", "Zeile 2", "Zeile 3", "äöü"));
        System.out.println(zeilen(datei));
        System.out.println(lesbar(datei));
    }
}
