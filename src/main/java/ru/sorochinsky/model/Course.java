package ru.sorochinsky.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Simple JavaBean object that represents role of {@link Course}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

@Entity // This tells Hibernate to make a table out of this class
public class Course {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer number;

    @NotNull
    private Float cost;

    @OneToOne(optional = false, mappedBy="course")
    private CourseProgress courseProgress;

    @OneToOne(optional = false, mappedBy="course")
    private Professor professor;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    private List<Student> students;

    public CourseProgress getCourseProgress() {
        return courseProgress;
    }

    public void setCourseProgress(CourseProgress courseProgress) {
        this.courseProgress = courseProgress;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

}
