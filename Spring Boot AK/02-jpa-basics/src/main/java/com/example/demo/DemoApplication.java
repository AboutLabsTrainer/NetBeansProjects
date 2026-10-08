package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet das JPA-Grundlagenbeispiel.
 * Das aktive Profil wählt die DataSource. Hibernate legt daraus den Persistence-Context für die Person-Entity an.
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

