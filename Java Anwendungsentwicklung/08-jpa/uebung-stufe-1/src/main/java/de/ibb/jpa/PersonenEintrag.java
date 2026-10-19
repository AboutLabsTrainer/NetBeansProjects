package de.ibb.jpa;

/** Anzeige im Format von Person.toString aus dem JPA-Beispiel, ohne Persistence Unit. */
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
        // TODO: Person[id=%d, name=%s, alter=%d, email=%s]
        return "";
    }
}
