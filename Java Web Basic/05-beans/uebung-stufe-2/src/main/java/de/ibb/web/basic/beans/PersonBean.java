package de.ibb.web.basic.beans;

import java.io.Serializable;

public class PersonBean implements Serializable {
    private String email;
    private int age;

    public PersonBean() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public boolean hasValidEmail() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isAdult() {
        // TODO
        throw new UnsupportedOperationException("TODO");
    }
}
