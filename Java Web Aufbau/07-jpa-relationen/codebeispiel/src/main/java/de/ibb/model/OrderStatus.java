package de.ibb.model;

/**
 * Lebenszyklus einer Bestellung. Order speichert diesen Wert als Enum.
 */
public enum OrderStatus {
    PENDING,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
