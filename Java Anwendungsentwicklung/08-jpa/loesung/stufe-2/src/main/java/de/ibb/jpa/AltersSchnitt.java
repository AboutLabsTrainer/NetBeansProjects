package de.ibb.jpa;

import java.util.List;

public final class AltersSchnitt {

    private AltersSchnitt() {
    }

    public static double durchschnitt(List<Integer> alter) {
        if (alter == null || alter.isEmpty()) {
            return 0.0;
        }
        int summe = 0;
        for (Integer wert : alter) {
            summe += wert;
        }
        return (double) summe / alter.size();
    }
}
