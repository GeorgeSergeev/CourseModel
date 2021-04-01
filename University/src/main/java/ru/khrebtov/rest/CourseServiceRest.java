package ru.khrebtov.rest;


import ru.khrebtov.entity.dtoEntity.DtoCourse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/course")
public interface CourseServiceRest {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<DtoCourse> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    DtoCourse findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(DtoCourse course);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(DtoCourse course);

    @DELETE
    @Path("/{id}")
    void deleteById(@PathParam("id") Long id);


    boolean addStudentIntoCourse( Long course, Long student);


    void deleteStudentFromCourse( Long courseId, Long studentId);
}
