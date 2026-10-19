package de.ibb.boot.ak.jpa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
    private final NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/notes")
    public java.util.List<Note> all() {
        return repository.findAll();
    }

    @PostMapping("/api/notes")
    public Note create(@RequestParam String title) {
        // TODO: Note anlegen, Titel setzen, speichern und zurueckgeben
        return new Note();
    }

}
