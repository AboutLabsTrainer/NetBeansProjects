package de.ibb.orderservice.model;
import lombok.*;
/**
 * Benutzer, wie der Order-Service ihn vom User-Service übernimmt.
 * Es ist ein Übertragungsobjekt, kein Entity des User-Service.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
}
