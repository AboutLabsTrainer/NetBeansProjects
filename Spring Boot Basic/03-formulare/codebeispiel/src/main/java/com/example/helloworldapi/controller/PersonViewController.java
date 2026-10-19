package com.example.helloworldapi.controller;

import com.example.helloworldapi.model.Person;
import com.example.helloworldapi.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

/**
 * MVC-Controller für die Personenmasken.
 * Methoden geben View-Namen zurück. Formulardaten werden an das Person-Objekt gebunden, Bean Validation hält ungültige Eingaben auf dem Formular.
 */
@Controller
@RequiredArgsConstructor
public class PersonViewController {

    private final PersonService personService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }

    @GetMapping("/persons")
    public String listPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "persons-list";
    }

    @GetMapping("/persons/{id}")
    public String showPersonDetails(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person nicht gefunden");
        }
        model.addAttribute("person", person);
        return "person-details";
    }

    @GetMapping("/persons/new")
    public String showCreateForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    @PostMapping("/persons/new")
    public String createPerson(@Valid @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "person-form";
        }
        personService.createPerson(person);
        return "redirect:/persons";
    }

    @GetMapping("/persons/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            return "redirect:/persons";
        }
        model.addAttribute("person", person);
        return "person-edit-form";
    }

    @PostMapping("/persons/{id}/edit")
    public String updatePerson(@PathVariable Long id, @Valid @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "person-edit-form";
        }
        personService.updatePerson(id, person);
        return "redirect:/persons/" + id;
    }

    @PostMapping("/persons/{id}/delete")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return "redirect:/persons";
    }
}
