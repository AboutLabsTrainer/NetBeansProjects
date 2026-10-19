package de.ibb.springboot.tag3.service;

import de.ibb.springboot.tag3.model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PersonService - Business Logic für Person-Verwaltung
 * Verwaltet 100 Test-Personen im Speicher
 *
 * @author Java Fleet Systems - Elyndra Valen
 */
@Service
public class PersonService {

    private final List<Person> persons = new ArrayList<>();
    private Long nextId = 1L;

    /**
     * Initialisiert 100 Test-Personen beim Start
     */
    @PostConstruct
    public void init() {
        String[] vornamen = {
            "Anna", "Max", "Laura", "Tom", "Lisa", "Paul", "Sarah", "Tim", "Julia", "Ben",
            "Emma", "Leon", "Mia", "Lukas", "Sophie", "Felix", "Marie", "Noah", "Lena", "Jonas"
        };

        String[] nachnamen = {
            "Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meyer", "Wagner", "Becker",
            "Schulz", "Hoffmann", "Koch", "Richter", "Klein", "Wolf", "Schröder", "Neumann",
            "Schwarz", "Zimmermann", "Braun", "Krüger"
        };

        String[] domains = {
            "gmail.com", "yahoo.de", "web.de", "gmx.de", "outlook.com",
            "hotmail.de", "t-online.de", "freenet.de", "arcor.de", "aol.de"
        };

        for (int i = 0; i < 100; i++) {
            String vorname = vornamen[i % vornamen.length];
            String nachname = nachnamen[(i / vornamen.length) % nachnamen.length];
            String domain = domains[i % domains.length];

            String email = vorname.toLowerCase() + "." + nachname.toLowerCase() +
                (i > 19 ? i : "") + "@" + domain;

            persons.add(new Person(nextId++, vorname, nachname, email));
        }
    }

    /**
     * Gibt alle Personen zurück
     */
    public List<Person> getAllPersons() {
        return new ArrayList<>(persons);
    }

    /**
     * Findet Person nach ID
     */
    public Person getPersonById(Long id) {
        return persons.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    /**
     * Sucht Personen nach Vor- oder Nachnamen
     */
    public List<Person> searchPersons(String query) {
        String lowerQuery = query.toLowerCase().trim();
        return persons.stream()
            .filter(p -> p.getFirstname().toLowerCase().contains(lowerQuery) ||
                p.getLastname().toLowerCase().contains(lowerQuery))
            .collect(Collectors.toList());
    }

    /**
     * Gibt sortierte Personen zurück
     * @param sortBy Feld zum Sortieren (id, firstname, lastname, email)
     * @param order Sortierreihenfolge (asc, desc)
     */
    public List<Person> getSortedPersons(String sortBy, String order) {
        List<Person> sorted = new ArrayList<>(persons);

        Comparator<Person> comparator = switch (sortBy.toLowerCase()) {
            case "firstname" -> Comparator.comparing(Person::getFirstname);
            case "lastname" -> Comparator.comparing(Person::getLastname);
            case "email" -> Comparator.comparing(Person::getEmail);
            default -> Comparator.comparing(Person::getId);
        };

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        sorted.sort(comparator);
        return sorted;
    }

    /**
     * Fügt eine neue Person hinzu
     */
    public Person addPerson(Person person) {
        person.setId(nextId++);
        persons.add(person);
        return person;
    }

    /**
     * Aktualisiert eine Person
     */
    public Person updatePerson(Long id, Person updatedPerson) {
        Person person = getPersonById(id);
        if (person != null) {
            person.setFirstname(updatedPerson.getFirstname());
            person.setLastname(updatedPerson.getLastname());
            person.setEmail(updatedPerson.getEmail());
            return person;
        }
        return null;
    }

    /**
     * Löscht eine Person
     */
    public boolean deletePerson(Long id) {
        return persons.removeIf(p -> p.getId().equals(id));
    }
}
