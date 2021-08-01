package ru.softlab.coursemodel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "completing_courses")
public class CompletingCourse extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column
    private Integer mark;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private Integer version;
        private Student student;
        private Course course;
        private Integer mark;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder version(Integer version) {
            this.version = version;
            return this;
        }

        public Builder student(Student student) {
            this.student = student;
            return this;
        }

        public Builder course(Course course) {
            this.course = course;
            return this;
        }

        public Builder mark(Integer mark) {
            this.mark = mark;
            return this;
        }

        public CompletingCourse build() {
            CompletingCourse completingCourse = new CompletingCourse();
            completingCourse.setId(id);
            completingCourse.setVersion(version);
            completingCourse.setStudent(student);
            completingCourse.setCourse(course);
            completingCourse.setMark(mark);
            return completingCourse;
        }
    }
}
