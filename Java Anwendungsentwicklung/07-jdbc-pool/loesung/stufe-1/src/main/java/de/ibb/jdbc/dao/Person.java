package de.ibb.jdbc.dao;

public record Person(Integer id, String name, int alter, String email) {

    public Person(String name, int alter, String email) {
        this(null, name, alter, email);
    }

    public Person withId(int neueId) {
        return new Person(neueId, name, alter, email);
    }
}
