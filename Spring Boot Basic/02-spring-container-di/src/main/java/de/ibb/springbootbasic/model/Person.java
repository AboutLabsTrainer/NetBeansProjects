package de.ibb.springbootbasic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person Model - Repräsentiert eine Person in unserem System
 *
 * @Data - Lombok generiert automatisch:
 * - Getter für alle Fields
 * - Setter für alle Fields
 * - toString()
 * - equals() und hashCode()
 *
 * @AllArgsConstructor - Konstruktor mit allen Parametern
 * @NoArgsConstructor - Konstruktor ohne Parameter (für Jackson JSON)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
