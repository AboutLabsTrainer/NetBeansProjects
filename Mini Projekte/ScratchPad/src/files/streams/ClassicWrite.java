/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package files.streams;

import java.io.*;

/**
 * Schreibt eine Textdatei mit dem klassischen IO-Stapel. try-with-resources schließt den Writer.
 */
public class ClassicWrite {

    public static void main(String[] args) {
        // Das Ziel auf der Festplatte
        File datei = new File("beispiel_alt.txt");

        // Try-with-resources: Garantiert, dass der Stream am Ende sicher geschlossen wird
        try (BufferedWriter writer = new BufferedWriter( // 3. Schritt: Pufferung für Performance
                new OutputStreamWriter( // 2. Schritt: Text-zu-Byte-Übersetzer
                        new FileOutputStream(datei), "UTF-8"))) {   // 1. Schritt: Rohdaten-Strom zur Datei

            writer.write("Hallo Kurs!");
            writer.newLine(); // Erzeugt den passenden Zeilenumbruch für Windows, Mac oder Linux
            writer.write("Das ist der klassische Weg.");

            // Ein explizites .flush() ist hier nicht nötig, da .close() das automatisch macht
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben: " + e.getMessage());
        }
    }
}
