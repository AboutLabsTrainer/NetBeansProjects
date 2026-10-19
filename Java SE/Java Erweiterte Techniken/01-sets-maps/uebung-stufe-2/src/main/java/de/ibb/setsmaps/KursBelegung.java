package de.ibb.setsmaps;

import java.util.Set;

public class KursBelegung {

    public boolean anmelden(String kurs, String student) {
        // TODO false, wenn der Name in diesem Kurs schon steht
        return false;
    }

    public Set<String> teilnehmer(String kurs) {
        // TODO Kopie, unbekannter Kurs ist leer
        return Set.of();
    }

    public Set<String> kurseVon(String student) {
        // TODO alle Kurse dieses Namens
        return Set.of();
    }

    public static void main(String[] args) {
        KursBelegung belegung = new KursBelegung();
        belegung.anmelden("Java", "Mia");
        belegung.anmelden("SQL", "Mia");
        System.out.println(belegung.kurseVon("Mia"));
    }
}
