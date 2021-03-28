package ru.khrebtov.rest;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/student")
public interface StudentServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Student> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Student findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(Student student);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(Student student);

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    void deleteById(@PathParam("id") Long id);

    void signIntoCourse(Course course, Student student);

    @GET
    @Path("/course/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Course> getStudentCourses(@PathParam("id") Long studentId);
}
