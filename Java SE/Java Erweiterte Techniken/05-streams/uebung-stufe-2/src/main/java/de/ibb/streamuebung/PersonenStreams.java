package de.ibb.streamuebung;

import java.util.List;
import java.util.Map;

public class PersonenStreams {

    public static Map<String, Double> durchschnittAlter(List<Person> personen) {
        // TODO groupingBy Abteilung, averagingInt Alter
        return Map.of();
    }

    public static List<String> erwachseneNamen(List<Person> personen) {
        // TODO Alter ab 18, Namen eindeutig, sortiert
        return List.of();
    }

    public static void main(String[] args) {
        List<Person> personen = List.of(
                new Person("Max", 25, "IT"),
                new Person("Anna", 30, "HR"),
                new Person("Tom", 17, "IT"),
                new Person("Max", 25, "HR"),
                new Person("Lisa", 28, "HR"));
        System.out.println(durchschnittAlter(personen));
        System.out.println(erwachseneNamen(personen));
    }
}
