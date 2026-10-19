package de.ibb.dateien;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DateiHelfer {

    public static long zeilen(Path datei) throws IOException {
        // TODO UTF-8, Files.lines
        return 0;
    }

    public static void schreibe(Path datei, List<String> zeilen) throws IOException {
        // TODO Elternordner anlegen, Inhalt ersetzen
    }

    public static boolean lesbar(Path datei) {
        // TODO existiert und ist lesbar
        return false;
    }

    public static void main(String[] args) throws IOException {
        Path datei = Path.of("ziel", "probe.txt");
        schreibe(datei, List.of("Zeile 1", "Zeile 2", "Zeile 3", "äöü"));
        System.out.println(zeilen(datei));
        System.out.println(lesbar(datei));
    }
}
