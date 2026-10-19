package de.ibb.boot.basic.rest;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final List<Note> notes = new CopyOnWriteArrayList<>(List.of(new Note(1, "Erste Notiz")));

    @GetMapping
    public List<Note> all() {
        return notes;
    }

    @PostMapping
    public Note create(@RequestBody NoteRequest request) {
        // TODO: neue Notiz mit fortlaufender id anlegen, speichern und zurueckgeben
        return new Note(0, "");
    }

}
