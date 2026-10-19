package de.ibb.boot.ak.relations.entity;

/**
 * Unterscheidet die Art einer Adresse, gespeichert als Enum in der Address-Entity.
 */
public enum AddressType {
    HOME,      // Hauptwohnsitz
    WORK,      // Geschäftsadresse
    BILLING,   // Rechnungsadresse
    SHIPPING   // Lieferadresse
}
