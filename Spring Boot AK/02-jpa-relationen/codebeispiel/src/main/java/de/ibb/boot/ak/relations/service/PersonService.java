package de.ibb.boot.ak.relations.service;

import de.ibb.boot.ak.relations.entity.Address;
import de.ibb.boot.ak.relations.entity.Person;
import de.ibb.boot.ak.relations.projection.PersonSummary;
import de.ibb.boot.ak.relations.repository.PersonRepository;
import de.ibb.boot.ak.relations.specification.PersonSpecifications;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transaktionsgrenze für Personen und ihre Adressen.
 * Der Controller liefert nur HTTP; Anlegen, Ändern und Laden laufen hier.
 */
@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Page<Person> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Optional<Person> getPersonWithAll(Long id) {
        return personRepository.findByIdWithAll(id);
    }

    public Optional<Person> getPersonWithEntityGraph(Long id) {
        return personRepository.findByIdWithEntityGraph(id);
    }

    public List<Person> getAllWithAddresses() {
        return personRepository.findAllWithAddresses();
    }

    public Person createPerson(Person person) {
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

        return personRepository.save(toSave);
    }

    public Optional<Person> updatePerson(Long id, Person personDetails) {
        return personRepository.findById(id).map(person -> {
            person.setFirstname(personDetails.getFirstname());
            person.setLastname(personDetails.getLastname());
            person.setEmail(personDetails.getEmail());
            return personRepository.save(person);
        });
    }

    public boolean deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }

    public Optional<Person> addAddress(Long id, Address address) {
        return personRepository.findById(id).map(person -> {
            person.addAddress(address);
            return personRepository.save(person);
        });
    }

    public List<Person> searchByLastname(String lastname) {
        return personRepository.findByLastname(lastname);
    }

    public Optional<Person> searchByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public List<Person> searchByCity(String city) {
        return personRepository.findByAddresses_City(city);
    }

    public List<Person> searchByFirstname(String firstname) {
        return personRepository.findByFirstnameContainingIgnoreCase(firstname);
    }

    public List<Person> searchDynamic(String lastname, String city) {
        Specification<Person> spec = Specification
                .where(PersonSpecifications.hasLastname(lastname))
                .and(PersonSpecifications.hasAddressInCity(city));
        return personRepository.findAll(spec);
    }

    public List<PersonSummary> searchSummary(String lastname) {
        return personRepository.findSummaryByLastname(lastname);
    }
}
