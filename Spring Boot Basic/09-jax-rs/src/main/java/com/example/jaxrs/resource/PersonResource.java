package com.example.jaxrs.resource;

import com.example.jaxrs.exception.PersonNotFoundException;
import com.example.jaxrs.model.Person;
import com.example.jaxrs.service.PersonService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * JAX-RS-Ressource für die Personen.
 * Weil die Klasse eine Spring-Bean ist, kann sie den gemeinsamen PersonService injizieren. Jersey mappt die HTTP-Verben auf die Methoden.
 */
@Component
@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GET
    public List<Person> getAllPersons(
            @QueryParam("firstname") String firstname,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("0") int size) {

        List<Person> result = personService.getAllPersons();

        if (firstname != null && !firstname.isBlank()) {
            String search = firstname.toLowerCase();
            result = result.stream()
                    .filter(p -> p.getFirstname() != null
                            && p.getFirstname().toLowerCase().contains(search))
                    .toList();
        }

        if (size > 0) {
            int fromIndex = page * size;
            if (fromIndex >= result.size()) {
                return List.of();
            }
            int toIndex = Math.min(fromIndex + size, result.size());
            result = result.subList(fromIndex, toIndex);
        }

        return result;
    }

    @GET
    @Path("/{id}")
    public Person getPersonById(@PathParam("id") Long id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            throw new PersonNotFoundException(id);
        }
        return person;
    }

    @POST
    public Response createPerson(Person person) {
        Person created = personService.createPerson(person);
        return Response
                .status(Response.Status.CREATED)
                .entity(created)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") Long id, Person person) {
        Person updated = personService.updatePerson(id, person);
        if (updated == null) {
            throw new PersonNotFoundException(id);
        }
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        boolean deleted = personService.deletePerson(id);
        if (!deleted) {
            throw new PersonNotFoundException(id);
        }
        return Response.noContent().build();
    }
}
