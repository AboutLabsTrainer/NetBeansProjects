package de.ibb.boot.ak.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * JPA-Entity des Kontos mit Passwort, Rolle und Authorities.
 * Das Passwort liegt kodiert vor. UserDetailsService lädt den Datensatz in den SecurityContext.
 * Der Hash verlässt die Anwendung nicht: weder als JSON noch über toString im Log.
 */
@Entity
@Table(name = "jsprbak03_users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username darf nicht leer sein")
    @Size(min = 3, max = 50)
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @NotBlank(message = "Password darf nicht leer sein")
    @Column(nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Role role = Role.USER;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "jsprbak03_user_authorities",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Set<Authority> authorities = new HashSet<>();

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

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
        if (!(o instanceof User other)) {
            return false;
        }
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return User.class.hashCode();
    }
}
