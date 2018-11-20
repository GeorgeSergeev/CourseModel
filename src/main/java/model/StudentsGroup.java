package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(StudentsGroup.GroupKey.class)
@Table(name = "courses_groups")
public class StudentsGroup {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private Student student;

    @Column(name = "status")
    private StudentStatus status;

    @EqualsAndHashCode
    @ToString
    public static class GroupKey implements Serializable {

        static final long serialVersionUID = 1L;

        @Getter
        @Setter
        private int course;

        @Getter
        @Setter
        private int student;

    }

    public StudentsGroup() {
    }

    public StudentsGroup(Course course, Student student) {
        this.course = course;
        this.student = student;
        this.status = StudentStatus.JOINED;
    }

    public StudentsGroup(Course course, Student student, StudentStatus status) {
        this.course = course;
        this.student = student;
        this.status = status;
    }

}
