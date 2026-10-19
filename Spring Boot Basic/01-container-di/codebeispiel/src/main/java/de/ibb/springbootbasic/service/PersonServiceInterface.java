package de.ibb.springbootbasic.service;

import de.ibb.springbootbasic.model.Person;

import java.util.List;

/**
 * PersonService Interface
 *
 * Definiert die Contract für alle PersonService-Implementierungen.
 * Nützlich wenn du mehrere Implementierungen hast (z.B. DatabaseService, MockService)
 *
 * Best Practice: Programmiere gegen Interfaces, nicht gegen Implementierungen!
 */
public interface PersonServiceInterface {

    /**
     * Gibt alle Personen zurück
     */
    List<Person> getAllPersons();

    /**
     * Gibt eine Person anhand der ID zurück
     *
     * @return Person oder null wenn nicht gefunden
     */
    Person getPersonById(Long id);

    /**
     * Erstellt eine neue Person
     *
     * @return Die erstellte Person mit generierter ID
     */
    Person createPerson(Person person);

    /**
     * Gibt die Anzahl aller Personen zurück
     */
    int getPersonCount();
}
