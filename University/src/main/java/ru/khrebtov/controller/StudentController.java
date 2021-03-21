
package ru.khrebtov.controller;


import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.DTOentity.CourseRepr;
import ru.khrebtov.entity.DTOentity.StudentRepr;
import ru.khrebtov.entity.Student;
import ru.khrebtov.service.CourseService;
import ru.khrebtov.service.StudentService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class StudentController implements Serializable {

    @EJB
    private StudentService studentService;
    @EJB
    private CourseService courseService;

    private StudentRepr student;
    private List<StudentRepr> students;
    private List<CourseRepr> courses;



    public StudentRepr getStudent() {
        return student;
    }

    public void setStudent(StudentRepr student) {
        this.student = student;
    }

    public String createStudent() {
        this.student = new StudentRepr();
        return "/student_form.xhtml?faces-redirect=true";
    }

    public List<StudentRepr> getAllStudents() {
        return students;
    }

    public String editStudent(StudentRepr student) {
        this.student = student;
        return "/student_form.xhtml?faces-redirect=true";
    }

    public void deleteStudent(Student student) {
        studentService.deleteById(student.getId());
    }

    public String saveStudent() {
        studentService.merge(student);
        return "/student.xhtml?faces-redirect=true";
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.students = studentService.findAll();
        this.courses = courseService.getAll();

    }

    public List<Course> getStudentCourses(StudentRepr student) {
        return studentService.getStudentCourses(student);
    }
    public List<CourseRepr> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseRepr> courses) {
        this.courses = courses;
    }
}

