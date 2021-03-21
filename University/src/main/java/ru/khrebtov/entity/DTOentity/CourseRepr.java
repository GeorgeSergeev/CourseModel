package ru.khrebtov.entity.DTOentity;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;

import java.util.Set;

public class CourseRepr extends Course {
    private Long id;
    private String name;
    private int number;
    private float cost;
    private Set<Student> students;

    public CourseRepr() {
    }
    public CourseRepr(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.number = course.getNumber();
        this.cost = course.getCost();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public Set<Student> getStudents() {
        return students;
    }

    @Override
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "CourseRepr{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", cost=" + cost +
                '}';
    }
}
