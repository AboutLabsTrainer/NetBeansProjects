package de.ibb.springbootbasic.controller;

import de.ibb.springbootbasic.model.Person;
import de.ibb.springbootbasic.service.PersonServiceInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PersonController - REST API für Person-Verwaltung
 *
 * @RestController - Kombiniert @Controller + @ResponseBody
 * Alle Methoden returnen automatisch JSON
 *
 * @RequestMapping("/api/persons") - Base-URL für alle Endpoints
 *
 * @RequiredArgsConstructor - Lombok generiert automatisch einen Konstruktor
 * für alle final Fields. Spring nutzt diesen Konstruktor für DI!
 *
 * Dependency Injection Best Practice: Constructor Injection
 * ✅ final möglich (immutable, Thread-safe)
 * ✅ Einfach testbar (Service im Test übergeben)
 * ✅ Klare Dependencies (siehst du im Konstruktor)
 * ✅ Spring Best Practice seit Jahren!
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonServiceInterface personService;

    /**
     * Constructor Injection mit @Qualifier("databaseService") = 100 Faker-Personen.
     * Um MockService zu nutzen: @Qualifier("mockService")
     */
    public PersonController(@Qualifier("databaseService") PersonServiceInterface personService) {
        this.personService = personService;
    }

    /**
     * GET /api/persons
     * Gibt alle Personen zurück
     *
     * curl http://localhost:8081/api/persons
     */
    @GetMapping
    public List<Person> getAllPersons() {
        System.out.println("📥 GET /api/persons - Alle Personen abrufen");
        return personService.getAllPersons();
    }

    /**
     * GET /api/persons/{id}
     * Gibt eine Person anhand der ID zurück
     *
     * curl http://localhost:8081/api/persons/1
     */
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        System.out.println("📥 GET /api/persons/" + id);
        return personService.getPersonById(id);
    }

    /**
     * POST /api/persons
     * Erstellt eine neue Person
     *
     * curl -X POST http://localhost:8081/api/persons \
     *   -H "Content-Type: application/json" \
     *   -d '{"firstName":"Max","lastName":"Mustermann","email":"max@test.de"}'
     */
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        System.out.println("📥 POST /api/persons - Neue Person erstellen");
        return personService.createPerson(person);
    }

    /**
     * GET /api/persons/count
     * Gibt die Anzahl aller Personen zurück
     *
     * curl http://localhost:8081/api/persons/count
     */
    @GetMapping("/count")
    public int getPersonCount() {
        System.out.println("📥 GET /api/persons/count");
        return personService.getPersonCount();
    }
}
