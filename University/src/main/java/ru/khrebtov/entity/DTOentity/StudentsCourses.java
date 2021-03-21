package ru.khrebtov.entity.DTOentity;

import javax.persistence.*;

@Entity
@Table(name = "students_courses")
@NamedQueries({
        @NamedQuery(name = "courseLeftJoin", query = "select c from Course c left join StudentsCourses sc on sc.coursesId=c.id where sc.studentId =:studentId")
})
public class StudentsCourses {
    @Id
    @Column(name = "student_id")
    Long studentId;
    @Column(name = "courses_id")
    Long coursesId;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCoursesId() {
        return coursesId;
    }

    public void setCoursesId(Long coursesId) {
        this.coursesId = coursesId;
    }
}
