package de.ibb.springboot.tag3.model;

/**
 * Person Model-Klasse
 * Repräsentiert eine Person mit ID, Vorname, Nachname und E-Mail
 *
 * Ohne Lombok, damit das Projekt auch ohne Annotation Processing in allen Umgebungen kompiliert.
 */
public class Person {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;

    public Person() {
    }

    public Person(Long id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
