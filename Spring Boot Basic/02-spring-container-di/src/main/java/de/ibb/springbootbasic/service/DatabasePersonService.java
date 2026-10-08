package de.ibb.springbootbasic.service;

import de.ibb.springbootbasic.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import net.datafaker.Faker;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * DatabasePersonService - Hauptimplementierung mit DataFaker
 *
 * @Service - Sagt Spring: "Das ist ein Service - verwalte ihn!"
 * @Primary - Wenn mehrere Implementierungen existieren, nimm diese standardmäßig
 *
 * Bean Lifecycle:
 * 1. Konstruktor wird aufgerufen
 * 2. Dependencies werden injected
 * 3. @PostConstruct Methoden werden ausgeführt
 * 4. Bean ist bereit für Nutzung
 * 5. ...App läuft...
 * 6. @PreDestroy Methoden werden ausgeführt
 * 7. Bean wird zerstört
 */
@Service("databaseService")
@Primary
public class DatabasePersonService implements PersonServiceInterface {

    private final List<Person> persons = new ArrayList<>();
    private final Faker faker;

    /**
     * Konstruktor wird von Spring aufgerufen
     * Spring erstellt automatisch einen Faker (siehe AppConfig)
     */
    public DatabasePersonService(Faker faker) {
        this.faker = faker;
        System.out.println("🔧 1️⃣ DatabasePersonService Konstruktor wird aufgerufen");
    }

    /**
     * @PostConstruct - Wird NACH dem Konstruktor aufgerufen
     * Hier initialisieren wir die Testdaten
     */
    @PostConstruct
    public void init() {
        System.out.println("🚀 2️⃣ @PostConstruct - Service ist bereit!");
        System.out.println("📊 3️⃣ Generiere 100 Testpersonen mit DataFaker...");

        for (long i = 1; i <= 100; i++) {
            persons.add(new Person(
                    i,
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress()
            ));
        }

        System.out.println("✅ " + persons.size() + " Personen erfolgreich generiert!");
        System.out.println("💡 Beispiel: " + persons.get(0).getFirstName() + " "
                + persons.get(0).getLastName() + " - " + persons.get(0).getEmail());
    }

    /**
     * @PreDestroy - Wird BEIM Shutdown aufgerufen
     * Hier könntest du Ressourcen freigeben, Connections schließen, etc.
     */
    @PreDestroy
    public void cleanup() {
        System.out.println("🔴 @PreDestroy - DatabasePersonService wird aufgeräumt!");
        System.out.println("👋 Tschüss, Spring Container!");
    }

    @Override
    public List<Person> getAllPersons() {
        return new ArrayList<>(persons);
    }

    @Override
    public Person getPersonById(Long id) {
        return persons.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person createPerson(Person person) {
        person.setId((long) (persons.size() + 1));
        persons.add(person);
        System.out.println("✨ Neue Person erstellt: " + person.getFirstName() + " " + person.getLastName());
        return person;
    }

    @Override
    public int getPersonCount() {
        return persons.size();
    }
}
