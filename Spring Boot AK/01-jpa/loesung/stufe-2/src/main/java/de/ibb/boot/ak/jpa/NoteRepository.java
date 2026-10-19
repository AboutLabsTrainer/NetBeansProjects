package de.ibb.boot.ak.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

    java.util.List<Note> findByTitle(String title);
}
