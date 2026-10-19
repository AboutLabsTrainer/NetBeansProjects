package de.ibb.streamuebung;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StreamAufgaben {

    public static List<Integer> ueberFuenfVerdoppelt(List<Integer> zahlen) {
        return zahlen.stream()
                .filter(n -> n > 5)
                .map(n -> n * 2)
                .toList();
    }

    public static Optional<String> laengsterName(List<String> namen) {
        return namen.stream().max(Comparator.comparing(String::length));
    }

    public static long beginneMitA(List<String> namen) {
        return namen.stream().filter(name -> name.startsWith("A")).count();
    }

    public static void main(String[] args) {
        System.out.println(ueberFuenfVerdoppelt(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
        System.out.println(laengsterName(List.of("Max", "Anna", "Tom", "Alexandra", "Tim", "Christoph")));
        System.out.println(beginneMitA(List.of("Max", "Anna", "Tom", "Alexandra", "Tim", "Alfred")));
    }
}
