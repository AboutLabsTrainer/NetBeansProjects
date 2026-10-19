package de.ibb.streamuebung;

import java.util.List;
import java.util.Optional;

public class StreamAufgaben {

    public static List<Integer> ueberFuenfVerdoppelt(List<Integer> zahlen) {
        // TODO größer als 5, dann verdoppeln
        return List.of();
    }

    public static Optional<String> laengsterName(List<String> namen) {
        // TODO max nach Länge, Gleichstand behält den ersten
        return Optional.empty();
    }

    public static long beginneMitA(List<String> namen) {
        // TODO beginnt mit 'A'
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(ueberFuenfVerdoppelt(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
        System.out.println(laengsterName(List.of("Max", "Anna", "Tom", "Alexandra", "Tim", "Christoph")));
        System.out.println(beginneMitA(List.of("Max", "Anna", "Tom", "Alexandra", "Tim", "Alfred")));
    }
}
