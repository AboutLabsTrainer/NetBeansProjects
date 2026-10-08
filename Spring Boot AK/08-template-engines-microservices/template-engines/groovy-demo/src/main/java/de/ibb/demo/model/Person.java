package de.ibb.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View-Model für eine Person in der Groovy Templates-Demo.
 * Die Klasse ist kein persistiertes Entity. Sie wird nur ins Model gelegt und von der Template-Engine ausgegeben.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String country;
    private String jobTitle;
    private String company;
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
