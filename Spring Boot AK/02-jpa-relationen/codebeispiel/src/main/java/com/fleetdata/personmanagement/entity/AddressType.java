package com.fleetdata.personmanagement.entity;

/**
 * Unterscheidet die Art einer Adresse, gespeichert als Enum in der Address-Entity.
 */
public enum AddressType {
    HOME,      // Hauptwohnsitz
    WORK,      // Geschäftsadresse
    BILLING,   // Rechnungsadresse
    SHIPPING   // Lieferadresse
}
