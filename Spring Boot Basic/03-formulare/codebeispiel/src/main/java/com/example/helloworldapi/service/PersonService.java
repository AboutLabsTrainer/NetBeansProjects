package com.example.helloworldapi.service;

import com.example.helloworldapi.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Hält die Personen im Speicher und kapselt die Anwendungsfälle hinter dem Controller.
 * Der Controller hängt nur von diesem Service ab, nicht von der Liste selbst.
 */
@Service
public class PersonService {

    private final List<Person> persons = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public PersonService() {
        persons.add(new Person(nextId.getAndIncrement(), "Max", "Mustermann", "max@example.com", "+49-151-12345678"));
        persons.add(new Person(nextId.getAndIncrement(), "Erika", "Musterfrau", "erika@example.com", "0151/98765432"));
        persons.add(new Person(nextId.getAndIncrement(), "Thomas", "Schmidt", "thomas@example.com", "+49 171 5551234"));
    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(persons);
    }

    public Person getPersonById(Long id) {
        return persons.stream()
                .filter(p -> p.getId() != null && p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Person createPerson(Person person) {
        person.setId(nextId.getAndIncrement());
        persons.add(person);
        return person;
    }

    public Person updatePerson(Long id, Person person) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId() != null && persons.get(i).getId().equals(id)) {
                person.setId(id);
                persons.set(i, person);
                return person;
            }
        }
        return null;
    }

    public void deletePerson(Long id) {
        persons.removeIf(p -> p.getId() != null && p.getId().equals(id));
    }
}
