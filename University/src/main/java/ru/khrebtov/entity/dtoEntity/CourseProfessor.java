package ru.khrebtov.entity.dtoEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_professor")
public class CourseProfessor {
    @Id
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "professors_id")
    private Long professorsId;

    public CourseProfessor() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getProfessorsId() {
        return professorsId;
    }

    public void setProfessorsId(Long professorsId) {
        this.professorsId = professorsId;
    }
}
