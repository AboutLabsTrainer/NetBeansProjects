package com.example.helloworldapi.bootstrap;

import com.example.helloworldapi.model.Person;
import com.example.helloworldapi.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Legt im Profil dev Beispieldaten an, sobald der Container steht.
 * In anderen Profilen wird diese Bean nicht erzeugt.
 */
@Component
@Profile("dev")
@RequiredArgsConstructor
public class DataBootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataBootstrap.class);

    private final PersonService personService;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting DataBootstrap...");

        loadPersons();

        log.info("DataBootstrap completed! Loaded {} persons",
                personService.getAllPersons().size());
    }

    private void loadPersons() {
        log.debug("Loading test persons...");

        Person person1 = new Person();
        person1.setFirstname("Max");
        person1.setLastname("Mustermann");
        person1.setEmail("max@example.com");
        personService.createPerson(person1);

        Person person2 = new Person();
        person2.setFirstname("Anna");
        person2.setLastname("Schmidt");
        person2.setEmail("anna@example.com");
        personService.createPerson(person2);

        log.info("Loaded 2 test persons");
    }
}
