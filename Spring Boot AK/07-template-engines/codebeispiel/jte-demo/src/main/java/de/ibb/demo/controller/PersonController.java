package de.ibb.demo.controller;

import de.ibb.demo.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MVC-Controller der JTE-Demo.
 * Er legt die Personen ins Model und gibt einen View-Namen zurück. Welche Engine daraus HTML macht, entscheidet die Auto-Configuration, nicht der Controller.
 */
@Controller
public class PersonController {
    
    private final PersonService personService;
    
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("engine", "JTE");
        return "persons";
    }
}
