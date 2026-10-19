package com.example.personmanagement.service;

import com.example.personmanagement.aop.LogExecutionTime;
import com.example.personmanagement.domain.Person;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Zentrale Personenliste für MVC, REST und JAX-RS.
 * Create ist mit LogExecutionTime markiert, der Aspect misst diesen Aufruf.
 */
@Service
public class PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final List<Person> persons = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @PostConstruct
    public void init() {
        log.info("PersonService initialized");
        // Demo-Daten
        createPerson(new Person(null, "Max", "Mustermann"));
        createPerson(new Person(null, "Erika", "Musterfrau"));
    }

    @PreDestroy
    public void cleanup() {
        log.info("PersonService destroyed");
    }

    public List<Person> getAllPersons() {
        return Collections.unmodifiableList(persons);
    }

    public Optional<Person> findById(Long id) {
        return persons.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @LogExecutionTime
    public Person createPerson(Person person) {
        long newId = idCounter.getAndIncrement();
        person.setId(newId);
        persons.add(person);
        log.debug("Created person with id={}", newId);
        return person;
    }

    public boolean deletePerson(Long id) {
        return persons.removeIf(p -> p.getId().equals(id));
    }
}

