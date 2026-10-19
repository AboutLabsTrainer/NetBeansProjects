package de.ibb.listen;

import java.util.ArrayList;
import java.util.List;

public class AufgabenListe {

    private final List<String> offen = new ArrayList<>();
    private final List<String> erledigt = new ArrayList<>();

    public void hinzufuegen(String text) {
        offen.add(text);
    }

    public boolean erledigen(int index) {
        if (index < 0 || index >= offen.size()) {
            return false;
        }
        erledigt.add(offen.remove(index));
        return true;
    }

    public boolean entfernen(int index) {
        if (index < 0 || index >= offen.size()) {
            return false;
        }
        offen.remove(index);
        return true;
    }

    public List<String> offene() {
        return List.copyOf(offen);
    }

    public List<String> erledigte() {
        return List.copyOf(erledigt);
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
