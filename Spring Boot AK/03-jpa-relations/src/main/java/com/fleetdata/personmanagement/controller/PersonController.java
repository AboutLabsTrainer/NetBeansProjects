package com.fleetdata.personmanagement.controller;

import com.fleetdata.personmanagement.entity.Address;
import com.fleetdata.personmanagement.entity.Person;
import com.fleetdata.personmanagement.projection.PersonSummary;
import com.fleetdata.personmanagement.repository.PersonRepository;
import com.fleetdata.personmanagement.specification.PersonSpecifications;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * REST-Zugriff auf Personen inklusive Adressen, Graphen und dynamischer Suche.
 * Der Controller spricht das Repository direkt an. Fetch-Join und Entity-Graph steuern, welche Beziehungen mitgeladen werden.
 */
@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

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
        return personRepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<Person> getPersonWithAll(@PathVariable Long id) {
        return personRepository.findByIdWithAll(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/graph")
    public ResponseEntity<Person> getPersonWithEntityGraph(@PathVariable Long id) {
        return personRepository.findByIdWithEntityGraph(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/with-addresses")
    public List<Person> getAllWithAddresses() {
        return personRepository.findAllWithAddresses();
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
        Person toSave = new Person();
        toSave.setFirstname(person.getFirstname());
        toSave.setLastname(person.getLastname());
        toSave.setEmail(person.getEmail());

        if (person.getAddresses() != null) {
            Set<Address> incoming = new HashSet<>(person.getAddresses());
            for (Address address : incoming) {
                toSave.addAddress(address);
            }
        }

        Person saved = personRepository.save(toSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(
            @PathVariable Long id,
            @Valid @RequestBody Person personDetails) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setFirstname(personDetails.getFirstname());
                    person.setLastname(personDetails.getLastname());
                    person.setEmail(personDetails.getEmail());
                    return ResponseEntity.ok(personRepository.save(person));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        if (!personRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity<Person> addAddress(
            @PathVariable Long id,
            @Valid @RequestBody Address address) {
        return personRepository.findById(id)
                .map(person -> {
                    person.addAddress(address);
                    return ResponseEntity.ok(personRepository.save(person));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/by-lastname")
    public List<Person> searchByLastname(@RequestParam String lastname) {
        return personRepository.findByLastname(lastname);
    }

    @GetMapping("/search/by-email")
    public ResponseEntity<Person> searchByEmail(@RequestParam String email) {
        return personRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/by-city")
    public List<Person> searchByCity(@RequestParam String city) {
        return personRepository.findByAddresses_City(city);
    }

    @GetMapping("/search/by-firstname")
    public List<Person> searchByFirstname(@RequestParam String firstname) {
        return personRepository.findByFirstnameContainingIgnoreCase(firstname);
    }

    @GetMapping("/search/dynamic")
    public List<Person> searchDynamic(
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String city) {
        Specification<Person> spec = Specification
                .where(PersonSpecifications.hasLastname(lastname))
                .and(PersonSpecifications.hasAddressInCity(city));
        return personRepository.findAll(spec);
    }

    @GetMapping("/search/summary")
    public List<PersonSummary> searchSummary(@RequestParam String lastname) {
        return personRepository.findSummaryByLastname(lastname);
    }
}
