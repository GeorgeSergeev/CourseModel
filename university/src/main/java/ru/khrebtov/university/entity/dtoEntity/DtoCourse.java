package ru.khrebtov.university.entity.dtoEntity;



import ru.khrebtov.university.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class DtoCourse {
    private Long id;
    private String name;
    private int number;
    private float cost;
    private List<DtoStudent> students;
    private List<DtoStudyCourse> studyCourses;
    private List<DtoProfessor> professors;

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
            this.studyCourses = new ArrayList<>();
            course.getStudyCourses().forEach(studyCourse -> studyCourses.add(new DtoStudyCourse(studyCourse)));
        }
        if (course.getStudents() != null) {
            this.students = new ArrayList<>();
            course.getStudents().forEach(student -> students.add(new DtoStudent(student)));
        }

        if (course.getProfessors() != null && !(course.getProfessors().isEmpty())) {
            this.professors = new ArrayList<>();
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

    public List<DtoStudent> getStudents() {
        return students;
    }

    public void setStudents(List<DtoStudent> students) {
        this.students = students;
    }

    public List<DtoStudyCourse> getStudyCourses() {
        return studyCourses;
    }

    public void setStudyCourses(List<DtoStudyCourse> studyCourses) {
        this.studyCourses = studyCourses;
    }

    public List<DtoProfessor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<DtoProfessor> professors) {
        this.professors = professors;
    }
}


