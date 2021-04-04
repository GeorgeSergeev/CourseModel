package ru.khrebtov.rest;

import ru.khrebtov.entity.dtoEntity.DtoCourse;
import ru.khrebtov.entity.dtoEntity.DtoStudent;
import ru.khrebtov.entity.dtoEntity.DtoStudyCourse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Path("/student")
public interface StudentServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<DtoStudent> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    DtoStudent findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(DtoStudent student);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(DtoStudent student);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteById(@PathParam("id") Long id);

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    void signIntoCourse(DtoStudyCourse studyCourse);

    @GET
    @Path("/course/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Set<DtoCourse> getStudentCourses(@PathParam("id") Long studentId);
}
