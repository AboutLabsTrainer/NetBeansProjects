package de.ibb.integration.service;

public final class PersonPruefung {

    private PersonPruefung() {
    }

    public static void pruefe(String name, Integer alter, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer sein!");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Name darf max. 100 Zeichen haben!");
        }
        if (alter != null && (alter < 0 || alter > 150)) {
            throw new IllegalArgumentException("Alter muss zwischen 0 und 150 liegen!");
        }
        if (email != null && !email.isEmpty() && !email.contains("@")) {
            throw new IllegalArgumentException("Ungültige E-Mail-Adresse!");
        }
    }
}
