package ru.khrebtov.entity;

import javax.persistence.*;
import java.util.Objects;

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
    float cost;

    public Course() {
    }

    public Course(Long id, String name, int number, float cost) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.cost = cost;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return number == course.number && Float.compare(course.cost, cost) == 0 && id.equals(course.id) && name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, cost);
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
