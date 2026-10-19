package de.ibb.fileio.tasks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Einsteigeraufgaben: eine Textdatei zeilenweise lesen, Text schreiben und prüfen, ob die Datei nutzbar ist.
 */
public final class Level1Tasks {
    private Level1Tasks() {}

    public static void runAll(Path dataDir) throws IOException {
        System.out.println();
        System.out.println("---- Level 1 – Einsteiger ----");
        Path input = dataDir.resolve("text").resolve("input.txt");
        Path output = dataDir.resolve("text").resolve("output.txt");

        ensureSampleTextFile(input);

        countLines(input);
        writeListOfStrings(output);
        checkExistsAndReadable(input);
    }

    // [ ] Lies eine Textdatei und zähle die Zeilen
    private static void countLines(Path file) throws IOException {
        long lines;
        try (var stream = Files.lines(file, StandardCharsets.UTF_8)) {
            lines = stream.count();
        }
        System.out.println("[L1] Zeilen in " + file + ": " + lines);
    }

    // [ ] Schreibe eine Liste von Strings in eine Datei
    private static void writeListOfStrings(Path file) throws IOException {
        Files.createDirectories(file.getParent());

        List<String> lines = List.of(
            "Zeile 1",
            "Zeile 2",
            "Zeile 3"
        );

        Files.write(
            file,
            lines,
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        );
        System.out.println("[L1] Liste geschrieben nach: " + file);
    }

    // [ ] Prüfe ob eine Datei existiert und lesbar ist
    private static void checkExistsAndReadable(Path file) {
        boolean exists = Files.exists(file);
        boolean readable = Files.isReadable(file);
        System.out.println("[L1] exists=" + exists + ", readable=" + readable + " -> " + file);
    }

    private static void ensureSampleTextFile(Path file) throws IOException {
        if (Files.exists(file)) {
            return;
        }
        Files.createDirectories(file.getParent());
        List<String> sample = List.of(
            "Hallo Datei!",
            "Dies ist eine Beispiel-Datei.",
            "Sie hat mehrere Zeilen.",
            "Umlaute: äöü ÄÖÜ ß"
        );
        Files.write(file, sample, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
    }
}

