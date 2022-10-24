package ru.tembaster.courses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "number")
    private int number;

    @NotNull
    @Column(name = "price")
    private Float price;

    @JsonIgnore
    @OneToOne
    @JoinTable(name = "professor_course",
               joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"))
    private Professor professor;

    @OneToMany(mappedBy = "course")
    private Set<CourseProgress> students;

    public Course() {
    }

    public Course(String name, Integer number, Float price) {
        this(null, name, number, price);
    }

    public Course(Integer id, String name, Integer number, Float price) {
        super(id);
        this.name = name;
        this.number = number;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
