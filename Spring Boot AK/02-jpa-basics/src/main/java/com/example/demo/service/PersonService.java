package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transaktionsgrenze für die Personen-Anwendungsfälle.
 * Innerhalb einer Methode teilen sich die Repository-Aufrufe einen Persistence-Context. Der Controller startet keine eigene Transaktion.
 */
@Service
@Transactional
public class PersonService {

	private final PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	public Optional<Person> getPersonById(Long id) {
		return personRepository.findById(id);
	}

	public Person createPerson(Person person) {
		return personRepository.save(person);
	}

	public Person updatePerson(Long id, Person personDetails) {
		Person person = personRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Person not found with id " + id));

		person.setFirstname(personDetails.getFirstname());
		person.setLastname(personDetails.getLastname());
		person.setEmail(personDetails.getEmail());

		return personRepository.save(person);
	}

	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
}

