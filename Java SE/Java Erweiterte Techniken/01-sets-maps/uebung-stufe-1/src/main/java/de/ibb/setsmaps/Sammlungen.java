package de.ibb.setsmaps;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Sammlungen {

    public static Set<String> staedte(List<String> eingabe) {
        // TODO jede Stadt nur einmal
        return Set.of();
    }

    public static Map<String, Integer> wortHaeufigkeit(String text) {
        // TODO Kleinbuchstaben, Satzzeichen weg, merge
        return Map.of();
    }

    public static void main(String[] args) {
        System.out.println(staedte(List.of("Berlin", "Hamburg", "Berlin")));
        System.out.println(wortHaeufigkeit(
                "Java ist eine Programmiersprache. Java ist objektorientiert."));
    }
}
