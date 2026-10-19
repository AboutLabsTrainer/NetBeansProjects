package de.ibb.springbootbasic.service;

import de.ibb.springbootbasic.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * MockPersonService - Alternative Implementierung für Tests
 *
 * Diese Implementation liefert statische Mock-Daten.
 * Nützlich für Tests oder wenn du keine echten Daten brauchst.
 *
 * Um diesen Service zu nutzen statt DatabaseService:
 * - Entferne @Primary von DatabasePersonService
 * - Füge @Primary hier hinzu
 * - ODER nutze @Qualifier("mockService") im Controller
 *
 * @Service("mockService") - Bean bekommt den Namen "mockService"
 */
@Service("mockService")
public class MockPersonService implements PersonServiceInterface {

    public MockPersonService() {
        System.out.println("🧪 MockPersonService wird erstellt (wird nicht aktiv genutzt)");
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> mockPersons = new ArrayList<>();
        mockPersons.add(new Person(1L, "Test", "User", "test@example.com"));
        mockPersons.add(new Person(2L, "Demo", "Person", "demo@example.com"));
        mockPersons.add(new Person(3L, "Mock", "Data", "mock@example.com"));
        return mockPersons;
    }

    @Override
    public Person getPersonById(Long id) {
        return new Person(id, "Test", "User", "test@example.com");
    }

    @Override
    public Person createPerson(Person person) {
        person.setId(999L);
        return person;
    }

    @Override
    public int getPersonCount() {
        return 3;
    }
}
