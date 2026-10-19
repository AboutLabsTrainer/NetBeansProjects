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
        if (email == null) {
            return false;
        }
        String value = email.trim();
        int at = value.indexOf('@');
        return at > 0 && at < value.length() - 1 && value.indexOf('@', at + 1) < 0;
    }

    public boolean isAdult() {
        return age >= 18;
    }
}
