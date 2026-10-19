package de.ibb.contacteditor;

public class Kontakt {

    private final String name;
    private final String email;

    public Kontakt(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public boolean istVollstaendig() {
        return name != null && !name.isBlank() && email != null && email.contains("@");
    }
}
