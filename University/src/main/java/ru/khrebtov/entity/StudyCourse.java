package ru.khrebtov.entity;

import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "study_course")
public class StudyCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "ratings", joinColumns = @JoinColumn(name = "study_course_id"))
    @Column(name = "rating")
    private List<Integer> rating;



    @ManyToOne
    private Student student;

    @OneToMany(mappedBy = "studyCourse")
    private Set<Course> courses;



    public StudyCourse() {
    }

    public List<Integer> getRating() {
        return rating;
    }

    public void setRating(List<Integer> rating) {
        this.rating = rating;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
