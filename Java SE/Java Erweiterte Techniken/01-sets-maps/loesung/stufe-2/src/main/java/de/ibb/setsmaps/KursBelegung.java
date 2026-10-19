package de.ibb.setsmaps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KursBelegung {

    private final Map<String, Set<String>> kurse = new HashMap<>();

    public boolean anmelden(String kurs, String student) {
        return kurse.computeIfAbsent(kurs, name -> new HashSet<>()).add(student);
    }

    public Set<String> teilnehmer(String kurs) {
        return Set.copyOf(kurse.getOrDefault(kurs, Set.of()));
    }

    public Set<String> kurseVon(String student) {
        Set<String> treffer = new HashSet<>();
        for (Map.Entry<String, Set<String>> eintrag : kurse.entrySet()) {
            if (eintrag.getValue().contains(student)) {
                treffer.add(eintrag.getKey());
            }
        }
        return treffer;
    }

    public static void main(String[] args) {
        KursBelegung belegung = new KursBelegung();
        belegung.anmelden("Java", "Mia");
        belegung.anmelden("SQL", "Mia");
        System.out.println(belegung.kurseVon("Mia"));
    }
}
