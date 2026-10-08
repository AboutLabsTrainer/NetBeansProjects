package com.example.jaxrs.exception;

import com.example.jaxrs.dto.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Übersetzt PersonNotFoundException in eine HTTP-404-Antwort.
 * Als Provider findet Jersey den Mapper und ruft ihn statt einer generischen Fehlerseite auf.
 */
@Provider
public class PersonNotFoundExceptionMapper
        implements ExceptionMapper<PersonNotFoundException> {

    @Override
    public Response toResponse(PersonNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
                Response.Status.NOT_FOUND.getStatusCode(),
                exception.getMessage(),
                System.currentTimeMillis()
        );

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
