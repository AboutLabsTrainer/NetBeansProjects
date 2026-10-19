package de.ibb.boot.ak.security.entity;

/**
 * Grobe Rolle eines Benutzers, aus der Spring Security eine ROLE_-Authority macht.
 * hasRole in der Filterkette meint diese Werte.
 */
public enum Role {
    USER,
    ADMIN,
    MODERATOR
}
