package de.ibb.listen;

import java.util.List;

/**
 * Offene Aufgaben mit Priorität. Dieselbe Beschreibung nur einmal.
 */
public class PrioritaetsListe {

    public boolean hinzufuegen(String text, Prioritaet prioritaet) {
        // TODO false, wenn text schon offen ist
        return false;
    }

    public List<String> sortiert() {
        // TODO HIGH, dann MEDIUM, dann LOW
        return List.of();
    }

    public int anzahl(Prioritaet prioritaet) {
        // TODO offene Einträge dieser Priorität
        return 0;
    }

    public static void main(String[] args) {
        PrioritaetsListe liste = new PrioritaetsListe();
        liste.hinzufuegen("Bug", Prioritaet.HIGH);
        liste.hinzufuegen("Doku", Prioritaet.LOW);
        System.out.println(liste.sortiert());
        System.out.println(liste.anzahl(Prioritaet.HIGH));
    }
}
