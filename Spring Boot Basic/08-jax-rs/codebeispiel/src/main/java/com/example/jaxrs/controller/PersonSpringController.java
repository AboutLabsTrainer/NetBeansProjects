package com.example.jaxrs.controller;

import com.example.jaxrs.model.Person;
import com.example.jaxrs.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Dieselbe Personenliste als Spring-MVC-REST-Controller, parallel zur JAX-RS-Ressource.
 * Der Vergleich zeigt zwei Programmiermodelle auf einem Container und einem Service.
 */
@RestController
@RequestMapping("/spring/persons")
public class PersonSpringController {

    private final PersonService personService;

    public PersonSpringController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }
}
