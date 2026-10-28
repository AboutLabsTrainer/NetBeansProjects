package de.ibb.boot.ak.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA-Entity einer Person in der abgesicherten Anwendung.
 * Der Eigentümer verknüpft den Datensatz mit dem Benutzer, den die eigenen Security-Ausdrücke prüfen.
 */
@Entity
@Table(name = "jsprbak03_persons")
@Data
@NoArgsConstructor
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
        if (!(o instanceof Person other)) {
            return false;
        }
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return Person.class.hashCode();
    }
}
