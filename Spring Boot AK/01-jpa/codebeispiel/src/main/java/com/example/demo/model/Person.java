package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * JPA-Entity für die Tabelle jsprbak01_persons.
 * Der Persistence-Context verfolgt Änderungen an verwalteten Instanzen und schreibt sie am Transaktionsende.
 */
@Entity
@Table(name = "jsprbak01_persons")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Vorname darf nicht leer sein")
	@Column(nullable = false, length = 100)
	private String firstname;

	@NotBlank(message = "Nachname darf nicht leer sein")
	@Column(nullable = false, length = 100)
	private String lastname;

	@Email(message = "Email muss gültig sein")
	@Column(unique = true, length = 150)
	private String email;

	// JPA benötigt einen Default-Konstruktor (ohne Parameter).
	public Person() {
	}

	public Person(String firstname, String lastname, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

