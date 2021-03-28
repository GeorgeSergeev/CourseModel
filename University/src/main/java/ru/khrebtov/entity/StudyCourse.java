package ru.khrebtov.entity;


import ru.khrebtov.entity.dtoEntity.DtoStudyCourse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "study_course")
@NamedQueries({
        @NamedQuery(name = "findAllStudyCourse", query = "from StudyCourse "),
        @NamedQuery(name = "countAllStudyCourse", query = "select count(*) from StudyCourse"),
        @NamedQuery(name = "deleteStudyCourseById", query = "delete from StudyCourse sc where sc.id = :id"),
        @NamedQuery(name = "findStudyCourseById", query = "from StudyCourse sc where sc.id = :id"),
        @NamedQuery(name = "getAverageRating",query = "select sum(rating)/count(*) from Rating r " +
                "where r.studyCourseId=:id"),
        @NamedQuery(name = "getRatings",query = "select r.rating from Rating r where r.studyCourseId=:id")
})
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
    public StudyCourse(DtoStudyCourse dtoStudyCourse) {
        this.id = dtoStudyCourse.getId();
        this.rating = dtoStudyCourse.getRating();
        this.student = dtoStudyCourse.getStudent();
        this.course = dtoStudyCourse.getCourse();
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
