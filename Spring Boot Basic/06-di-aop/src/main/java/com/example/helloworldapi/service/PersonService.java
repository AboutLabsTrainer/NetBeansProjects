package com.example.helloworldapi.service;

import com.example.helloworldapi.annotation.Logged;
import com.example.helloworldapi.annotation.Timed;
import com.example.helloworldapi.annotation.ValidateNotNull;
import com.example.helloworldapi.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service-Bean und damit das Ziel der Aspects in diesem Beispiel.
 * Primary legt fest, dass diese Bean injiziert wird, falls mehrere Kandidaten denselben Typ haben.
 */
@Service
@Primary
public class PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final List<Person> persons = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public PersonService() {
        log.info("PersonService Constructor called");
    }

    @PostConstruct
    public void init() {
        log.info("PersonService @PostConstruct - Initialization");
    }

    @PreDestroy
    public void cleanup() {
        log.info("PersonService @PreDestroy - Cleanup");
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

    @Timed
    @Logged
    @ValidateNotNull(parameterIndices = {0})
    public Person createPerson(Person person) {
        log.info("Creating new person: {} {}", person.getFirstname(), person.getLastname());

        person.setId(idCounter.getAndIncrement());
        persons.add(person);

        log.info("Person created successfully with ID: {}", person.getId());
        log.debug("Total persons now: {}", persons.size());

        return person;
    }

    @Timed
    @Logged
    public Person updatePerson(Long id, Person person) {
        log.info("Updating person with ID: {}", id);

        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getId() != null && persons.get(i).getId().equals(id)) {
                person.setId(id);
                persons.set(i, person);
                log.info("Person {} updated successfully", id);
                return person;
            }
        }

        log.error("Failed to update person {} - not found!", id);
        return null;
    }

    @Timed
    @Logged
    public void deletePerson(Long id) {
        log.info("Deleting person with ID: {}", id);

        boolean removed = persons.removeIf(p -> p.getId() != null && p.getId().equals(id));

        if (removed) {
            log.info("Person {} deleted successfully", id);
        } else {
            log.warn("Could not delete person {} - not found", id);
        }
    }
}
