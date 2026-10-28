package de.ibb.boot.ak.relations.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity auf der Many-Seite der Beziehung Person zu Adressen.
 * Der Fremdschlüssel liegt hier. Anlegen und Entfernen entlang der Beziehung steuert die One-to-Many-Seite an Person.
 */
@Entity
@Table(name = "jsprbak02_addresses")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Straße darf nicht leer sein")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String street;

    @NotBlank(message = "Stadt darf nicht leer sein")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String city;

    @NotBlank(message = "PLZ darf nicht leer sein")
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String zipCode;

    @Size(max = 50)
    @Column(length = 50)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private AddressType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @ToString.Exclude
    @JsonBackReference("person-addresses")
    private Person person;

    /**
     * Gleich ist nur dieselbe Zeile in der Datenbank, also dieselbe id. Neue Objekte ohne id sind nur sich selbst gleich.
     * hashCode hängt nicht von id oder Feldern ab und bleibt deshalb über persist und Änderungen hinweg stabil.
     * Der Vergleich liest other.getId(), damit er auch mit einem Hibernate-Proxy funktioniert.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address other)) {
            return false;
        }
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Address.class.hashCode();
    }
}
