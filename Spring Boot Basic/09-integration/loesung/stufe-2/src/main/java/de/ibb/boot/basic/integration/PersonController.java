package de.ibb.boot.basic.integration;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/api/names")
    public List<String> names() {
        return personService.names();
    }

    @GetMapping("/api/count")
    public int count() {
        return personService.names().size();
    }

}
