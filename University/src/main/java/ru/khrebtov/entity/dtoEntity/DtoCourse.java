package ru.khrebtov.entity.dtoEntity;

import ru.khrebtov.entity.Course;

import java.util.HashSet;
import java.util.Set;

public class DtoCourse {
    private Long id;
    private String name;
    private int number;
    private float cost;
    private Set<DtoStudent> students;
    private Set<DtoStudyCourse> studyCourses;
    private Set<DtoProfessor> professors;

    public DtoCourse() {
    }

    public DtoCourse(Long id, String name, int number, float cost) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

    public DtoCourse(Course course) {
        this(course.getId(), course.getName(), course.getNumber(), course.getCost());
        if (course.getStudyCourses() != null) {
            this.studyCourses = new HashSet<>();
            course.getStudyCourses().forEach(studyCourse -> studyCourses.add(new DtoStudyCourse(studyCourse)));
        }
        if (course.getStudents() != null) {
            this.students = new HashSet<>();
            course.getStudents().forEach(student -> students.add(new DtoStudent(student)));
        }

        if (course.getProfessors() != null && !(course.getProfessors().isEmpty())) {
            this.professors = new HashSet<>();
            course.getProfessors().forEach(p -> professors.add(new DtoProfessor(p)));
        }

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

    public Set<DtoStudent> getStudents() {
        return students;
    }

    public void setStudents(Set<DtoStudent> students) {
        this.students = students;
    }

    public Set<DtoStudyCourse> getStudyCourses() {
        return studyCourses;
    }

    public void setStudyCourses(Set<DtoStudyCourse> studyCourses) {
        this.studyCourses = studyCourses;
    }

    public Set<DtoProfessor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<DtoProfessor> professors) {
        this.professors = professors;
    }
}


