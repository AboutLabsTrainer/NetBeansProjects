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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@AllArgsConstructor
@EqualsAndHashCode(exclude = "person")
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
}
