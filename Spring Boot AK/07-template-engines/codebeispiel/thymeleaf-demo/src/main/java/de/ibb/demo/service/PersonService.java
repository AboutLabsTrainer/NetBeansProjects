package de.ibb.demo.service;

import de.ibb.demo.model.Person;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Erzeugt die Demodaten, die der Thymeleaf-Controller anzeigt.
 * Als Service-Bean liegt die Liste einmal im Application-Context und wird von der Seite gelesen.
 */
@Service
public class PersonService {
    
    private final Faker faker = new Faker(Locale.GERMAN);
    private final List<Person> persons;
    
    public PersonService() {
        this.persons = generatePersons(50);
    }
    
    public List<Person> getAllPersons() {
        return persons;
    }
    
    public Person getPersonById(Long id) {
        return persons.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    private List<Person> generatePersons(int count) {
        List<Person> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Person person = new Person();
            person.setId((long) i);
            person.setFirstName(faker.name().firstName());
            person.setLastName(faker.name().lastName());
            person.setEmail(faker.internet().emailAddress());
            person.setPhone(faker.phoneNumber().phoneNumber());
            person.setCity(faker.address().city());
            person.setCountry("Deutschland");
            person.setJobTitle(faker.job().title());
            person.setCompany(faker.company().name());
            list.add(person);
        }
        return list;
    }
}
