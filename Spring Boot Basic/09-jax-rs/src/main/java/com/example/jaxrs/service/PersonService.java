package com.example.jaxrs.service;

import com.example.jaxrs.model.Person;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * Gemeinsamer Dienst hinter der JAX-RS-Ressource und dem Spring-MVC-Controller.
 * Beide Schnittstellen ändern dieselbe Liste im Speicher.
 */
@Service
public class PersonService {

    private final List<Person> persons = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public PersonService() {
        Faker faker = new Faker(Locale.GERMANY);
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Person p = new Person(
                    idCounter.incrementAndGet(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress()
            );
            persons.add(p);
        });
    }

    public List<Person> getAllPersons() {
        return List.copyOf(persons);
    }

    public Person getPersonById(Long id) {
        return persons.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Person createPerson(Person person) {
        person.setId(idCounter.incrementAndGet());
        persons.add(person);
        return person;
    }

    public Person updatePerson(Long id, Person updated) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId().equals(id)) {
                updated.setId(id);
                persons.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    public boolean deletePerson(Long id) {
        return persons.removeIf(p -> p.getId().equals(id));
    }
}
