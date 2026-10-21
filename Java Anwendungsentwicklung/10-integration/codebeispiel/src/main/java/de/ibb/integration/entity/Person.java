package de.ibb.integration.entity;

import jakarta.persistence.*;

/**
 * Person Entity für die integrierte Anwendung.
 */
@Entity
@Table(name = "jae10_personen")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(name = "alter")
    private Integer alter;
    
    @Column(length = 100)
    private String email;
    
    public Person() {}
    
    public Person(String name, Integer alter, String email) {
        this.name = name;
        this.alter = alter;
        this.email = email;
    }
    
    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Integer getAlter() { return alter; }
    public void setAlter(Integer alter) { this.alter = alter; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return String.format("Person[id=%d, name=%s, alter=%d]", id, name, alter);
    }
}
