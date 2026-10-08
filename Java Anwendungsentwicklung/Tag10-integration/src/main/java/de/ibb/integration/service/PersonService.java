package de.ibb.integration.service;

import de.ibb.integration.entity.Person;
import de.ibb.integration.repository.PersonRepository;
import java.util.List;
import java.util.Optional;

/**
 * PersonService - Geschäftslogikschicht.
 * 
 * Hier findet Validierung und Geschäftslogik statt.
 * Die UI ruft NUR den Service auf, NIEMALS das Repository direkt!
 */
public class PersonService {
    
    private final PersonRepository repository;
    
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Speichert oder aktualisiert eine Person.
     * @throws IllegalArgumentException wenn Validierung fehlschlägt
     */
    public Person save(Person person) {
        // Validierung
        validate(person);
        
        // Geschäftslogik: E-Mail normalisieren
        if (person.getEmail() != null) {
            person.setEmail(person.getEmail().toLowerCase().trim());
        }
        
        return repository.save(person);
    }
    
    public Optional<Person> findById(Long id) {
        return repository.findById(id);
    }
    
    public List<Person> findAll() {
        return repository.findAll();
    }
    
    public List<Person> search(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return findAll();
        }
        return repository.findByNameContaining(searchTerm.trim());
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public long count() {
        return repository.count();
    }
    
    // ══════════════════════════════════════════════════════════════
    // VALIDIERUNG
    // ══════════════════════════════════════════════════════════════
    
    private void validate(Person person) {
        if (person.getName() == null || person.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer sein!");
        }
        
        if (person.getName().length() > 100) {
            throw new IllegalArgumentException("Name darf max. 100 Zeichen haben!");
        }
        
        if (person.getAlter() != null && (person.getAlter() < 0 || person.getAlter() > 150)) {
            throw new IllegalArgumentException("Alter muss zwischen 0 und 150 liegen!");
        }
        
        if (person.getEmail() != null && !person.getEmail().isEmpty()) {
            if (!person.getEmail().contains("@")) {
                throw new IllegalArgumentException("Ungültige E-Mail-Adresse!");
            }
        }
    }
}
