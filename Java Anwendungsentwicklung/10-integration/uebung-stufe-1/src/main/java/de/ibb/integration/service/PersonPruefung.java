package de.ibb.integration.service;

/** Regeln aus PersonService.validate, ohne Repository. */
public final class PersonPruefung {

    private PersonPruefung() {
    }

    public static void pruefe(String name, Integer alter, String email) {
        // TODO: Name leer, Name länger als 100, Alter außerhalb 0..150, E-Mail ohne @
    }
}
