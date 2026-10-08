package com.fleetdata.personmanagement.entity;

/**
 * Status einer Bestellung, den Abfragen im OrderRepository filtern.
 */
public enum OrderStatus {
    PENDING,     // Wartend
    CONFIRMED,   // Bestätigt
    SHIPPED,     // Versandt
    DELIVERED,   // Zugestellt
    CANCELLED    // Storniert
}
