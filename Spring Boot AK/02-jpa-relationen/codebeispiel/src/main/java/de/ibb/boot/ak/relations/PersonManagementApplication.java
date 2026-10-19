package de.ibb.boot.ak.relations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet das Beispiel zu JPA-Beziehungen.
 * Person, Address und Order liegen in einem Persistence-Context. Das aktive Profil mysql wählt die DataSource.
 */
@SpringBootApplication
public class PersonManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonManagementApplication.class, args);
    }
}
