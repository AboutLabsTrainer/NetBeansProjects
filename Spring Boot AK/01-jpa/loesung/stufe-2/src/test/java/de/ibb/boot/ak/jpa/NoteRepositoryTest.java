package de.ibb.boot.ak.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class NoteRepositoryTest {
    @Autowired
    private NoteRepository repository;

    @Test
    void savesNote() {
        Note note = new Note();
        note.setTitle("Erste Notiz");
        repository.save(note);
        assertEquals(1, repository.findAll().size());
        assertEquals("Erste Notiz", repository.findAll().get(0).getTitle());
    }

    @Test
    void findsByTitle() {
        Note note = new Note();
        note.setTitle("Erste Notiz");
        repository.save(note);
        assertEquals(1, repository.findByTitle("Erste Notiz").size());
    }

}
