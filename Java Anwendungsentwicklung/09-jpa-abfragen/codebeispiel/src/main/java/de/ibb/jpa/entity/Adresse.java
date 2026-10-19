package de.ibb.jpa.entity;

import jakarta.persistence.*;

/**
 * Adresse, die in diesem Beispiel zu genau einer Person gehört.
 */
@Entity
@Table(name = "adressen")
public class Adresse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String strasse;
    
    @Column(nullable = false, length = 10)
    private String plz;
    
    @Column(nullable = false)
    private String ort;
    
    // Konstruktoren
    public Adresse() {}
    
    public Adresse(String strasse, String plz, String ort) {
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }
    
    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStrasse() { return strasse; }
    public void setStrasse(String strasse) { this.strasse = strasse; }
    
    public String getPlz() { return plz; }
    public void setPlz(String plz) { this.plz = plz; }
    
    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }
    
    @Override
    public String toString() {
        return String.format("Adresse[%s, %s %s]", strasse, plz, ort);
    }
}
