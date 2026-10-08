/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package files.streams;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Liest eine Textdatei zeilenweise über {@code Files.lines} und einen Stream.
 */
public class ModernNIORead {
    public static void main(String[] args) {
        Path path = Paths.get("beispiel_neu.txt");

        // Files.lines öffnet die Datei und gibt einen Stream von Zeilen zurück
        // Wichtig: Auch dieser Stream sollte in ein try-with-resources!
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            
            // Verarbeite jede Zeile einzeln
            stream.forEach(zeile -> System.out.println("NIO Stream: " + zeile));
            
        } catch (IOException e) {
            System.err.println("NIO-Lese-Fehler: " + e.getMessage());
        }
    }
}

