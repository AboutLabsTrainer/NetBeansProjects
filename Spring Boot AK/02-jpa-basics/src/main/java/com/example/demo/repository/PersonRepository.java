package com.example.demo.repository;

import com.example.demo.model.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring-Data-Repository für Person.
 * Die Implementierung erzeugt Spring Data aus dem Interface. findByEmail wird aus dem Methodennamen zu einer Abfrage.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	// Spring Data JPA generiert die Implementierung automatisch.
	Optional<Person> findByEmail(String email);
}

