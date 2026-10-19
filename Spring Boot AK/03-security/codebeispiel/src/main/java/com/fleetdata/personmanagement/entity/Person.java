package com.fleetdata.personmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA-Entity einer Person in der abgesicherten Anwendung.
 * Der Eigentümer verknüpft den Datensatz mit dem Benutzer, den die eigenen Security-Ausdrücke prüfen.
 */
@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnoreProperties({"password", "authorities", "hibernateLazyInitializer"})
    private User owner;

    @Column(nullable = false)
    private boolean confidential = false;
}
