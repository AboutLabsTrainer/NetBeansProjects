package com.example.personmanagement.jaxrs;

import com.example.personmanagement.domain.Person;
import com.example.personmanagement.service.PersonService;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * JAX-RS-Sicht auf denselben PersonService.
 * Die Ressource ist eine Spring-Bean, damit die Dependency Injection auch unter Jersey greift.
 */
@Component
@Path("/persons")
public class PersonResource {

    private static final Logger log = LoggerFactory.getLogger(PersonResource.class);

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    public void init() {
        log.info("PersonResource initialized (JAX-RS + Jakarta EE)");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAll() {
        return personService.getAllPersons();
    }
}

