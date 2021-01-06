package ru.tembaster.courses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
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
    @ManyToMany
    @JoinTable(name = "student_course",
               joinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")})
    private Set<Student> students;

    @JsonIgnore
    @OneToOne
    @JoinTable(name = "professor_course",
               joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "professor_id", referencedColumnName = "id"))
    private Professor professor;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    @Fetch(FetchMode.SELECT)
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
