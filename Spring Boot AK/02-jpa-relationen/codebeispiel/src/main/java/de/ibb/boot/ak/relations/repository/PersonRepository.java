package de.ibb.boot.ak.relations.repository;

import de.ibb.boot.ak.relations.entity.AddressType;
import de.ibb.boot.ak.relations.entity.Person;
import de.ibb.boot.ak.relations.projection.PersonSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring-Data-Repository für Personen, inklusive Specifications und Entity-Graph.
 * Abgeleitete Namen, JPQL und der Entity-Graph sind drei Wege, Beziehungen gezielt zu laden oder zu filtern.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Optional<Person> findByEmail(String email);

    List<Person> findByLastname(String lastname);

    Page<Person> findByLastname(String lastname, Pageable pageable);

    List<Person> findByFirstnameContainingIgnoreCase(String firstname);

    List<Person> findByFirstnameAndLastname(String firstname, String lastname);

    List<Person> findByLastnameOrderByFirstnameAsc(String lastname);

    List<Person> findByAddresses_City(String city);

    List<Person> findByAddresses_Type(AddressType type);

    List<PersonSummary> findSummaryByLastname(String lastname);

    @Query("SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.addresses")
    List<Person> findAllWithAddresses();

    @Query("SELECT DISTINCT p FROM Person p "
            + "LEFT JOIN FETCH p.addresses "
            + "LEFT JOIN FETCH p.orders "
            + "WHERE p.id = :id")
    Optional<Person> findByIdWithAll(@Param("id") Long id);

    @EntityGraph(attributePaths = {"addresses", "orders"})
    @Query("SELECT p FROM Person p WHERE p.id = :id")
    Optional<Person> findByIdWithEntityGraph(@Param("id") Long id);
}
