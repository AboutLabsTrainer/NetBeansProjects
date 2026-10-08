/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.island.ready;

import java.util.ArrayList;
import java.util.List;

/**
 * Erste Schritte mit {@code ArrayList}: einfügen, die Länge abfragen, ein Element lesen und suchen.
 */
public class MeineErsteListe {
    public static void main(String[] args) {
        // Liste erstellen
        List<String> einkaufsliste = new ArrayList<>();
        
        // Elemente hinzufügen
        einkaufsliste.add("Milch");
        einkaufsliste.add("Brot");
        einkaufsliste.add("Käse");
        einkaufsliste.add("Äpfel");
        
        // Liste ausgeben
        System.out.println("Einkaufsliste: " + einkaufsliste);
        // Ausgabe: Einkaufsliste: [Milch, Brot, Käse, Äpfel]
        
        // Größe abfragen
        System.out.println("Anzahl: " + einkaufsliste.size());
        // Ausgabe: Anzahl: 4
        
        // Element an Position 1 abrufen (0-basiert!)
        System.out.println("Zweites Element: " + einkaufsliste.get(1));
        // Ausgabe: Zweites Element: Brot
        
        // Prüfen ob Element vorhanden
        System.out.println("Haben wir Milch? " + einkaufsliste.contains("Milch"));
        // Ausgabe: Haben wir Milch? true
        
        // Element entfernen
        einkaufsliste.remove("Brot");
        System.out.println("Nach Entfernen: " + einkaufsliste);
        // Ausgabe: Nach Entfernen: [Milch, Käse, Äpfel]
        
        // Element an bestimmter Position einfügen
        einkaufsliste.add(1, "Butter");
        System.out.println("Mit Butter: " + einkaufsliste);
        // Ausgabe: Mit Butter: [Milch, Butter, Käse, Äpfel]
    }
}
