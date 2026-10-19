package de.ibb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Startet die Demo für die Template-Engine JSP.
 * Der eingebettete Webserver liefert die Seite, die diese Engine aus dem View-Namen rendert.
 */
@SpringBootApplication
public class JspDemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(JspDemoApplication.class, args);
    }
}
