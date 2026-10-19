package com.fleetdata.personmanagement.projection;

/**
 * Closed Projection: Spring Data füllt nur Vorname, Nachname und E-Mail.
 * Die restlichen Spalten und die Beziehungen werden für diese Abfrage nicht geladen.
 */
public interface PersonSummary {
    String getFirstname();
    String getLastname();
    String getEmail();
}
