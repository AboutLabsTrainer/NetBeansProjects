package de.ibb.awtfarbspiel;

import java.util.ArrayList;
import java.util.List;

public class Punktliste {

    private final List<int[]> punkte = new ArrayList<>();

    public void hinzufuegen(int x, int y) {
        punkte.add(new int[] {x, y});
    }

    public int anzahl() {
        return punkte.size();
    }

    public void loeschen() {
        punkte.clear();
    }
}
