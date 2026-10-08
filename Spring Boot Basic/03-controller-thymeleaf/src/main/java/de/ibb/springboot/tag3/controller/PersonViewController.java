package de.ibb.springboot.tag3.controller;

import de.ibb.springboot.tag3.model.Person;
import de.ibb.springboot.tag3.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * PersonViewController - HTML View Controller (NEU in Tag 3!)
 * Gibt HTML-Seiten zurück für Menschen im Browser
 *
 * Unterschied zu PersonController:
 * - @Controller statt @RestController
 * - Gibt View-Namen (String) zurück statt Objekte
 * - Nutzt Thymeleaf Templates
 * - Für Web-Oberflächen, nicht für APIs
 *
 * Endpoints:
 * - GET / - Startseite (Redirect zu /persons)
 * - GET /persons - Personen-Liste als HTML-Tabelle
 * - GET /persons/{id} - Person-Details als HTML-Seite
 *
 * Ohne Lombok, damit das Projekt überall ohne Annotation Processing kompiliert.
 */
@Controller
public class PersonViewController {

    private final PersonService personService;

    public PersonViewController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Startseite - leitet zur Personen-Liste weiter
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/persons";
    }

    /**
     * GET /persons - Zeigt alle Personen als HTML-Tabelle
     *
     * Optional mit:
     * - Pagination: ?page=0&size=10
     * - Suche: ?search=Anna
     * - Sortierung: ?sort=firstname&order=asc
     */
    @GetMapping("/persons")
    public String showPersons(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String search,
        @RequestParam(defaultValue = "id") String sort,
        @RequestParam(defaultValue = "asc") String order,
        Model model
    ) {
        List<Person> persons;

        if (search != null && !search.trim().isEmpty()) {
            persons = personService.searchPersons(search);
            model.addAttribute("search", search);
        } else {
            persons = personService.getSortedPersons(sort, order);
        }

        int totalPersons = persons.size();
        int start = Math.min(page * size, totalPersons);
        int end = Math.min(start + size, totalPersons);

        List<Person> pagePersons = start >= totalPersons ? List.of() : persons.subList(start, end);

        model.addAttribute("persons", pagePersons);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", (int) Math.ceil(totalPersons / (double) size));
        model.addAttribute("totalPersons", totalPersons);
        model.addAttribute("currentSort", sort);
        model.addAttribute("currentOrder", order);

        return "persons-list";
    }

    /**
     * GET /persons/{id} - Zeigt Details einer Person als HTML
     */
    @GetMapping("/persons/{id}")
    public String showPersonDetails(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id);

        if (person == null) {
            return "redirect:/persons";
        }

        model.addAttribute("person", person);
        return "person-details";
    }
}
