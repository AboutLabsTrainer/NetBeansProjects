/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package files.streams;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.List;

/**
 * Schreibt Textzeilen mit {@code Files.write}. Der Strom wird dabei intern geschlossen.
 */
public class ModernNIOWrite {
    public static void main(String[] args) {
        // Path ist das moderne Gegenstück zur alten File-Klasse
        Path path = Paths.get("beispiel_neu.txt");
        
        // Eine Liste von Strings, die als Zeilen geschrieben werden sollen
        List<String> zeilen = List.of("Hallo Kurs!", "Das ist der moderne NIO-Weg.");

        try {
            /*
             * Files.write erledigt alles für dich:
             * - Öffnet den Stream
             * - Erstellt die Datei, falls sie nicht existiert
             * - Nutzt intern einen Puffer (Performance)
             * - Schließt den Stream am Ende automatisch
             */
            Files.write(path, zeilen, StandardCharsets.UTF_8);
            
            System.out.println("Datei erfolgreich mit NIO geschrieben!");
            
        } catch (IOException e) {
            System.err.println("NIO-Fehler: " + e.getMessage());
        }
    }
}
