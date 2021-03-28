
package ru.khrebtov.controller;


import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;
import ru.khrebtov.repositories.CourseRepository;
import ru.khrebtov.repositories.StudentRepository;

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
    private StudentRepository studentService;
    @EJB
    private CourseRepository courseService;

    private Student student;
    private List<Student> students;
    private List<Course> courses;



    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String createStudent() {
        this.student = new Student();
        return "/student_form.xhtml?faces-redirect=true";
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public String editStudent(Student student) {
        this.student = student;
        return "/student_form.xhtml?faces-redirect=true";
    }

    public void deleteStudent(Student student) {
        studentService.deleteById(student.getId());
    }

    public String saveStudent() {
        studentService.saveOrUpdate(student);
        return "/student.xhtml?faces-redirect=true";
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.students = studentService.findAll();
        this.courses = courseService.findAll();

    }

    public List<Course> getStudentCourses(Long studentId) {
        return studentService.getStudentCourses(studentId);
    }
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

