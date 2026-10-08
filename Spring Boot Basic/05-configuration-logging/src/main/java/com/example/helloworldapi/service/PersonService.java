package com.example.helloworldapi.service;

import com.example.helloworldapi.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Anwendungsdienst für die Personen im Speicher.
 * Controller und der Dev-Bootstrap hängen von dieser Bean ab.
 */
@Service
public class PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final List<Person> persons = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public PersonService() {
        log.info("PersonService initialized");
    }

    public List<Person> getAllPersons() {
        log.debug("Getting all persons, current count: {}", persons.size());
        return persons;
    }

    public Person getPersonById(Long id) {
        log.debug("Looking for person with ID: {}", id);

        Person person = persons.stream()
                .filter(p -> p.getId() != null && p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (person == null) {
            log.warn("Person with ID {} not found!", id);
        } else {
            log.debug("Found person: {} {}", person.getFirstname(), person.getLastname());
        }

        return person;
    }

    public Person createPerson(Person person) {
        log.info("Creating new person: {} {}", person.getFirstname(), person.getLastname());

        person.setId(idCounter.getAndIncrement());
        persons.add(person);

        log.info("Person created successfully with ID: {}", person.getId());
        log.debug("Total persons now: {}", persons.size());

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
