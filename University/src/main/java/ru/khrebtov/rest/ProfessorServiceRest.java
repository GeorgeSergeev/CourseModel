package ru.khrebtov.rest;

import ru.khrebtov.entity.Professor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/professor")
public interface ProfessorServiceRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Professor> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Professor findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(Professor professor);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(Professor professor);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteById(@PathParam("id") Long id);

}
