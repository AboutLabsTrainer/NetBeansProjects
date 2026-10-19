package de.ibb.userservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Benutzer im Speicher des User-Service.
 * Die Demo persistiert ihn nicht.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String city;
}
