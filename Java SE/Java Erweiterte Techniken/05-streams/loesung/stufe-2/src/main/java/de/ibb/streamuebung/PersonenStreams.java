package de.ibb.streamuebung;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonenStreams {

    public static Map<String, Double> durchschnittAlter(List<Person> personen) {
        return personen.stream().collect(Collectors.groupingBy(
                Person::abteilung,
                Collectors.averagingInt(Person::alter)));
    }

    public static List<String> erwachseneNamen(List<Person> personen) {
        return personen.stream()
                .filter(person -> person.alter() >= 18)
                .map(Person::name)
                .distinct()
                .sorted()
                .toList();
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
