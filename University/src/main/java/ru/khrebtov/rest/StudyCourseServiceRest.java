package ru.khrebtov.rest;

import ru.khrebtov.entity.StudyCourse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/study")
public interface StudyCourseServiceRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<StudyCourse> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    StudyCourse findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(StudyCourse studyCourse);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(StudyCourse studyCourse);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteById(@PathParam("id") Long id);
}
