package ru.khrebtov.rest;


import ru.khrebtov.entity.dtoEntity.DtoProfessor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/professor")
public interface ProfessorServiceRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<DtoProfessor> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    DtoProfessor findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(DtoProfessor professor);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(DtoProfessor professor);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteById(@PathParam("id") Long id);

    void saveOrUpdate(DtoProfessor professor);

}
