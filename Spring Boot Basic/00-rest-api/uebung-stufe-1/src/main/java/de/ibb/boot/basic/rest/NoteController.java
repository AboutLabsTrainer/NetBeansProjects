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

    @GetMapping
    public List<Note> all() {
        // TODO: gib eine Notiz mit id 1 und Titel "Erste Notiz" zurueck
        return List.of();
    }

}
