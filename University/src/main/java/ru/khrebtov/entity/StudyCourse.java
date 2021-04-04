package ru.khrebtov.entity;


import ru.khrebtov.entity.dtoEntity.DtoCourse;
import ru.khrebtov.entity.dtoEntity.DtoStudyCourse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "study_course", uniqueConstraints = {@UniqueConstraint(columnNames = {"course_id", "student_id"})})
@NamedQueries({
        @NamedQuery(name = "findAllStudyCourse", query = "from StudyCourse "),
        @NamedQuery(name = "countAllStudyCourse", query = "select count(*) from StudyCourse"),
        @NamedQuery(name = "deleteStudyCourseById", query = "delete from StudyCourse sc where sc.id = :id"),
        @NamedQuery(name = "findStudyCourseById", query = "from StudyCourse sc where sc.id = :id"),
        @NamedQuery(name = "getAverageRating", query = "select 1.0*sum(rating)/count(*) from Rating r " +
                "where r.studyCourseId=:id"),
        @NamedQuery(name = "getRatings", query = "select r.rating from Rating r where r.studyCourseId=:id"),
        @NamedQuery(name = "getCourseByStudyCourseId", query = "select c from Course c " +
                "left join StudyCourse sc on c.id=sc.course.id where sc.id = :id"),
        @NamedQuery(name = "getStudentByStudyCourseId", query = "select s from Student s " +
                "left join StudyCourse sc on s.id=sc.student.id where sc.id = :id"),
        @NamedQuery(name = "findByCourseIdAndStudentId", query = "from StudyCourse sc " +
                "where sc.course.id=:courseId AND sc.student.id = :studentId")
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
