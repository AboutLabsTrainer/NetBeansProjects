package com.example.personmanagement.controller;

import com.example.personmanagement.domain.Person;
import com.example.personmanagement.service.PersonService;
import com.example.personmanagement.session.PersonFavorites;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * MVC-Controller für Liste und Formular.
 * Die Favoriten kommen aus einer Session-Bean. Der Controller bleibt ein Singleton und spricht sie über den Scoped Proxy an.
 */
@Controller
@RequestMapping("/persons")
public class PersonMvcController {

    private final PersonService personService;
    private final PersonFavorites personFavorites;

    public PersonMvcController(PersonService personService, PersonFavorites personFavorites) {
        this.personService = personService;
        this.personFavorites = personFavorites;
    }

    @GetMapping
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("favorites", personFavorites.getFavoritePersonIds());
        return "persons";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";
    }

    @PostMapping("/add")
    public String addPerson(@Valid @ModelAttribute("person") Person person,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-person";
        }
        personService.createPerson(person);
        return "redirect:/persons";
    }
}

