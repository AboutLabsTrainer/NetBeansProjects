package com.fleetdata.personmanagement.controller;

import com.fleetdata.personmanagement.entity.Address;
import com.fleetdata.personmanagement.entity.Person;
import com.fleetdata.personmanagement.projection.PersonSummary;
import com.fleetdata.personmanagement.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST-Zugriff auf Personen. Der Controller übersetzt HTTP; PersonService hält die Transaktion.
 */
@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public Page<Person> getAllPersons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "lastname") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return personService.getAllPersons(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<Person> getPersonWithAll(@PathVariable Long id) {
        return personService.getPersonWithAll(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/graph")
    public ResponseEntity<Person> getPersonWithEntityGraph(@PathVariable Long id) {
        return personService.getPersonWithEntityGraph(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/with-addresses")
    public List<Person> getAllWithAddresses() {
        return personService.getAllWithAddresses();
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
        Person saved = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(
            @PathVariable Long id,
            @Valid @RequestBody Person personDetails) {
        return personService.updatePerson(id, personDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (!personService.deletePerson(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity<Person> addAddress(
            @PathVariable Long id,
            @Valid @RequestBody Address address) {
        return personService.addAddress(id, address)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/by-lastname")
    public List<Person> searchByLastname(@RequestParam String lastname) {
        return personService.searchByLastname(lastname);
    }

    @GetMapping("/search/by-email")
    public ResponseEntity<Person> searchByEmail(@RequestParam String email) {
        return personService.searchByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/by-city")
    public List<Person> searchByCity(@RequestParam String city) {
        return personService.searchByCity(city);
    }

    @GetMapping("/search/by-firstname")
    public List<Person> searchByFirstname(@RequestParam String firstname) {
        return personService.searchByFirstname(firstname);
    }

    @GetMapping("/search/dynamic")
    public List<Person> searchDynamic(
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String city) {
        return personService.searchDynamic(lastname, city);
    }

    @GetMapping("/search/summary")
    public List<PersonSummary> searchSummary(@RequestParam String lastname) {
        return personService.searchSummary(lastname);
    }
}
