package de.ibb.jdbc.dao;

import java.util.List;
import java.util.Optional;

/**
 * PersonDao - Data Access Object Interface für Personen.
 * 
 * Tag 7 des Kurses "Java Anwendungsentwicklung"
 * 
 * Definiert die Schnittstelle für Datenbankzugriffe.
 * Die konkrete Implementierung (JDBC, JPA, ...) ist austauschbar!
 */
public interface PersonDao {
    
    /**
     * Findet eine Person anhand ihrer ID.
     * @return Optional mit der Person, oder empty wenn nicht gefunden
     */
    Optional<Person> findById(int id);
    
    /**
     * Findet alle Personen.
     * @return Liste aller Personen (kann leer sein)
     */
    List<Person> findAll();
    
    /**
     * Findet Personen nach Name (enthält).
     * @param name Suchbegriff (case-insensitive)
     * @return Liste der gefundenen Personen
     */
    List<Person> findByNameContaining(String name);
    
    /**
     * Speichert eine neue Person.
     * @param person Die zu speichernde Person (ohne ID)
     * @return Die Person MIT generierter ID
     */
    Person save(Person person);
    
    /**
     * Aktualisiert eine bestehende Person.
     * @param person Die zu aktualisierende Person (mit ID)
     * @return true wenn erfolgreich, false wenn Person nicht existiert
     */
    boolean update(Person person);
    
    /**
     * Löscht eine Person anhand ihrer ID.
     * @param id Die ID der zu löschenden Person
     * @return true wenn gelöscht, false wenn nicht gefunden
     */
    boolean deleteById(int id);
    
    /**
     * Zählt alle Personen.
     * @return Anzahl der Personen in der Datenbank
     */
    long count();
}
