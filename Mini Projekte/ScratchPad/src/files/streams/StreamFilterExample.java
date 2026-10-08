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
 * Filtert, formt um und gibt Zeilen einer Textdatei mit der Stream-API aus.
 */
public class StreamFilterExample {
    public static void main(String[] args) {
        Path path = Paths.get("beispiel_neu.txt");

        // Öffnet die Datei als Stream von Zeilen
        try (Stream<String> zeilen = Files.lines(path, StandardCharsets.UTF_8)) {
            
            zeilen
                .filter(s -> s.contains("NIO"))      // 1. Nur Zeilen behalten, die "NIO" enthalten
                .map(String::toUpperCase)            // 2. Diese Zeilen in GROSSBUCHSTABEN umwandeln
                .distinct()                          // 3. Duplikate entfernen (falls vorhanden)
                .forEach(System.out::println);       // 4. Das Ergebnis ausgeben
                
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

