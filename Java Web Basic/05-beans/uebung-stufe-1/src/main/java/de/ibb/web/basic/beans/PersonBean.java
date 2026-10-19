package de.ibb.web.basic.beans;

import java.io.Serializable;

public class PersonBean implements Serializable {
    private String firstName;
    private String lastName;

    public PersonBean() {}

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDisplayName() {
        // TODO: vorhandene Namen mit einem Leerzeichen verbinden. Leere Teile weglassen.
        throw new UnsupportedOperationException("TODO");
    }
}
