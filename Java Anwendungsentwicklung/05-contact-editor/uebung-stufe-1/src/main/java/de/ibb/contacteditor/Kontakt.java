package de.ibb.contacteditor;

/** Name und E-Mail aus ContactEditorUI, ohne JFrame. */
public class Kontakt {

    private final String name;
    private final String email;

    public Kontakt(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public boolean istVollstaendig() {
        // TODO: Name nicht leer, E-Mail enthält @
        return false;
    }
}
