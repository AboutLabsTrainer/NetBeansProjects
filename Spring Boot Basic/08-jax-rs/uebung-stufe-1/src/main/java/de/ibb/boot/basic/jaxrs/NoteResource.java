package de.ibb.boot.basic.jaxrs;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/notes")
public class NoteResource {
    private final List<String> notes = new ArrayList<>(List.of("Erste Notiz"));

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String list() {
        // TODO: return String.join(",", notes);
        return "";
    }

}
