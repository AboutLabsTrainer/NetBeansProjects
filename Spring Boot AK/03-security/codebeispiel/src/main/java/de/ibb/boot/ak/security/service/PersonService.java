package de.ibb.boot.ak.security.service;

import de.ibb.boot.ak.security.entity.Person;
import de.ibb.boot.ak.security.entity.User;
import de.ibb.boot.ak.security.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Anwendungsdienst für Personen hinter der abgesicherten API.
 */
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @PreAuthorize("isAuthenticated()")
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @PostAuthorize("@securityExpressions.canView(returnObject)")
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person nicht gefunden: " + id));
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('WRITE_PRIVILEGES')")
    @Transactional
    public Person create(Person person, User owner) {
        person.setOwner(owner);
        return personRepository.save(person);
    }

    @PreAuthorize("@securityExpressions.isOwnerOrAdmin(#id)")
    @Transactional
    public Person update(Long id, Person updatedPerson) {
        Person existing = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Person nicht gefunden: " + id));
        existing.setFirstname(updatedPerson.getFirstname());
        existing.setLastname(updatedPerson.getLastname());
        existing.setEmail(updatedPerson.getEmail());
        return personRepository.save(existing);
    }

    @PreAuthorize("@securityExpressions.isOwnerOrAdmin(#id)")
    @Transactional
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteAll() {
        personRepository.deleteAll();
    }
}
