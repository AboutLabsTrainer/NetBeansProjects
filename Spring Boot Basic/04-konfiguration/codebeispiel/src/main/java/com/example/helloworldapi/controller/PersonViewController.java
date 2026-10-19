package com.example.helloworldapi.controller;

import com.example.helloworldapi.config.AppProperties;
import com.example.helloworldapi.model.Person;
import com.example.helloworldapi.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import java.util.List;

/**
 * MVC-Controller der Personenansichten in diesem Konfigurationsbeispiel.
 * Die Seiten kommen aus Thymeleaf, die Daten aus dem Service. Profil und Logging ändern den Ablauf nicht, nur die Umgebung.
 */
@Controller
@RequiredArgsConstructor
public class PersonViewController {

    private static final Logger log = LoggerFactory.getLogger(PersonViewController.class);

    private final PersonService personService;
    private final AppProperties appProperties;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }

    @GetMapping("/persons")
    public String listPersons(Model model) {
        log.debug("Showing persons list");

        List<Person> persons = personService.getAllPersons();
        log.info("Displaying {} persons", persons.size());

        model.addAttribute("persons", persons);
        model.addAttribute("appName", appProperties.getName());
        model.addAttribute("appVersion", appProperties.getVersion());
        model.addAttribute("maxPersons", appProperties.getMaxPersons());

        return "persons-list";
    }

    @GetMapping("/persons/{id}")
    public String showPersonDetails(@PathVariable Long id, Model model) {
        log.debug("Showing details for person ID: {}", id);

        Person person = personService.getPersonById(id);

        if (person == null) {
            log.warn("Person with ID {} not found, redirecting to list", id);
            return "redirect:/persons";
        }

        log.debug("Displaying person: {} {}", person.getFirstname(), person.getLastname());
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
