package ru.khrebtov.rest;

import ru.khrebtov.entity.dtoEntity.DtoStudyCourse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/study")
public interface StudyCourseServiceRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<DtoStudyCourse> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    DtoStudyCourse findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(DtoStudyCourse studyCourse);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(DtoStudyCourse studyCourse);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteById(@PathParam("id") Long id);
}
