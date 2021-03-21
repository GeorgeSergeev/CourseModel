
package ru.khrebtov.controller;


import ru.khrebtov.entity.Student;
import ru.khrebtov.repositories.StudentRepo;

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
    private StudentRepo studentRepo;

    private Student student;
    private List<Student> students;


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
        studentRepo.deleteById(student.getId());
    }

    public String saveStudent() {
        studentRepo.saveOrUpdate(student);
        return "/student.xhtml?faces-redirect=true";
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        students = studentRepo.findAll();
    }
}

