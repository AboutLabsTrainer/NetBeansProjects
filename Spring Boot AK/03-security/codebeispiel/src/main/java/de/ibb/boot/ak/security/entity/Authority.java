package de.ibb.boot.ak.security.entity;

/**
 * Feine Rechte, die zusätzlich zur Rolle an einem Benutzer hängen.
 * Die Filterkette und die Methodensicherheit prüfen diese Authorities getrennt von den Rollen.
 */
public enum Authority {
    READ_PRIVILEGES,
    WRITE_PRIVILEGES,
    DELETE_PRIVILEGES,
    ADMIN_PRIVILEGES
}
