package com.fleetdata.personmanagement.repository;

import com.fleetdata.personmanagement.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring-Data-Repository für Personen in der Security-Anwendung.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByOwnerUsername(String username);
}
