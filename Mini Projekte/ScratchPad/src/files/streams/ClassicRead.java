/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package files.streams;

import java.io.*;

/**
 * Liest eine Textdatei zeilenweise mit dem klassischen IO-Stapel aus Strom, Reader und Puffer.
 */
public class ClassicRead {
    public static void main(String[] args) {
        File datei = new File("beispiel_alt.txt");

        // Verschachtelung: Datei -> Byte-Stream -> Zeichen-Übersetzer -> Puffer
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(datei), "UTF-8"))) {
            
            String zeile;
            // readLine() liest eine komplette Zeile bis zum Umbruch (\n oder \r\n)
            while ((zeile = reader.readLine()) != null) {
                System.out.println("Gelesen: " + zeile);
            }
            
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen: " + e.getMessage());
        }
    }
}

