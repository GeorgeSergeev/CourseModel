package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@JsonAutoDetect
@Data
@Entity
@IdClass(StudentsGroup.GroupKey.class)
@Table(name = "courses_groups")
public class StudentsGroup {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;

    @JsonBackReference
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsGroup that = (StudentsGroup) o;
        return Objects.equals(course.getId(), that.course.getId()) &&
                Objects.equals(student.getId(), that.student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(course.getId(), student.getId());
    }
}
