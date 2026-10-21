package de.ibb.jpa.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Person mit optionaler Adresse und mehreren Bestellungen für die Beziehungs-Demos.
 */
@Entity
@Table(name = "jae09_personen")
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
    
    // @OneToOne: Eine Person hat eine Adresse
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;
    
    // @OneToMany: Eine Person hat viele Bestellungen
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bestellung> bestellungen = new ArrayList<>();
    
    // Konstruktoren
    public Person() {}
    
    public Person(String name, Integer alter, String email) {
        this.name = name;
        this.alter = alter;
        this.email = email;
    }
    
    // Helper-Methoden für Bestellungen
    public void addBestellung(Bestellung bestellung) {
        bestellungen.add(bestellung);
        bestellung.setPerson(this);
    }
    
    public void removeBestellung(Bestellung bestellung) {
        bestellungen.remove(bestellung);
        bestellung.setPerson(null);
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
    
    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
    
    public List<Bestellung> getBestellungen() { return bestellungen; }
    
    @Override
    public String toString() {
        return String.format("Person[id=%d, name=%s, alter=%d, email=%s]",
            id, name, alter, email);
    }
}
