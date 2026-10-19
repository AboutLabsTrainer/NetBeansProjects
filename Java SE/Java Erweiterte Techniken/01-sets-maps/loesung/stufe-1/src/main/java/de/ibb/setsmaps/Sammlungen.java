package de.ibb.setsmaps;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Sammlungen {

    public static Set<String> staedte(List<String> eingabe) {
        return new HashSet<>(eingabe);
    }

    public static Map<String, Integer> wortHaeufigkeit(String text) {
        Map<String, Integer> haeufigkeit = new HashMap<>();
        String[] woerter = text.toLowerCase(Locale.ROOT)
                .replaceAll("[.,!?;:]", "")
                .split("\\s+");
        for (String wort : woerter) {
            if (wort.isBlank()) {
                continue;
            }
            haeufigkeit.merge(wort, 1, Integer::sum);
        }
        return haeufigkeit;
    }

    public static void main(String[] args) {
        System.out.println(staedte(List.of("Berlin", "Hamburg", "Berlin")));
        System.out.println(wortHaeufigkeit(
                "Java ist eine Programmiersprache. Java ist objektorientiert."));
    }
}
