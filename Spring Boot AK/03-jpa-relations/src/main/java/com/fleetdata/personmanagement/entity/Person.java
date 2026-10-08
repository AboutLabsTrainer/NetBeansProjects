package com.fleetdata.personmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * Aggregatwurzel mit je einer One-to-Many-Beziehung zu Adressen und Bestellungen.
 * Die Collections sind lazy. Equals lässt die Beziehungen außen vor, damit der Persistence-Context keine Zyklen vergleicht.
 */
@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"addresses", "orders"})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vorname darf nicht leer sein")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String firstname;

    @NotBlank(message = "Nachname darf nicht leer sein")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String lastname;

    @Email(message = "Email muss valid sein")
    @Column(unique = true)
    private String email;

    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @JsonManagedReference("person-addresses")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    @JsonManagedReference("person-orders")
    private Set<Order> orders = new HashSet<>();

    public void addAddress(Address address) {
        addresses.add(address);
        address.setPerson(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setPerson(null);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setPerson(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setPerson(null);
    }
}
