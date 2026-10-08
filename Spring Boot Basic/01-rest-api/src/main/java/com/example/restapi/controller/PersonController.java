package com.example.restapi.controller;

import com.example.restapi.model.Person;
import net.datafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * REST-Controller für die Personenliste.
 * Anfragen unter /api/persons werden als JSON beantwortet. Die Daten liegen nur im Speicher und kommen nicht aus einer Datenbank.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final List<Person> persons;

    public PersonController() {
        Faker faker = new Faker(Locale.GERMANY);
        persons = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Person(
                        (long) i,
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress()))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<Person> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "0") int size) {

        List<Person> result = persons;

        if (name != null && !name.isBlank()) {
            String search = name.toLowerCase();
            result = result.stream()
                    .filter(p -> (p.getFirstname() != null && p.getFirstname().toLowerCase().contains(search))
                            || (p.getLastname() != null && p.getLastname().toLowerCase().contains(search)))
                    .collect(Collectors.toList());
        }

        if (size > 0) {
            int fromIndex = page * size;
            if (fromIndex >= result.size()) {
                return List.of();
            }
            int toIndex = Math.min(fromIndex + size, result.size());
            result = result.subList(fromIndex, toIndex);
        }

        return result;
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id) {
        return persons.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
