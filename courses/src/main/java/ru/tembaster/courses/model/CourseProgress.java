package ru.tembaster.courses.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "course_progress")
public class CourseProgress extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "grade")
    private Integer grade;
}
