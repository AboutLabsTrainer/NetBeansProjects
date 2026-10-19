package de.ibb.boot.ak.security.repository;

import de.ibb.boot.ak.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring-Data-Repository für Konten.
 * Der UserDetailsService sucht darüber den Benutzer, den die Anmeldung prüft.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
