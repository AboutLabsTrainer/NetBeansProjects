package de.ibb.boot.ak.security.controller;

import de.ibb.boot.ak.security.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Liefert Login- und Personenseiten für die formularbasierte Anmeldung.
 * Die Filterkette lässt die Login-Seite anonym zu und schützt die übrigen Seiten.
 */
@Controller
@RequiredArgsConstructor
public class WebController {

    private final PersonService personService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/persons")
    public String persons(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "persons";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/persons";
    }
}
