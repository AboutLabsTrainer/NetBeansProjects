package com.example.repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring-Data-Repository für User.
 * Seine Aufrufe sind die Join-Points, die der Monitoring-Aspect zählt.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
