package de.ibb.jpa.entity;

import jakarta.persistence.*;

/**
 * Person Entity - Eine JPA-gemanagte Klasse.
 * 
 * Tag 8 des Kurses "Java Anwendungsentwicklung"
 * 
 * Diese Klasse wird automatisch auf eine Datenbank-Tabelle gemappt!
 * Keine SQL-Queries, keine ResultSet-Verarbeitung - JPA macht das alles.
 * 
 * Wichtige Annotations:
 * - @Entity: "Diese Klasse ist eine Tabelle"
 * - @Table: Optionale Tabellen-Konfiguration
 * - @Id: "Das ist der Primary Key"
 * - @GeneratedValue: "Die Datenbank generiert die ID"
 * - @Column: Optionale Spalten-Konfiguration
 */
@Entity
@Table(name = "personen")
public class Person {
    
    // ══════════════════════════════════════════════════════════════
    // FELDER (werden zu Spalten)
    // ══════════════════════════════════════════════════════════════
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(name = "alter")  // "alter" ist ein SQL-Keyword, daher explizit
    private Integer alter;
    
    @Column(length = 100)
    private String email;
    
    // ══════════════════════════════════════════════════════════════
    // KONSTRUKTOREN
    // ══════════════════════════════════════════════════════════════
    
    /**
     * Standard-Konstruktor - PFLICHT für JPA!
     * JPA braucht einen no-arg Konstruktor um Objekte zu erstellen.
     */
    public Person() {
    }
    
    /**
     * Convenience-Konstruktor für neue Personen.
     */
    public Person(String name, Integer alter, String email) {
        this.name = name;
        this.alter = alter;
        this.email = email;
    }
    
    // ══════════════════════════════════════════════════════════════
    // GETTER & SETTER (JPA braucht diese!)
    // ══════════════════════════════════════════════════════════════
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAlter() {
        return alter;
    }
    
    public void setAlter(Integer alter) {
        this.alter = alter;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    // ══════════════════════════════════════════════════════════════
    // toString (zum Debuggen)
    // ══════════════════════════════════════════════════════════════
    
    @Override
    public String toString() {
        return String.format("Person[id=%d, name=%s, alter=%d, email=%s]",
            id, name, alter, email);
    }
}
