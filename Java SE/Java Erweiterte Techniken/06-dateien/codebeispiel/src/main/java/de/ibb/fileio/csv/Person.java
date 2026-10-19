package de.ibb.fileio.csv;

/**
 * Eine Datenzeile aus der CSV: Name, Alter und Stadt als typisierte Werte.
 */
public record Person(String name, int alter, String stadt) {}

