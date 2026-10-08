package com.example.helloworldapi.model;

import com.example.helloworldapi.validation.ValidGermanPhone;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Personenmodell für Formularbindung und Validierung.
 * Der Service verwaltet die Instanzen, die Aspects ändern das Modell nicht.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;

    @NotBlank(message = "Vorname darf nicht leer sein")
    @Size(min = 2, max = 50, message = "Vorname: 2-50 Zeichen")
    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜß\\s-]+$", message = "Vorname darf nur Buchstaben enthalten")
    private String firstname;

    @NotBlank(message = "Nachname darf nicht leer sein")
    @Size(min = 2, max = 50, message = "Nachname: 2-50 Zeichen")
    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜß\\s-]+$", message = "Nachname darf nur Buchstaben enthalten")
    private String lastname;

    @NotBlank(message = "E-Mail darf nicht leer sein")
    @Email(message = "Bitte gültige E-Mail eingeben")
    private String email;

    @ValidGermanPhone
    private String phone;
}
