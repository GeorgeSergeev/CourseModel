package ru.khrebtov.entity.dtoEntity;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Professor;
import ru.khrebtov.entity.Student;
import ru.khrebtov.entity.StudyCourse;

import java.util.Set;

public class DtoCourse {
    private Long id;
    private String name;
    private int number;
    private float cost;
    private Set<Student> students;
    private Set<StudyCourse> studyCourses;
    private Set<Professor> professors;

    public DtoCourse() {
    }

    public DtoCourse(Course course, Set<Student> students, Set<StudyCourse> studyCourses, Set<Professor> professors) {
        this.id = course.getId();
        this.name = course.getName();
        this.number = course.getNumber();
        this.cost = course.getCost();
        this.students = students;
        this.studyCourses = studyCourses;
        this.professors = professors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<StudyCourse> getStudyCourses() {
        return studyCourses;
    }

    public void setStudyCourses(Set<StudyCourse> studyCourses) {
        this.studyCourses = studyCourses;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }
}
