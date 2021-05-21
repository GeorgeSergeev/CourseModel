package ru.khrebtov.university.entity;



import ru.khrebtov.university.entity.dtoEntity.DtoCourse;
import ru.khrebtov.university.entity.dtoEntity.DtoStudyCourse;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "study_course", uniqueConstraints = {@UniqueConstraint(columnNames = {"course_id", "student_id"})})
public class StudyCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "ratings", joinColumns = @JoinColumn(name = "study_course_id"))
    @Column(name = "rating")
    @Transient
    private List<Integer> rating;


    @ManyToOne
    @NotNull
    private Student student;

    @ManyToOne
    @NotNull
    private Course course;


    public StudyCourse() {
    }

    public StudyCourse(Long id, List<Integer> rating, @NotNull Student student, @NotNull Course course) {
        this.id = id;
        this.rating = rating;
        this.student = student;
        this.course = course;
    }

    public StudyCourse(DtoStudyCourse dtoStudyCourse) {
        this.id = dtoStudyCourse.getId();
        if (dtoStudyCourse.getRating() != null) {
            this.rating = dtoStudyCourse.getRating();
        }
        if (dtoStudyCourse.getStudent() != null) {
            this.student = new Student(dtoStudyCourse.getStudent());
        }
        DtoCourse course = dtoStudyCourse.getCourse();
        if (course != null) {
            this.course = new Course(course.getId(), course.getName(), course.getNumber(), course.getCost());
        }
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

    public Course getCourse() {
        return course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
