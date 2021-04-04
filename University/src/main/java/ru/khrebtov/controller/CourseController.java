package ru.khrebtov.controller;

import ru.khrebtov.entity.Course;
import ru.khrebtov.repositories.CourseRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CourseController implements Serializable {
    @EJB
    private CourseRepository courseRepo;

    private Course course;
    private List<Course> courses;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String createCourse() {
        this.course = new Course();
        return "/course_form.xhtml?faces-redirect=true";
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public String editCourse(Course course) {
        this.course = course;
        return "/course_form.xhtml?faces-redirect=true";
    }

    public void deleteCourse(Course course) {
        courseRepo.deleteById(course.getId());
    }

    public String saveCourse() {
        courseRepo.saveOrUpdate(course);
        return "/course.xhtml?faces-redirect=true";
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        courses = courseRepo.findAll();
    }

}
