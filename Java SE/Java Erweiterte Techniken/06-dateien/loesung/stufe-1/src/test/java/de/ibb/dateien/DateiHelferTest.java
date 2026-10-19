package de.ibb.dateien;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateiHelferTest {

    @TempDir
    Path ordner;

    @Test
    void schreibtZaehltUndPrueft() throws Exception {
        Path datei = ordner.resolve("text").resolve("probe.txt");
        List<String> zeilen = List.of("Zeile 1", "Zeile 2", "Zeile 3", "äöü");
        DateiHelfer.schreibe(datei, zeilen);

        assertEquals(4, DateiHelfer.zeilen(datei));
        assertEquals(zeilen, Files.readAllLines(datei, StandardCharsets.UTF_8));
        assertTrue(DateiHelfer.lesbar(datei));
        assertFalse(DateiHelfer.lesbar(ordner.resolve("fehlt.txt")));
    }
}
