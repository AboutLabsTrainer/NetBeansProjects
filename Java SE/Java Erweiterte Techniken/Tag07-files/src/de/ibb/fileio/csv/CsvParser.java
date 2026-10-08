package de.ibb.fileio.csv;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Macht aus Semikolon-Zeilen {@link Person}-Objekte, statt die CSV als reinen Text stehen zu lassen.
 */
public final class CsvParser {
    private CsvParser() {}

    /**
     * Erwartetes Format (wie im Artikelbeispiel): Header und dann Zeilen mit ';' getrennt: Name;Alter;Stadt
     */
    public static List<Person> readPersons(Path csvFile) throws IOException {
        try (var lines = Files.lines(csvFile, StandardCharsets.UTF_8)) {
            return lines
                .skip(1) // Header
                .map(String::trim)
                .filter(l -> !l.isBlank())
                .map(line -> line.split(";"))
                .map(arr -> new Person(
                    safeGet(arr, 0),
                    safeParseInt(safeGet(arr, 1), 0),
                    safeGet(arr, 2)
                ))
                .toList();
        }
    }

    private static String safeGet(String[] arr, int idx) {
        if (arr == null || idx < 0 || idx >= arr.length) {
            return "";
        }
        return arr[idx].trim();
    }

    private static int safeParseInt(String raw, int def) {
        try {
            return Integer.parseInt(raw.trim());
        } catch (Exception e) {
            return def;
        }
    }
}

