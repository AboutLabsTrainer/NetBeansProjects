package com.example.personmanagement.controller;

import com.example.personmanagement.domain.Person;
import com.example.personmanagement.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * REST-Schnittstelle der Personen als JSON.
 * Sie nutzt denselben Service wie die Thymeleaf-Seiten und die JAX-RS-Ressource.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonApiController {

    private final PersonService personService;

    public PersonApiController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        return personService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person created = personService.createPerson(person);
        return ResponseEntity
                .created(URI.create("/api/persons/" + created.getId()))
                .body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}

