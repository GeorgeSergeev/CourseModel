package ru.khrebtov.entity;

import ru.khrebtov.entity.DTOentity.CourseRepr;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course")
@NamedQueries({
        @NamedQuery(name = "findAllCourse", query = "from Course "),
        @NamedQuery(name = "countAllCourse", query = "select count(*) from Course "),
        @NamedQuery(name = "deleteCourseById", query = "delete from Course c where c.id = :id"),
        @NamedQuery(name = "findCourseByNumber", query = "from Course c where c.number = :number"),
        @NamedQuery(name = "findCourseById", query = "from Course c where c.id = :id")
})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private int number;
    @Column
    private float cost;



    @OneToMany(mappedBy = "course")
    private Set<Student> student;
    @ManyToOne
    private StudyCourse studyCourse;
    @OneToOne
    private Professor professor;


    public Course() {
    }

    public Course(Long id, String name, int number, float cost) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

    public Course(CourseRepr courseRepr) {
        this.id = courseRepr.getId();
        this.name = courseRepr.getName();
        this.number = courseRepr.getNumber();
        this.cost = courseRepr.getCost();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    public StudyCourse getStudyCourse() {
        return studyCourse;
    }

    public void setStudyCourse(StudyCourse studyCourse) {
        this.studyCourse = studyCourse;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", cost=" + cost +
                '}';
    }
}
