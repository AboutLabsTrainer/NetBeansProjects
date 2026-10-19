package de.ibb.listen;

import java.util.ArrayList;
import java.util.List;

/**
 * Zwei Listen: offen und erledigt. Die Methoden sind noch leer.
 */
public class AufgabenListe {

    private final List<String> offen = new ArrayList<>();
    private final List<String> erledigt = new ArrayList<>();

    public void hinzufuegen(String text) {
        // TODO Text an die offene Liste hängen
    }

    public boolean erledigen(int index) {
        // TODO gültigen Index von offen nach erledigt verschieben
        return false;
    }

    public boolean entfernen(int index) {
        // TODO nur aus der offenen Liste löschen
        return false;
    }

    public List<String> offene() {
        // TODO Kopie der offenen Aufgaben
        return List.of();
    }

    public List<String> erledigte() {
        // TODO Kopie der erledigten Aufgaben
        return List.of();
    }

    public static void main(String[] args) {
        AufgabenListe liste = new AufgabenListe();
        liste.hinzufuegen("Java-Artikel lesen");
        liste.hinzufuegen("Mini-Challenge nachbauen");
        System.out.println(liste.offene());
        liste.erledigen(0);
        System.out.println(liste.offene());
        System.out.println(liste.erledigte());
    }
}
