package de.ibb.jdbc.dao;

/**
 * Person - Einfache Entity-Klasse als Java Record.
 * 
 * Tag 7 des Kurses "Java Anwendungsentwicklung"
 * Java Fleet Systems Consulting - java-developer.online
 * 
 * Records (seit Java 16) sind perfekt für einfache Datenklassen:
 * - Immutable (unveränderlich)
 * - equals(), hashCode(), toString() automatisch
 * - Kompakte Syntax
 * 
 * @author Franz-Martin (CTO, Java Fleet Systems Consulting)
 */
public record Person(
    Integer id,
    String name,
    int alter,
    String email
) {
    /**
     * Konstruktor für neue Personen (ohne ID).
     * Die ID wird von der Datenbank generiert.
     */
    public Person(String name, int alter, String email) {
        this(null, name, alter, email);
    }
    
    /**
     * Erstellt eine Kopie mit neuer ID.
     * Nützlich nach dem Speichern, wenn die DB die ID generiert hat.
     */
    public Person withId(int id) {
        return new Person(id, this.name, this.alter, this.email);
    }
}
