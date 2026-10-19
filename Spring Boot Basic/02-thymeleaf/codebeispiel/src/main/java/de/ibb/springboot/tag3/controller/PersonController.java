package de.ibb.springboot.tag3.controller;

import de.ibb.springboot.tag3.model.Person;
import de.ibb.springboot.tag3.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PersonController - REST API Controller aus Tag 2
 * Gibt JSON zurück für externe Programme/APIs
 *
 * Endpoints:
 * - GET /api/persons - Alle Personen
 * - GET /api/persons/{id} - Person nach ID
 * - POST /api/persons - Neue Person erstellen
 * - PUT /api/persons/{id} - Person aktualisieren
 * - DELETE /api/persons/{id} - Person löschen
 *
 * Ohne Lombok, damit das Projekt überall ohne Annotation Processing kompiliert.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * GET /api/persons - Alle Personen als JSON
     */
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    /**
     * GET /api/persons/{id} - Person nach ID als JSON
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }

    /**
     * POST /api/persons - Neue Person erstellen
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person created = personService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/persons/{id} - Person aktualisieren
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updated = personService.updatePerson(id, person);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/persons/{id} - Person löschen
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean deleted = personService.deletePerson(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
