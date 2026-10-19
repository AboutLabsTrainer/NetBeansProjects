package de.ibb.model;

/**
 * Sitzungsobjekt nach erfolgreichem Login. Enthält id und E-Mail, nicht das Passwort.
 */
public class Customer {
    private long id;
    private String email;

    public Customer(long id, String email) {
        this.id = id;
        this.email = email;
    }
    public long getId() { return id; }
    public String getEmail() { return email; }
}
