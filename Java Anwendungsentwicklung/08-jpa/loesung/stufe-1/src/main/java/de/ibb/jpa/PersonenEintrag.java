package de.ibb.jpa;

public class PersonenEintrag {

    private final Long id;
    private final String name;
    private final Integer alter;
    private final String email;

    public PersonenEintrag(Long id, String name, Integer alter, String email) {
        this.id = id;
        this.name = name;
        this.alter = alter;
        this.email = email;
    }

    public String anzeige() {
        return String.format("Person[id=%d, name=%s, alter=%d, email=%s]", id, name, alter, email);
    }
}
