package de.ibb.jpa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Bestellung, die zu genau einer Person gehört.
 */
@Entity
@Table(name = "jae09_bestellungen")
public class Bestellung {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate datum;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal betrag;
    
    private String beschreibung;
    
    // @ManyToOne: Viele Bestellungen gehören zu einer Person
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
    
    // Konstruktoren
    public Bestellung() {}
    
    public Bestellung(LocalDate datum, BigDecimal betrag, String beschreibung) {
        this.datum = datum;
        this.betrag = betrag;
        this.beschreibung = beschreibung;
    }
    
    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }
    
    public BigDecimal getBetrag() { return betrag; }
    public void setBetrag(BigDecimal betrag) { this.betrag = betrag; }
    
    public String getBeschreibung() { return beschreibung; }
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }
    
    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
    
    @Override
    public String toString() {
        return String.format("Bestellung[id=%d, datum=%s, betrag=%.2f€, beschreibung=%s]",
            id, datum, betrag, beschreibung);
    }
}
