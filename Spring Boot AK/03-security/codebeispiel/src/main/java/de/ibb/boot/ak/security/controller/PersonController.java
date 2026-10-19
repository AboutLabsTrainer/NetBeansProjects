package de.ibb.boot.ak.security.controller;

import de.ibb.boot.ak.security.entity.Person;
import de.ibb.boot.ak.security.entity.User;
import de.ibb.boot.ak.security.security.CurrentUser;
import de.ibb.boot.ak.security.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Schnittstelle der Personen hinter der Security-Filterkette.
 * Löschen verlangt die Authority DELETE_PRIVILEGES, die übrigen API-Pfade eine angemeldete Identität.
 */
@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> getAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping
    public Person create(@RequestBody Person person, @CurrentUser User currentUser) {
        return personService.create(person, currentUser);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable Long id, @RequestBody Person person) {
        return personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
