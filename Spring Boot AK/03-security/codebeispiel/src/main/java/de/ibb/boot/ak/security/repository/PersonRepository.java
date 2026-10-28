package de.ibb.boot.ak.security.repository;

import de.ibb.boot.ak.security.entity.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring-Data-Repository für Personen in der Security-Anwendung.
 * findAll und findById laden den Owner gleich mit, weil die Security-Ausdrücke dessen Namen nach der Transaktion lesen.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Override
    @EntityGraph(attributePaths = "owner")
    List<Person> findAll();

    @Override
    @EntityGraph(attributePaths = "owner")
    Optional<Person> findById(Long id);

    List<Person> findByOwnerUsername(String username);
}
