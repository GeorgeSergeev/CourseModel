package ru.khrebtov.entity.dtoEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_students")
public class CourseStudent {
    @Id
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "student_id")
    private Long studentId;

    public CourseStudent() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
