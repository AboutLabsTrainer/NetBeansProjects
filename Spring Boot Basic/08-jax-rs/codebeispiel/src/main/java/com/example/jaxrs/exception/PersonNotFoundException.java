package com.example.jaxrs.exception;

/**
 * Fachliche Exception, wenn eine Person nicht existiert.
 * JAX-RS übersetzt sie nicht von allein in HTTP. Dafür ist der ExceptionMapper zuständig.
 */
public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long id) {
        super("Person not found with id: " + id);
    }
}
