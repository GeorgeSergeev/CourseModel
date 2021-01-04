package ru.tembaster.courses.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    @JoinTable(name = "student_course",
               joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    private Set<Student> students;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinTable(name = "professor_course",
               joinColumns = {@JoinColumn(name = "professor_id", referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")})
    private Professor professor;

    @OneToMany
    @JoinColumn(name = "course_id")
    private List<CourseProgress> courseProgress;

    public Course() {
    }

    public Course(Integer id, String name, Float price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public Course(String name, Float price) {
        this(null, name, price);
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
