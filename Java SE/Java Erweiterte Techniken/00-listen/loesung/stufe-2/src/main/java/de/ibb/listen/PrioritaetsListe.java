package de.ibb.listen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrioritaetsListe {

    private final List<Eintrag> offen = new ArrayList<>();

    public boolean hinzufuegen(String text, Prioritaet prioritaet) {
        for (Eintrag eintrag : offen) {
            if (eintrag.text().equals(text)) {
                return false;
            }
        }
        offen.add(new Eintrag(text, prioritaet));
        return true;
    }

    public List<String> sortiert() {
        return offen.stream()
                .sorted(Comparator.comparing(Eintrag::prioritaet))
                .map(Eintrag::text)
                .toList();
    }

    public int anzahl(Prioritaet prioritaet) {
        int treffer = 0;
        for (Eintrag eintrag : offen) {
            if (eintrag.prioritaet() == prioritaet) {
                treffer++;
            }
        }
        return treffer;
    }

    public static void main(String[] args) {
        PrioritaetsListe liste = new PrioritaetsListe();
        liste.hinzufuegen("Bug", Prioritaet.HIGH);
        liste.hinzufuegen("Doku", Prioritaet.LOW);
        liste.hinzufuegen("Test", Prioritaet.MEDIUM);
        System.out.println(liste.sortiert());
    }
}
